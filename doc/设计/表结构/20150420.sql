-- 
-- TABLE: ddo_sm_msisdn_list 
--

CREATE TABLE ddo_sm_msisdn_list(
    id               VARCHAR(32)    NOT NULL COMMENT '唯一标识',
    msisdn           BIGINT COMMENT '手机号码',
    sm_request_id    VARCHAR(32)    NOT NULL COMMENT '请求id',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='号码清单'
;

-- 
-- TABLE: ddo_sm_request 
--

CREATE TABLE ddo_sm_request(
    id                VARCHAR(32)    NOT NULL COMMENT '唯一标识',
    response_no       VARCHAR(32) COMMENT '返回请求编号',
    response_state    INT COMMENT '返回状态',
    response_time     DATETIME COMMENT '请求返回时间',
    send_time         DATETIME COMMENT '发送时间',
    sm_task_id        VARCHAR(32)    NOT NULL COMMENT '任务id',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='短信请求'
;

-- 
-- TABLE: ddo_sm_task 
--

CREATE TABLE ddo_sm_task(
    id                   VARCHAR(32)    NOT NULL COMMENT '唯一标识',
    msisdn_num           INT COMMENT '号码数量',
    recapture            BIT(1) COMMENT '是否去重',
    send_type            SMALLINT       NOT NULL COMMENT '发送类型',
    time_to_send_time    DATETIME COMMENT '定时发送时间',
    state                SMALLINT       NOT NULL COMMENT '状态',
    execute_time         DATETIME COMMENT '执行时间',
    create_date          DATETIME COMMENT '创建时间',
    create_name          VARCHAR(32) COMMENT '创建人',
    channel_id           VARCHAR(32)    NOT NULL COMMENT '渠道id',
    bill_business_id     VARCHAR(32)    NOT NULL COMMENT '计费业务id',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='短信任务'
;

-- 
-- TABLE: ddo_sm_msisdn_list 
--

ALTER TABLE ddo_sm_msisdn_list ADD CONSTRAINT Refddo_sm_request20 
    FOREIGN KEY (sm_request_id)
    REFERENCES ddo_sm_request(id)
;


-- 
-- TABLE: ddo_sm_request 
--

ALTER TABLE ddo_sm_request ADD CONSTRAINT Refddo_sm_task19 
    FOREIGN KEY (sm_task_id)
    REFERENCES ddo_sm_task(id)
;


-- 
-- TABLE: ddo_sm_task 
--

ALTER TABLE ddo_sm_task ADD CONSTRAINT Refddo_channel16 
    FOREIGN KEY (channel_id)
    REFERENCES ddo_channel(id)
;

ALTER TABLE ddo_sm_task ADD CONSTRAINT Refddo_bill_business17 
    FOREIGN KEY (bill_business_id)
    REFERENCES ddo_bill_business(id)
;

ALTER TABLE `ddo_sm_task`
MODIFY COLUMN `recapture`  tinyint(1) NULL DEFAULT NULL COMMENT '是否去重' AFTER `msisdn_num`;

DROP TABLE ddo_sm_msisdn_list
;
-- 
-- TABLE: ddo_sm_msisdn_list 
--
CREATE TABLE ddo_sm_msisdn_list(
    id               VARCHAR(32)    NOT NULL COMMENT '唯一标识',
    msisdn           BIGINT COMMENT '手机号码',
	 sm_task_id      VARCHAR(32)    NOT NULL COMMENT '任务id',
    sm_request_id    VARCHAR(32) COMMENT '请求id',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='号码清单';

-- 
-- TABLE: ddo_sm_msisdn_list 
--

ALTER TABLE ddo_sm_msisdn_list ADD CONSTRAINT Refddo_sm_task21 
    FOREIGN KEY (sm_task_id)
    REFERENCES ddo_sm_task(id)
;

ALTER TABLE ddo_sm_msisdn_list ADD CONSTRAINT Refddo_sm_request22 
    FOREIGN KEY (sm_request_id)
    REFERENCES ddo_sm_request(id)
;
ALTER TABLE `ddo_sm_task`
MODIFY COLUMN `state`  smallint(6) NOT NULL DEFAULT 0 COMMENT '状态' AFTER `time_to_send_time`;

