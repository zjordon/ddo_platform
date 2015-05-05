--
-- ER/Studio 8.0 SQL Code Generation
-- Company :      Lenovo
-- Project :      风险预警模型.dm1
-- Author :       jason
--
-- Date Created : Monday, May 04, 2015 09:06:01
-- Target DBMS : MySQL 5.x
--

-- 
-- TABLE: ddo_channel_complaint_day 
--

CREATE TABLE ddo_channel_complaint_day(
    id            VARCHAR(32)    NOT NULL COMMENT '唯一标识',
    channel_id    VARCHAR(32)    NOT NULL COMMENT '渠道id',
    sum_date      INT COMMENT '统计日期',
    num           INT COMMENT '投诉数',
    PRIMARY KEY (id)
)ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='渠道投诉数'
;

-- 
-- TABLE: ddo_full_complaint_day 
--

CREATE TABLE ddo_full_complaint_day(
    id          VARCHAR(32)    NOT NULL COMMENT '唯一标识',
    sum_date    INT COMMENT '统计日期',
    num         INT COMMENT '投诉数',
    PRIMARY KEY (id)
)ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='全量投诉数'
;
ALTER TABLE `ddo_provice_close_state`
MODIFY COLUMN `provice_code`  varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '省份编码' AFTER `id`,
MODIFY COLUMN `close_state`  decimal(1,0) NOT NULL COMMENT '关停状态' AFTER `provice_code`,
ADD COLUMN `provice_pinyin`  varchar(32) NOT NULL COMMENT '省份的拼音名称' AFTER `close_state`;
ALTER TABLE `ddo_provice_close_state`
MODIFY COLUMN `close_state`  tinyint(1) NOT NULL COMMENT '关停状态' AFTER `provice_code`;
insert into ddo_provice_close_state(id, provice_code, close_state, provice_pinyin)
select t.id, t.territorycode, 0, t.territory_pinyin from t_s_territory t where t.territorylevel = 1 order by t.territory_pinyin;
