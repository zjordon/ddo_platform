INSERT INTO `` (`ID`, `functioniframe`, `functionlevel`, `functionname`, `functionorder`, `functionurl`, `parentfunctionid`, `iconid`, `desk_iconid`, `functiontype`) VALUES ('402848814dbd5eba014dbd6148dc0001', NULL, 1, '投诉记录', '6', 'complaintRecordController.do?complaintRecord', '402848814d1c8e30014d1c904bb80001', '8a8ab0b246dc81120146dc8180460000', '8a8ab0b246dc81120146dc8180dd001e', 0);
ALTER TABLE `ddo_complaint_record`
MODIFY COLUMN `msisdn`  bigint(20) NULL DEFAULT NULL COMMENT '用户手机号' AFTER `complaint_month`;
CREATE OR REPLACE VIEW ddo_c_s_m_inner_view as
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
	) AS `scale`,
  (case when (t2.num_threshold is null) then 0 else t2.num_threshold end) as num_threshold,
  (case when (t2.ratio_threshold is null) then 0 else t2.ratio_threshold end) as ratio_threshold
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
	);
create or replace view ddo_c_complaint_month_view as
select dct.*, ch.`name` as channel_name, ch.state from
ddo_c_s_m_inner_view dct, ddo_channel ch where dct.channel_id = ch.id;