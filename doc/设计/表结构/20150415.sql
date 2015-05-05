ALTER TABLE `ddo_event`
MODIFY COLUMN `param`  varchar(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '参数' AFTER `create_date`;

