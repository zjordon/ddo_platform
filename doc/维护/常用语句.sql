--查询是否有找不到对应traniid的状态报告记录
select * from ddo_bill_report where process_result = 2;
--查询是否有需要重发的记录
select * from ddo_bill_report where process_result = 2 or repeat_flag = 1;
select * from ddo_up_channel_record t where t.repeat_flag = 1;
select * from ddo_msg t where t.repeat_flag = 1;