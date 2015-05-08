--
-- ER/Studio 8.0 SQL Code Generation
-- Company :      Lenovo
-- Project :      统计模型.dm1
-- Author :       jason
--
-- Date Created : Friday, May 08, 2015 08:44:51
-- Target DBMS : MySQL 5.x
--

-- 
-- TABLE: ddo_exist_fmsisdn_month 
--

CREATE TABLE ddo_exist_fmsisdn_month(
    msisdn       BIGINT    NOT NULL,
    sum_month    INT       NOT NULL,
    PRIMARY KEY (msisdn, sum_month)
)ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='已统计全量号码'
;

-- 
-- TABLE: ddo_full_statistics_month 
--

CREATE TABLE ddo_full_statistics_month(
    id                  VARCHAR(32)      NOT NULL,
    sum_month           INT              NOT NULL,
    msisdn_num          INT              NOT NULL,
    sum_amount          DOUBLE(18, 2)    NOT NULL,
    msg_num             INT              NOT NULL,
    send_success_num    INT              NOT NULL,
    send_fail_num       INT              NOT NULL,
    bill_success_num    INT              NOT NULL,
    bill_fail_num       INT,
    PRIMARY KEY (id)
)ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='全量统计'
;
update t_s_function t set t.functionurl = 'fullStatisticsMonthController.do?fullStatisticsMonth' where id = '402848814ce57137014ce57365d20003';
update t_s_function t set t.functionurl = 'channelStatisticsMonthController.do?channelStatisticsMonth' where id = '402848814ce57137014ce573a3e80005';

--
-- ER/Studio 8.0 SQL Code Generation
-- Company :      Lenovo
-- Project :      风险预警模型.dm1
-- Author :       jason
--
-- Date Created : Friday, May 08, 2015 16:25:14
-- Target DBMS : MySQL 5.x
--

-- 
-- TABLE: ddo_full_complaint_month 
--

CREATE TABLE ddo_full_complaint_month(
    id           VARCHAR(32)    NOT NULL,
    sum_month    INT,
    num          INT,
    PRIMARY KEY (id)
)ENGINE=INNODB
COMMENT='全量投诉数(按月)'
;
create view ddo_f_complaint_month_view as
SELECT
	`t1`.`id` AS `id`,
	`t1`.`sum_amount` AS `sum_amount`,
	`t1`.`sum_month` AS `sum_month`,
	`t1`.`msisdn_num` AS `msisdn_num`,
	(
		CASE
		WHEN (`t2`.`num` IS NOT NULL) THEN
			`t2`.`num`
		ELSE
			0
		END
	) AS `num`,
	`t2`.`id` AS `full_complaint_id`,
	(
		CASE
		WHEN (`t2`.`num` IS NOT NULL) THEN
			round(
				(
					(
						`t2`.`num` / `t1`.`msisdn_num`
					) * 10000
				),
				2
			)
		ELSE
			0
		END
	) AS `scale`
FROM
	(
		`ddo_full_statistics_month` `t1`
		LEFT JOIN `ddo_full_complaint_month` `t2` ON (
			(
				`t1`.`sum_month` = `t2`.`sum_month`
			)
		)
	)
--
-- ER/Studio 8.0 SQL Code Generation
-- Company :      Lenovo
-- Project :      风险预警模型.dm1
-- Author :       jason
--
-- Date Created : Friday, May 08, 2015 16:41:20
-- Target DBMS : MySQL 5.x
--

-- 
-- TABLE: ddo_channel_complaint_month 
--

CREATE TABLE ddo_channel_complaint_month(
    id            VARCHAR(32)    NOT NULL,
    channel_id    VARCHAR(32)    NOT NULL,
    sum_month     INT,
    num           INT,
    PRIMARY KEY (id)
)ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_unicode_ci
COMMENT='渠道投诉数(按月)'
;
create view ddo_c_complaint_month_view as
SELECT
	`t1`.`id` AS `id`,
	`t1`.`channel_id` AS `channel_id`,
	`t1`.`sum_amount` AS `sum_amount`,
	`t1`.`sum_month` AS `sum_month`,
	`t1`.`msisdn_num` AS `msisdn_num`,
	(
		CASE
		WHEN (`t2`.`num` IS NOT NULL) THEN
			`t2`.`num`
		ELSE
			0
		END
	) AS `num`,
	`t2`.`id` AS `channel_complaint_id`,
	(
		CASE
		WHEN (`t2`.`num` IS NOT NULL) THEN
			round(
				(
					(
						`t2`.`num` / `t1`.`msisdn_num`
					) * 10000
				),
				2
			)
		ELSE
			0
		END
	) AS `scale`
FROM
	(
		`ddo_channel_statistics_month` `t1`
		LEFT JOIN `ddo_channel_complaint_month` `t2` ON (
			(
				(
					`t1`.`channel_id` = `t2`.`channel_id`
				)
				AND (
					`t1`.`sum_month` = `t2`.`sum_month`
				)
			)
		)
	)
update t_s_function t set t.functionurl = 'cComplaintMonthViewController.do?cComplaintMonthView' where id = '402848814d1c8e30014d1c9122b10003';
update t_s_function t set t.functionurl = 'fComplaintMonthViewController.do?fComplaintMonthView' where id = '402848814d1c8e30014d1c9191020005';