drop table ddo_need_repeat_msg;
ALTER TABLE `ddo_repeat_msg_record`
DROP COLUMN `repeat_reason`,
DROP COLUMN `repeat_time`;
ALTER TABLE `ddo_msg`
ADD COLUMN `repeat_flag`  smallint NULL COMMENT '重发标识' AFTER `request_id`;
drop table ddo_need_repeat_report;
ALTER TABLE `ddo_repeat_report_record`
DROP COLUMN `repeat_reason`,
DROP COLUMN `repeat_date`;
ALTER TABLE `ddo_bill_report`
ADD COLUMN `repeat_flag`  smallint NULL COMMENT '重发标识' AFTER `create_date`;
drop table ddo_need_up_channel;
ALTER TABLE `ddo_repeat_up_record`
DROP COLUMN `repeat_reason`,
DROP COLUMN `repeat_time`;
ALTER TABLE `ddo_up_channel_record`
ADD COLUMN `repeat_flag`  smallint NULL COMMENT '重发标识' AFTER `create_date`;
