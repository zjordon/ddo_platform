--
-- ER/Studio 8.0 SQL Code Generation
-- Company :      Lenovo
-- Project :      统计模型.dm1
-- Author :       jason
--
-- Date Created : Thursday, April 23, 2015 14:59:37
-- Target DBMS : MySQL 5.x
--

-- 
-- TABLE: ddo_bill_result_record 
--

CREATE TABLE ddo_bill_result_record(
    id             VARCHAR(32)    NOT NULL COMMENT '唯一标识',
    ddo_msg_id     VARCHAR(32) COMMENT 'ddo消息id',
    bill_result    TINYINT COMMENT '计费结果',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='计费结果'
;

-- 
-- TABLE: ddo_channel_statistics_day 
--

CREATE TABLE ddo_channel_statistics_day(
    id                  VARCHAR(32)      NOT NULL COMMENT '唯一标识',
    sum_date            INT              NOT NULL COMMENT '统计日期',
    channel_id          VARCHAR(32)      NOT NULL COMMENT '渠道id',
    msisdn_num          INT              NOT NULL COMMENT '用户数',
    sum_amount          DOUBLE(18, 0)    NOT NULL COMMENT '计费金额',
    msg_num             INT              NOT NULL COMMENT '短信条数',
    send_success_num    INT              NOT NULL COMMENT '发送成功数',
    send_fail_num       INT              NOT NULL COMMENT '发送失败数',
    bill_success_num    INT              NOT NULL COMMENT '计费成功数',
    bill_fail_num       INT NOT NULL COMMENT '计费失败数',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='分渠道统计'
;

-- 
-- TABLE: ddo_full_statistics_day 
--

CREATE TABLE ddo_full_statistics_day(
    id                  VARCHAR(32)      NOT NULL COMMENT '唯一标识',
    sum_date            INT              NOT NULL COMMENT '统计日期',
   msisdn_num          INT              NOT NULL COMMENT '用户数',
    sum_amount          DOUBLE(18, 0)    NOT NULL COMMENT '计费金额',
    msg_num             INT              NOT NULL COMMENT '短信条数',
    send_success_num    INT              NOT NULL COMMENT '发送成功数',
    send_fail_num       INT              NOT NULL COMMENT '发送失败数',
    bill_success_num    INT              NOT NULL COMMENT '计费成功数',
    bill_fail_num       INT NOT NULL COMMENT '计费失败数',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='全量统计'
;

-- 
-- TABLE: ddo_send_record 
--

CREATE TABLE ddo_send_record(
    id                     VARCHAR(32)    NOT NULL COMMENT '唯一标识',
    ddo_msg_id             VARCHAR(32)    NOT NULL COMMENT 'ddo消息id',
    msisdn                 BIGINT         NOT NULL COMMENT '手机号码',
    channel_id             VARCHAR(32)    NOT NULL COMMENT '渠道id',
    billing_business_id    VARCHAR(32) NOT NULL COMMENT '计费业务id',
    send_date              INT NOT NULL COMMENT '发送日期',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='发送记录'
;

-- 
-- TABLE: ddo_send_result_record 
--

CREATE TABLE ddo_send_result_record(
    id             VARCHAR(32)    NOT NULL COMMENT '唯一标识',
    ddo_msg_id     VARCHAR(32)    NOT NULL COMMENT 'ddo消息id',
    send_result    TINYINT COMMENT '发送结果',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='发送结果'
;

