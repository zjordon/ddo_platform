ALTER TABLE `ddo_channel`
MODIFY COLUMN `day_limit`  bigint(17) NOT NULL COMMENT '日限额' AFTER `state`,
MODIFY COLUMN `month_limit`  bigint(17) NOT NULL COMMENT '月限额' AFTER `day_limit`;