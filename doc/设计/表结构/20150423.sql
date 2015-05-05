ALTER TABLE `ddo_msg`
MODIFY COLUMN `msisdn`  bigint(11) NOT NULL COMMENT '用户手机号码' AFTER `id`;