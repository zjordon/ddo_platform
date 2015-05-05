ALTER TABLE `ddo_channel_request`
MODIFY COLUMN `end_time`  datetime NULL DEFAULT NULL COMMENT '处理完成时间' AFTER `begin_time`;
ALTER TABLE `ddo_repeat_msg_record`
CHANGE COLUMN `ddo_request_id` `ddo_msg_id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '请求id' AFTER `return_msg_code`;
ALTER TABLE `ddo_channel_request`
MODIFY COLUMN `begin_time`  datetime NULL COMMENT '开始处理时间' AFTER `return_state`;
ALTER TABLE `ddo_channel_request`
MODIFY COLUMN `process_result`  smallint(6) NULL COMMENT '处理结果' AFTER `source_type`;