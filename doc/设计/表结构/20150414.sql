ALTER TABLE `ddo_msg`
ADD COLUMN `create_date`  datetime NULL COMMENT '创建时间' AFTER `repeat_flag`,
ADD COLUMN `response_time`  datetime NULL COMMENT '请求返回时间' AFTER `create_date`;

ALTER TABLE `ddo_repeat_msg_record`
ADD COLUMN `response_time`  datetime NULL COMMENT '请求返回时间' AFTER `ddo_msg_id`;

ALTER TABLE `ddo_bill_report`
ADD COLUMN `send_time`  datetime NULL COMMENT '发送时间' AFTER `repeat_flag`,
ADD COLUMN `response_time`  datetime NULL COMMENT '请求返回时间' AFTER `send_time`;
ALTER TABLE `ddo_repeat_report_record`
ADD COLUMN `response_time`  datetime NULL COMMENT '请求返回时间' AFTER `bill_report_id`;

ALTER TABLE `ddo_up_channel_record`
ADD COLUMN `response_time`  datetime NULL COMMENT '请求返回时间' AFTER `ddo_msg_id`;
ALTER TABLE `ddo_repeat_up_record`
ADD COLUMN `response_time`  datetime NULL COMMENT '请求返回时间' AFTER `record_id`;
ALTER TABLE `ddo_up_channel_record`
ADD COLUMN `send_time`  datetime NULL COMMENT '发送时间' AFTER `response_time`;