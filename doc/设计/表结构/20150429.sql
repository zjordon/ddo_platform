--
-- ER/Studio 8.0 SQL Code Generation
-- Company :      Lenovo
-- Project :      统计模型.dm1
-- Author :       jason
--
-- Date Created : Wednesday, April 29, 2015 11:23:12
-- Target DBMS : MySQL 5.x
--

-- 
-- TABLE: ddo_exist_channel_msisdn 
--

CREATE TABLE ddo_exist_channel_msisdn(
    msisdn        BIGINT         NOT NULL COMMENT '手机号码',
    channel_id    VARCHAR(32)    NOT NULL COMMENT '渠道id',
    sum_date      INT            NOT NULL COMMENT '统计日期',
    PRIMARY KEY (msisdn, channel_id, sum_date)
)ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='已统计渠道号码'
;

-- 
-- TABLE: ddo_exist_full_msisdn 
--

CREATE TABLE ddo_exist_full_msisdn(
    msisdn      BIGINT    NOT NULL COMMENT '手机号码',
    sum_date    INT       NOT NULL COMMENT '统计日期',
    PRIMARY KEY (msisdn, sum_date)
)ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='已统计全量号码'
;

