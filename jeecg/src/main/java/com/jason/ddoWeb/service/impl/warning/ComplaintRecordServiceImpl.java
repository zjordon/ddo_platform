package com.jason.ddoWeb.service.impl.warning;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.entity.statistics.SendRecordEntity;
import com.jason.ddoWeb.entity.warning.ChannelComplaintMonthEntity;
import com.jason.ddoWeb.entity.warning.ComplaintRecordEntity;
import com.jason.ddoWeb.entity.warning.FullComplaintMonthEntity;
import com.jason.ddoWeb.entity.warning.ProviceComplaintMonthEntity;
import com.jason.ddoWeb.service.warning.ComplaintRecordServiceI;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.system.pojo.base.TSTerritory;

@Service("complaintRecordService")
@Transactional
public class ComplaintRecordServiceImpl extends CommonServiceImpl implements ComplaintRecordServiceI {
	private static final Logger logger = Logger.getLogger(ComplaintRecordServiceImpl.class);
	private final static String GET_RECORD_COUNT = "select count(*) from ddo_complaint_record where msisdn = ? and COMPLAINT_MONTH = ?";
	private final static String FIND_TERRITORYCODE_BY_PROVIDENAME = "from TSTerritory where territoryLevel = 1 and territoryName like ?";
	private final static String FIND_PROVICE_COMPLAINT_MONTH = "from ProviceComplaintMonthEntity where proviceCode = ? and sumMonth = ?";
	private final static String FIND_SEND_RECORD = "from SendRecordEntity where msisdn = ? and sendDate >= ? and sendDate <= ?";
	private final static String FIND_CHANNEL_COMPLAINT_MONTH = "from ChannelComplaintMonthEntity where channelId = ? and sumMonth = ?";

	@Override
	public void saveList(List<ComplaintRecordEntity> list) {
		for (ComplaintRecordEntity entity : list) {
			this.saveComplaintRecord(entity);
		}
		
	}
	
	private void saveComplaintRecord(ComplaintRecordEntity entity) {
		//从原始记录中根据投诉归档日期获取投诉月份
		int complaintMonth = entity.getComplaintDate()/100;
		//判断该号码是否已经被记录
		if (!this.isExist(complaintMonth, entity.getMsisdn())) {
			//对应省份及对应月份的投诉总记录是否存在
			//先根据原始记录中的省份字段找到数据库表t_s_territory中的territoryCode
			//因为原始记录中的省份名称不带省或市如福建省只会导入福建，上海市只会导入上海，所以需要通过like的方式来找对应的记录如like '上海%'这样找到对应的记录
			List list = super.findHql(FIND_TERRITORYCODE_BY_PROVIDENAME, new Object[]{entity.getProvice() + "%"});
			if (!list.isEmpty()) {
				if (list.size() > 1) {
					logger.warn("one more pvovice is math with " + entity.getProvice());
				}
				TSTerritory territory = (TSTerritory)list.get(0);
				list = super.findHql(FIND_PROVICE_COMPLAINT_MONTH, new Object[]{territory.getTerritoryCode(), new Integer(complaintMonth)});
				if (list.isEmpty()) {
					//创建新的总记录(投诉数为1,投诉数阀值和万投比阀值都为0)
					ProviceComplaintMonthEntity proviceComplaintMonth = new ProviceComplaintMonthEntity();
					proviceComplaintMonth.setProviceCode(territory.getTerritoryCode());
					proviceComplaintMonth.setSumMonth(new Integer(complaintMonth));
					proviceComplaintMonth.setNum(new Integer(1));
					proviceComplaintMonth.setNumThreshold(new Integer(0));
					proviceComplaintMonth.setRatioThreshold(new Double(0));
					super.save(proviceComplaintMonth);
				} else {
					//对应省份的投诉数加1
					ProviceComplaintMonthEntity proviceComplaintMonth = (ProviceComplaintMonthEntity)list.get(0);
					proviceComplaintMonth.setNum(new Integer(proviceComplaintMonth.getNum().intValue() + 1));
					super.updateEntitie(proviceComplaintMonth);
				}
				
			} else {
				logger.warn("the provice is not exist in database with " + entity.getProvice());
			}
			//对应月份的全量投诉记录是否存在
			list = super.findByProperty(FullComplaintMonthEntity.class, "sumMonth", new Integer(complaintMonth));
			if (list.isEmpty()) {
				//创建新的总记录(投诉数+1)
				FullComplaintMonthEntity fullComplaintMonth = new FullComplaintMonthEntity();
				fullComplaintMonth.setSumMonth(new Integer(complaintMonth));
				fullComplaintMonth.setNum(new Integer(1));
				fullComplaintMonth.setNumThreshold(new Integer(0));
				fullComplaintMonth.setRatioThreshold(new Double(0));
				super.save(fullComplaintMonth);
			} else {
				FullComplaintMonthEntity fullComplaintMonth = (FullComplaintMonthEntity)list.get(0);
				fullComplaintMonth.setNum(new Integer(fullComplaintMonth.getNum().intValue() + 1));
				super.updateEntitie(fullComplaintMonth);
			}
			//根据号码和月份查找对应的发送记录
			//从ddo_send_record表中找，不要从ddo_msg表中找以免给ddo_msg表产生太大的压力
			int startDate = complaintMonth * 100 + 1;
			int endDate = startDate + 30;
			list = super.findHql(FIND_SEND_RECORD, new Object[]{entity.getMsisdn(), new Integer(startDate), new Integer(endDate)});
			if (!list.isEmpty()) {
				SendRecordEntity sendRecord = (SendRecordEntity)list.get(0);
				String channelId = sendRecord.getChannelId();
				list = super.findHql(FIND_CHANNEL_COMPLAINT_MONTH, new Object[]{channelId, new Integer(complaintMonth)});
				if (list.isEmpty()) {
					ChannelComplaintMonthEntity channelComplaintMonth = new ChannelComplaintMonthEntity();
					channelComplaintMonth.setSumMonth(new Integer(complaintMonth));
					channelComplaintMonth.setNum(new Integer(1));
					channelComplaintMonth.setNumThreshold(new Integer(0));
					channelComplaintMonth.setRatioThreshold(new Double(0));
					channelComplaintMonth.setChannelId(channelId);
					super.save(channelComplaintMonth);
				} else {
					ChannelComplaintMonthEntity channelComplaintMonth = (ChannelComplaintMonthEntity)list.get(0);
					channelComplaintMonth.setNum(new Integer(channelComplaintMonth.getNum().intValue() + 1));
					super.updateEntitie(channelComplaintMonth);
				}
			} else {
				logger.warn("the sendrecord is not exist with msisdn " + entity.getMsisdn() + ", startDate " + startDate + ", endDate " + endDate);
			}
			//保存原始记录
			entity.setComplaintMonth(complaintMonth);
			super.save(entity);
		} else {
			logger.info("the record is exist with msisdn " + entity.getMsisdn() + " date is " + entity.getComplaintDate());
		}
	}
	
	private boolean isExist(int complaintMonth, Long msisdn) {
		boolean exist = false;
		Long count = super.getCountForJdbcParam(GET_RECORD_COUNT, new Object[]{msisdn, new Integer(complaintMonth)});
		if (count.longValue() > 0) {
			exist = true;
		}
		return exist;
	}
	
}