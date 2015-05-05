ALTER TABLE `ddo_msg`
MODIFY COLUMN `transation_id`  varchar(24) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '事务ID' AFTER `return_msg_code`;
CREATE TABLE dm_mobile(
    id               VARCHAR(32)      NOT NULL,
    mobilenumber     DECIMAL(7, 0),
    mobilearea       VARCHAR(50),
    mobiletype       VARCHAR(50),
    areacode         VARCHAR(10),
    postcode         VARCHAR(50),
    province_code    VARCHAR(10),
    city_code        VARCHAR(10),
    type             DECIMAL(1, 0),
    PRIMARY KEY (id)
)ENGINE=INNODB
COMMENT='手机号码归属地'
;
ALTER TABLE `dm_mobile`
ADD COLUMN `province_name`  varchar(24) NULL AFTER `type`,
ADD COLUMN `city_name`  varchar(48) NULL AFTER `province_name`;