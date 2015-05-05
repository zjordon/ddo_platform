ALTER TABLE `ddo_black_list` ADD UNIQUE (`msisdn`);
ALTER TABLE `ddo_black_list`
MODIFY COLUMN `state`  smallint(1) NOT NULL DEFAULT 1 COMMENT '状态' AFTER `msisdn`;