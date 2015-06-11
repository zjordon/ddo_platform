--查询是否有找不到对应traniid的状态报告记录
select * from ddo_bill_report where process_result = 2;
--查询是否有需要重发的记录
select * from ddo_bill_report where process_result = 2 or repeat_flag = 1;
select * from ddo_up_channel_record t where t.repeat_flag = 1;
select * from ddo_msg t where t.repeat_flag = 1;

--查看某个号码的处理时间
select t1.request_time, t1.end_time, unix_timestamp(t1.end_time) - unix_timestamp(t1.request_time) from ddo_channel_request t1, ddo_msg t2 where t1.id = t2.request_id and t2.msisdn = 13489569492;
--查看最近请求的处理时间
select t.request_time, t.end_time, unix_timestamp(t.end_time)-unix_timestamp(t.request_time) from ddo_channel_request t where t.request_time is not null and t.end_time is not null order by t.request_time desc limit 30;
--查看某个时间段内请求的处理时间
select t2.msisdn, t1.request_time, t1.end_time, unix_timestamp(t1.end_time) - unix_timestamp(t1.request_time) from ddo_channel_request t1, ddo_msg t2 where t1.id = t2.request_id and t1.request_time > '2015-06-11 10:00:00' and t1.request_time < '2015-06-11 11:00:00' order by t1.request_time desc;
--查看某个时间段内超过15秒的请求
select t.* from(
select t2.request_id, t2.id, t2.msisdn, t1.request_time, t1.end_time, t2.send_time, t2.response_time, (unix_timestamp(t1.end_time) - unix_timestamp(t1.request_time)) as de from ddo_channel_request t1, ddo_msg t2 where t1.id = t2.request_id and t1.request_time > '2015-06-11 10:00:00' and t1.request_time < '2015-06-11 11:00:00'
) t where de > 15