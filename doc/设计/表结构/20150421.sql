ALTER TABLE `ddo_sm_task`
ADD COLUMN `channel_user_name`  varchar(32) NULL COMMENT '渠道用户名' AFTER `bill_business_id`,
ADD COLUMN `channel_user_pass`  varchar(32) NULL COMMENT '渠道用户密码' AFTER `channel_user_name`;