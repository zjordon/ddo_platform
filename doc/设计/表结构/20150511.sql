ALTER TABLE `ddo_send_record`
ADD COLUMN `msisdn_province_code`  varchar(8) NULL COMMENT '手机号码归属省份编码' AFTER `state`;

--
-- ER/Studio 8.0 SQL Code Generation
-- Company :      Lenovo
-- Project :      统计模型.dm1
-- Author :       jason
--
-- Date Created : Monday, May 11, 2015 09:35:42
-- Target DBMS : MySQL 5.x
--

-- 
-- TABLE: ddo_exist_pmsisdn_month 
--

CREATE TABLE ddo_exist_pmsisdn_month(
    msisdn           BIGINT        NOT NULL COMMENT '手机号',
    province_code    VARCHAR(8)    NOT NULL COMMENT '省份编码',
    sum_month        INT           NOT NULL COMMENT '月份',
    PRIMARY KEY (msisdn, province_code, sum_month)
)ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='已统计省份号码'
;

-- 
-- TABLE: ddo_province_statistics_month 
--

CREATE TABLE ddo_province_statistics_month(
    id                  VARCHAR(32)      NOT NULL COMMENT '唯一标识',
    sum_month           INT              NOT NULL COMMENT '统计月份',
    province_code       VARCHAR(8)       NOT NULL COMMENT '用户数',
    msisdn_num          INT              NOT NULL COMMENT '用户数',
    sum_amount          DOUBLE(18, 0)    NOT NULL COMMENT '计费金额',
    msg_num             INT              NOT NULL COMMENT '短信条数',
    send_success_num    INT              NOT NULL COMMENT '发送成功数',
    send_fail_num       INT              NOT NULL COMMENT '发送失败数',
    bill_success_num    INT              NOT NULL COMMENT '计费成功数',
    bill_fail_num       INT, COMMENT '计费失败数'
    PRIMARY KEY (id)
)ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='分省统计'
;
update t_s_function t set t.functionorder = 4 where t.id = '402848814ce57137014ce574685a0007';
INSERT INTO `t_s_function` (`ID`, `functioniframe`, `functionlevel`, `functionname`, `functionorder`, `functionurl`, `parentfunctionid`, `iconid`, `desk_iconid`, `functiontype`) VALUES ('402848814d41829e014d41850cd30001', NULL, 1, '分省统计', '3', 'provinceStatisticsMonthController.do?provinceStatisticsMonth', '402848814ce57137014ce572d7320001', '8a8ab0b246dc81120146dc8180460000', '8a8ab0b246dc81120146dc8180dd001e', 0);

