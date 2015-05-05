ALTER TABLE `ddo_send_record`
ADD COLUMN `state`  tinyint NULL COMMENT '状态' AFTER `send_date`;
ALTER TABLE `ddo_send_result_record`
ADD COLUMN `state`  tinyint NULL COMMENT '状态' AFTER `send_result`;
ALTER TABLE `ddo_bill_result_record`
ADD COLUMN `state`  tinyint NULL COMMENT '状态' AFTER `bill_result`;

ALTER TABLE ddo_send_record ADD INDEX index_dsr_msgid (ddo_msg_id);

ALTER TABLE `ddo_channel_statistics_day`
MODIFY COLUMN `sum_amount`  double(18,2) NOT NULL COMMENT '计费金额' AFTER `msisdn_num`;