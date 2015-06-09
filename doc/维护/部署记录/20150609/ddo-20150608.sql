ALTER TABLE `ddo_channel`
ADD COLUMN `post_url`  varchar(128) NULL COMMENT '对应的ddo接收地址' AFTER `down_url`;