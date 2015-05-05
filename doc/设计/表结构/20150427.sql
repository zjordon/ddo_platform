ALTER TABLE `ddo_full_statistics_day`
MODIFY COLUMN `sum_amount`  double(18,2) NOT NULL COMMENT '计费金额' AFTER `msisdn_num`;

