ALTER TABLE `ddo_bill_business`
ADD COLUMN `channel_bill_code`  varchar(12) NOT NULL COMMENT '渠道计费业务代码' AFTER `state`;

ALTER TABLE `ddo_channel_business`
DROP COLUMN `code`;

ALTER TABLE `ddo_channel_business`
MODIFY COLUMN `instruct`  varchar(24) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '产品指令' AFTER `id`;

