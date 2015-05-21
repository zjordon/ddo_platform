ALTER TABLE `ddo_bill_report`
MODIFY COLUMN `transation_id`  varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '事务id' AFTER `id`;