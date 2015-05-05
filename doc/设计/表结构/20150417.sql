ALTER TABLE `ddo_bill_business`
MODIFY COLUMN `state`  smallint(1) NOT NULL COMMENT '状态' AFTER `code`;
ALTER TABLE `ddo_bill_business`
MODIFY COLUMN `price`  bigint(17) NULL DEFAULT NULL COMMENT '计费价格' AFTER `name`;
ALTER TABLE `ddo_channel_business`
MODIFY COLUMN `state`  smallint(1) NULL DEFAULT NULL COMMENT '状态' AFTER `instruct`,
MODIFY COLUMN `close_state`  smallint(1) NULL DEFAULT NULL COMMENT '关停状态' AFTER `state`;
