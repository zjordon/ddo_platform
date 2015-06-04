ALTER TABLE `ddo_msg`
ADD INDEX `idx_msg_cd` USING BTREE (`create_date`) ,
ADD INDEX `idx_msg_tr` USING BTREE (`transation_id`) ,
ADD INDEX `idx_msg_msisdn` USING BTREE (`msisdn`) ;
ALTER TABLE `ddo_send_record`
ADD INDEX `idx_dsr_msisdn` USING BTREE (`msisdn`) ;