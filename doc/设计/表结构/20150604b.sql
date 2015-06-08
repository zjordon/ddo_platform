ALTER TABLE `ddo_full_complaint_month`
ADD COLUMN `num_threshold`  int(11) NULL COMMENT '投诉数阀值' AFTER `num`,
ADD COLUMN `ratio_threshold`  double(11,2) NULL COMMENT '万投比阀值' AFTER `num_threshold`;
ALTER TABLE `ddo_channel_complaint_month`
ADD COLUMN `num_threshold`  int(11) NULL COMMENT '投诉数阀值' AFTER `num`,
ADD COLUMN `ratio_threshold`  double(11,2) NULL COMMENT '万投比阀值' AFTER `num_threshold`;

--
-- ER/Studio 8.0 SQL Code Generation
-- Company :      Lenovo
-- Project :      风险预警模型.dm1
-- Author :       jason
--
-- Date Created : Thursday, June 04, 2015 14:42:29
-- Target DBMS : MySQL 5.x
--

-- 
-- TABLE: ddo_complaint_record 
--

CREATE TABLE ddo_complaint_record(
    id                 VARCHAR(32)    NOT NULL COMMENT '唯一标识',
    complaint_date     INT COMMENT '投诉日期',
    complaint_month    INT            NOT NULL COMMENT '投诉月份',
    msisdn             INT COMMENT '用户手机号',
    provice            VARCHAR(64) COMMENT '省份',
    city               VARCHAR(64) COMMENT '地市',
    create_date        DATETIME COMMENT '创建时间',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='投诉记录'
;

-- 
-- TABLE: ddo_provice_complaint_month 
--

CREATE TABLE ddo_provice_complaint_month(
    id                 VARCHAR(32)      NOT NULL COMMENT '唯一标识',
    provice_code         VARCHAR(32)      NOT NULL COMMENT '省份编码',
    sum_month          INT COMMENT '统计月份',
    num                INT COMMENT '投诉数',
    num_threshold      INT COMMENT '投诉数阀值',
    ratio_threshold    DOUBLE(18, 0) COMMENT '万投比阀值',
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='分省投诉数(按月)'
;

