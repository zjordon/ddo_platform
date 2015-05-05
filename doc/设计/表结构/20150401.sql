ALTER TABLE `ddo_event`
ADD COLUMN `process_result`  tinyint NULL COMMENT '处理结果' AFTER `end_time`,
ADD COLUMN `fail_msg`  varchar(256) NULL COMMENT '失败原因' AFTER `process_result`;