/**
 * 
 */
package com.jason.ddoMsg;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import com.jason.ddoMsg.util.DataSourceUtil;
import com.jason.ddoMsg.util.DmMobile;
import com.jason.ddoMsg.util.DmMobileArrays;

/**
 * 从数据库表dm_mobile中获取手机号归属地信息写入到指定的文件中
 * @author jasonzhang
 *
 */
public class LoadDmMobileTest {
	private final static String GET_RECORDS_NUM = "select count(*) from dm_mobile t where t.type = 2 and t.province_code is not null and t.city_code is not null";
	private final static String GET_RECORDS = "select t.mobilenumber, t.province_code, t.city_code from dm_mobile t where t.type = 2 and t.province_code is not null and t.city_code is not null";
	//private final static String GET_RECORDS_NUM = "select count(*) from dm_mobile t where t.areacode = '0771' and t.province_code is not null and t.city_code is not null";
	//private final static String GET_RECORDS = "select t.mobilenumber, t.province_code, t.city_code from dm_mobile t where t.areacode = '0771' and t.province_code is not null and t.city_code is not null";

	public final static void main(String[] args) throws Exception {
		DataSource dataSource = DataSourceUtil.getDataSource();
		Connection conn = dataSource.getConnection();
		if (conn != null) {
			PreparedStatement pstmt = conn.prepareStatement(GET_RECORDS_NUM);
			ResultSet rs = pstmt.executeQuery();
			int recordsNum = 0;
			if (rs.next()) {
				recordsNum = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			if (recordsNum > 0) {
				DmMobile[] dmMobiles = new DmMobile[recordsNum];
				pstmt = conn.prepareStatement(GET_RECORDS);
				rs = pstmt.executeQuery();
				int idx = 0;
				while (rs.next()) {
					dmMobiles[idx++] = new DmMobile(rs.getInt(1), Integer.parseInt(rs.getString(2)), Integer.parseInt(rs.getString(3)));
				}
				rs.close();
				pstmt.close();
				conn.close();
				//对数组排序
				DmMobileArrays.sort(dmMobiles);
				//写入文件
				File f= new File("d:/work/data/ddo/dm-mobile.txt");
				if (f.exists()) {
					f.delete();
				}
				BufferedWriter output = new BufferedWriter(new FileWriter(f));
				//先写入记录数
				output.write(Integer.toString(recordsNum));
				output.write('\r');
				output.write('\n');
				for (int i=0; i<recordsNum; i++) {
					output.write(Integer.toString(dmMobiles[i].msisdn));
					output.write('|');
					output.write(Integer.toString(dmMobiles[i].provinceCode));
					output.write('|');
					output.write(Integer.toString(dmMobiles[i].cityCode));
					output.write('\r');
					output.write('\n');
				}
				output.flush();
				output.close();
			} else {
				conn.close();
			}
			
		}
	}
}
