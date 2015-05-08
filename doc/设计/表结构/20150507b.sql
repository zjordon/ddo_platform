--
-- ER/Studio 8.0 SQL Code Generation
-- Company :      Lenovo
-- Project :      统计模型.dm1
-- Author :       jason
--
-- Date Created : Thursday, May 07, 2015 15:21:19
-- Target DBMS : MySQL 5.x
--

-- 
-- TABLE: ddo_channel_statistics_month 
--

CREATE TABLE ddo_channel_statistics_month(
    id                  VARCHAR(32)      NOT NULL,
    sum_month           INT              NOT NULL,
    channel_id          VARCHAR(32)      NOT NULL,
    msisdn_num          INT              NOT NULL,
    sum_amount          DOUBLE(18, 2)    NOT NULL,
    msg_num             INT              NOT NULL,
    send_success_num    INT              NOT NULL,
    send_fail_num       INT              NOT NULL,
    bill_success_num    INT              NOT NULL,
    bill_fail_num       INT,
    PRIMARY KEY (id)
)ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='分渠道统计按月'
;

-- 
-- TABLE: ddo_exist_cmsisdn_month 
--

CREATE TABLE ddo_exist_cmsisdn_month(
    msisdn        BIGINT         NOT NULL,
    channel_id    VARCHAR(32)    NOT NULL,
    sum_month     INT            NOT NULL,
    PRIMARY KEY (msisdn, channel_id, sum_month)
)ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='已统计渠道号码按月'
;

