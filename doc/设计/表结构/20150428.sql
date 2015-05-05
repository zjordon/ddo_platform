ALTER TABLE `ddo_sm_task`
ADD COLUMN `fail_msg`  varchar(512) NULL COMMENT '失败信息' AFTER `channel_user_pass`;
ALTER TABLE `ddo_send_result_record`
ADD INDEX `index_dsrr_msgid` USING BTREE (`ddo_msg_id`) ;