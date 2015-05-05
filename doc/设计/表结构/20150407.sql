--
-- ER/Studio 8.0 SQL Code Generation
-- Company :      Lenovo
-- Project :      Model1.DM1
-- Author :       jason
--
-- Date Created : Tuesday, April 07, 2015 16:33:30
-- Target DBMS : MySQL 5.x
--

-- 
-- TABLE: ddo_bill_report 
--
drop table ddo_bill_report;
CREATE TABLE ddo_bill_report(
    id                 VARCHAR(32)    NOT NULL COMMENT '唯一标识',
    transation_id      VARCHAR(18) COMMENT '事务id',
    bill_state_code    VARCHAR(8) COMMENT '计费状态编码',
    state              TINYINT        NOT NULL COMMENT '状态',
    process_result     TINYINT COMMENT '处理结果',
    result_code        VARCHAR(32) COMMENT '下发状态报告结果编码',
	create_date       DATETIME COMMENT '创建时间',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='计费状态报告'
;

-- 
-- TABLE: ddo_need_repeat_report 
--

CREATE TABLE ddo_need_repeat_report(
    id                VARCHAR(32)    NOT NULL COMMENT '唯一标识',
    repeat_reason     VARCHAR(32) COMMENT '重发原因',
    create_date       DATETIME COMMENT '创建时间',
    bill_report_id    VARCHAR(32) COMMENT '对应的计费状态报告id',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='需要重新下发的状态报告'
;

-- 
-- TABLE: ddo_repeat_report_record 
--

CREATE TABLE ddo_repeat_report_record(
    id                VARCHAR(32)    NOT NULL COMMENT '唯一标识',
    repeat_reason     VARCHAR(32) COMMENT '重发原因',
    create_date       DATETIME COMMENT '创建时间',
    repeat_date       DATETIME COMMENT '重发时间',
    result_code       VARCHAR(32) COMMENT '下发状态报告结果编码',
    bill_report_id    VARCHAR(32)    NOT NULL COMMENT '对应的计费状态报告id',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='重新下发状态报告记录'
;

-- 
-- TABLE: ddo_need_repeat_report 
--

ALTER TABLE ddo_need_repeat_report ADD CONSTRAINT Refddo_bill_report13 
    FOREIGN KEY (bill_report_id)
    REFERENCES ddo_bill_report(id)
;


-- 
-- TABLE: ddo_repeat_report_record 
--

ALTER TABLE ddo_repeat_report_record ADD CONSTRAINT Refddo_bill_report14 
    FOREIGN KEY (bill_report_id)
    REFERENCES ddo_bill_report(id)
;


