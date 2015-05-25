--
-- ER/Studio 8.0 SQL Code Generation
-- Company :      Lenovo
-- Project :      Model1.DM1
-- Author :       jason
--
-- Date Created : Friday, May 22, 2015 14:27:25
-- Target DBMS : MySQL 5.x
--

-- 
-- TABLE: ddo_consume_record 
--

CREATE TABLE ddo_consume_record(
    sum_month     INT       NOT NULL,
    sum_amount    INT       NOT NULL,
    num           INT       NOT NULL,
    msisdn        BIGINT
)ENGINE=INNODB DEFAULT CHARSET=utf8
COMMENT='消费记录'
;

-- 
-- TABLE: ddo_consume_turnover 
--

CREATE TABLE ddo_consume_turnover(
    id             VARCHAR(32)    NOT NULL,
    msisdn         BIGINT,
    amount         INT,
    record_date    INT,
    state          TINYINT        NOT NULL,
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8
COMMENT='消费流水记录'
;

