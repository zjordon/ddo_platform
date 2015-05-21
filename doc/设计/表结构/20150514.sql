ALTER TABLE `ddo_province_statistics_month`
MODIFY COLUMN `sum_amount`  double(18,2) NOT NULL COMMENT '计费金额' AFTER `msisdn_num`;