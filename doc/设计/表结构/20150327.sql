--
-- ER/Studio 8.0 SQL Code Generation
-- Company :      Lenovo
-- Project :      Model1.DM1
-- Author :       jason
--
-- Date Created : Friday, March 27, 2015 16:12:00
-- Target DBMS : MySQL 5.x
--

-- 
-- TABLE: ddo_bill_business 
--

CREATE TABLE ddo_bill_business(
    id       VARCHAR(32)       NOT NULL COMMENT '唯一标识',
    name     VARCHAR(64)       NOT NULL COMMENT '计费点名称',
    price    DECIMAL(17, 0) COMMENT '计费价格',
    code     VARCHAR(12)       NOT NULL COMMENT '计费业务代码',
    state    DECIMAL(1, 0)     NOT NULL COMMENT '状态',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='计费业务'
;

-- 
-- TABLE: ddo_bill_report 
--

CREATE TABLE ddo_bill_report(
    id                 VARCHAR(32)    NOT NULL COMMENT '唯一标识',
    transation_id      VARCHAR(18) COMMENT '事务id',
    bill_state_code    VARCHAR(8) COMMENT '计费状态编码',
    state              TINYINT        NOT NULL COMMENT '状态',
    process_result     TINYINT COMMENT '处理结果',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='计费状态报告'
;

-- 
-- TABLE: ddo_black_list 
--

CREATE TABLE ddo_black_list(
    id             VARCHAR(32)       NOT NULL COMMENT '唯一标识',
    msisdn         DECIMAL(11, 0)    NOT NULL COMMENT '手机号码',
    state          DECIMAL(1, 0)     DEFAULT 1 NOT NULL COMMENT '状态',
    create_date    DATETIME          NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='黑名单'
;

-- 
-- TABLE: ddo_channel 
--

CREATE TABLE ddo_channel(
    id             VARCHAR(32)       NOT NULL COMMENT '唯一标识',
    name           VARCHAR(64)       NOT NULL COMMENT '渠道名称',
    no             DECIMAL(11, 0)    NOT NULL COMMENT '渠道编号',
    state          TINYINT           DEFAULT 1 NOT NULL COMMENT '状态',
    day_limit      DECIMAL(17, 0)    NOT NULL COMMENT '日限额',
    month_limit    DECIMAL(17, 0)    NOT NULL COMMENT '月限额',
    close_state    TINYINT COMMENT '关停状态',
    up_url         VARCHAR(256) COMMENT '上行地址',
    down_url       VARCHAR(256) COMMENT '下行地址',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='渠道'
;

-- 
-- TABLE: ddo_channel_business 
--

CREATE TABLE ddo_channel_business(
    id                  VARCHAR(32)      NOT NULL COMMENT '唯一标识',
    code                VARCHAR(12)      NOT NULL COMMENT '产品编码',
    instruct            VARCHAR(12)      NOT NULL COMMENT '产品指令',
    state               DECIMAL(1, 0) COMMENT '状态',
    close_state         DECIMAL(1, 0) COMMENT '关停状态',
    bill_business_id    VARCHAR(32)      NOT NULL COMMENT '计费业务id',
    channel_id          VARCHAR(32)      NOT NULL COMMENT '渠道id',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='渠道业务'
;

-- 
-- TABLE: ddo_channel_day_limit 
--

CREATE TABLE ddo_channel_day_limit(
    id            VARCHAR(32)       NOT NULL COMMENT '唯一标识',
    day           INT COMMENT '日期',
    limit_amount         DECIMAL(17, 0) COMMENT '限额',
    channel_id    VARCHAR(32)       NOT NULL COMMENT '渠道id',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='渠道每日限额'
;

-- 
-- TABLE: ddo_channel_month_limit 
--

CREATE TABLE ddo_channel_month_limit(
    id            VARCHAR(32)       NOT NULL COMMENT '唯一标识',
    month           INT COMMENT '月份',
    limit_amount         DECIMAL(17, 0) COMMENT '限额',
    channel_id    VARCHAR(32)       NOT NULL COMMENT '渠道id',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='渠道每月限额'
;

-- 
-- TABLE: ddo_channel_request 
--

CREATE TABLE ddo_channel_request(
    id                VARCHAR(32)    NOT NULL COMMENT '唯一标识',
    username          VARCHAR(32)    NOT NULL COMMENT '用户名',
    password          VARCHAR(32)    NOT NULL COMMENT '密码',
    content           VARCHAR(32) COMMENT '发送指令',
    product_id        VARCHAR(32) COMMENT '产品id',
    dstime            DATETIME COMMENT '定时时间',
    request_time      DATETIME COMMENT '请求时间',
    state             SMALLINT       NOT NULL COMMENT '状态',
    return_state      SMALLINT       NOT NULL COMMENT '返回状态',
    begin_time        DATETIME       NOT NULL COMMENT '开始处理时间',
    end_time          CHAR(10) COMMENT '处理完成时间',
    channel_id        VARCHAR(32) COMMENT '渠道id',
    source_type       SMALLINT       NOT NULL COMMENT '来源类型',
    process_result    SMALLINT       NOT NULL COMMENT '处理结果',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='外部请求'
;

-- 
-- TABLE: ddo_channel_user 
--

CREATE TABLE ddo_channel_user(
    id            VARCHAR(32)       NOT NULL COMMENT '唯一标识',
    username      VARCHAR(32)       NOT NULL COMMENT '用户名',
    password      VARCHAR(32)       NOT NULL COMMENT '密码',
    msisdn        DECIMAL(11, 0) COMMENT '手机号',
    state         TINYINT COMMENT '状态',
    channel_id    VARCHAR(32)       NOT NULL COMMENT '渠道id',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='渠道用户'
;

-- 
-- TABLE: ddo_event 
--

CREATE TABLE ddo_event(
    id             VARCHAR(32)     NOT NULL COMMENT '唯一标识',
    event_id       VARCHAR(64) COMMENT '事件ID',
    create_date    DATETIME COMMENT '创建时间',
    param          VARCHAR(128) COMMENT '参数',
    begin_time     DATETIME COMMENT '开始处理时间',
    end_time       DATETIME COMMENT '处理结束时间',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='事件'
;

-- 
-- TABLE: ddo_limit_use_log 
--

CREATE TABLE ddo_limit_use_log(
    id                     VARCHAR(32)       NOT NULL COMMENT '唯一标识',
    use_amount             DECIMAL(17, 0) COMMENT '本次使用的金额',
    remain_day_amount      DECIMAL(17, 0) COMMENT '使用后剩余的日金额',
    remain_month_amount    DECIMAL(17, 0) COMMENT '使用后剩余的月金额',
    create_date            DATETIME COMMENT '创建时间',
    request_id             VARCHAR(32) COMMENT '请求id',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='渠道限额使用流水'
;

-- 
-- TABLE: ddo_msg 
--

CREATE TABLE ddo_msg(
    id                      VARCHAR(32)       NOT NULL COMMENT '唯一标识',
    msisdn                  DECIMAL(11, 0)    NOT NULL COMMENT '用户手机号码',
    billing_business_id     VARCHAR(32)       NOT NULL COMMENT '计费业务ID',
    return_msg_code         VARCHAR(8) COMMENT '返回消息编码',
    transation_id           VARCHAR(18) COMMENT '事务ID',
    send_time               DATETIME COMMENT '发送时间',
    bill_state_code         VARCHAR(8) COMMENT '计费状态编码',
    bill_state_time         DATETIME COMMENT '计费状态返回时间',
    send_result             TINYINT           NOT NULL COMMENT '发送结果',
    msisdn_province_code    VARCHAR(8) COMMENT '手机号码归属省份编码',
    msisdn_city_code        VARCHAR(8) COMMENT '手机号码归属地市编码',
    channel_id              VARCHAR(32) COMMENT '渠道ID',
    request_id              VARCHAR(32)       NOT NULL COMMENT '请求id',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='DDO消息'
;

-- 
-- TABLE: ddo_need_repeat_msg 
--

CREATE TABLE ddo_need_repeat_msg(
    id               VARCHAR(32)    NOT NULL COMMENT '唯一标识',
    repeat_reason    VARCHAR(32) COMMENT '重发原因',
    create_date      DATETIME COMMENT '创建时间',
    ddo_msg_id       VARCHAR(32) COMMENT '消息id',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='需要重发的消息'
;

-- 
-- TABLE: ddo_need_up_channel 
--

CREATE TABLE ddo_need_up_channel(
    id               VARCHAR(32)    NOT NULL COMMENT '唯一标识',
    create_date      DATETIME COMMENT '创建时间',
    repeat_reason    VARCHAR(32) COMMENT '重发原因',
    record_id        VARCHAR(32) COMMENT '重发记录id',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='需要重新上行的记录'
;

-- 
-- TABLE: ddo_provice_close_state 
--

CREATE TABLE ddo_provice_close_state(
    id              VARCHAR(32)      NOT NULL COMMENT '唯一标识',
    provice_code    VARCHAR(10) COMMENT '省份编码',
    close_state     DECIMAL(1, 0) COMMENT '关停状态',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='省份关停状态'
;

-- 
-- TABLE: ddo_repeat_msg_record 
--

CREATE TABLE ddo_repeat_msg_record(
    id                 VARCHAR(32)    NOT NULL COMMENT '唯一标识',
    repeat_reason      VARCHAR(32) COMMENT '重发原因',
    create_date        DATETIME COMMENT '创建时间',
    repeat_time        DATETIME COMMENT '重发时间',
    return_msg_code    VARCHAR(8) COMMENT '返回消息编码',
    ddo_request_id     VARCHAR(32)    NOT NULL COMMENT '请求id',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='消息的重发记录'
;

-- 
-- TABLE: ddo_repeat_up_record 
--

CREATE TABLE ddo_repeat_up_record(
    id               VARCHAR(32)    NOT NULL COMMENT '唯一标识',
    create_date      DATETIME COMMENT '创建时间',
    repeat_reason    VARCHAR(64) COMMENT '重发原因',
    repeat_time      DATETIME COMMENT '重发时间',
    result_code      VARCHAR(32) COMMENT '上行结果编码',
    record_id        VARCHAR(32)    NOT NULL COMMENT '重发记录id',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='重新上行记录'
;

-- 
-- TABLE: ddo_request_msisdn 
--

CREATE TABLE ddo_request_msisdn(
    id            VARCHAR(32)       NOT NULL COMMENT '唯一标识',
    msisdn        DECIMAL(11, 0) COMMENT '手机号',
    request_id    VARCHAR(32)       NOT NULL COMMENT '请求id',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='外部请求对应的手机号'
;

-- 
-- TABLE: ddo_up_channel_record 
--

CREATE TABLE ddo_up_channel_record(
    id                VARCHAR(32)    NOT NULL COMMENT '唯一标识',
    create_date       DATETIME COMMENT '创建时间',
    result_code       VARCHAR(32) COMMENT '上行结果编码',
    process_result    TINYINT COMMENT '上行处理结果',
    ddo_msg_id        VARCHAR(32) COMMENT '消息id',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='上行渠道记录'
;


