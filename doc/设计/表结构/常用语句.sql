insert into ddo_send_record(id, ddo_msg_id, msisdn, channel_id, billing_business_id, send_date, state, msisdn_province_code)
SELECT
	REPLACE(uuid(), '-', ''),
	t.id,
	t.msisdn,
	t.channel_id,
	t.billing_business_id,
  CAST(DATE_FORMAT(t.send_time,'%Y%m%d') AS SIGNED INTEGER),
	0,
	t.msisdn_province_code
FROM
	ddo_msg t
WHERE
	t.id NOT IN (
		SELECT
			ddo_msg_id
		FROM
			ddo_send_record t
	)

insert into ddo_send_result_record(id, ddo_msg_id, send_result, state)
SELECT
	REPLACE(uuid(), '-', ''),
	t.id,
	case when t.return_msg_code = '00000000' then 1 else 0 end send_result,
	0
FROM
	ddo_msg t
WHERE
	t.id NOT IN (
		SELECT
			ddo_msg_id
		FROM
			ddo_send_result_record
	)
	
select count(*) from ddo_bill_report t where t.create_date > str_to_date('20150501', '%Y%m%d') and t.bill_state_code is not null;

select * from ddo_bill_report where  create_date > str_to_date('20150501', '%Y%m%d') order by create_date asc;

SELECT
	*
FROM
	ddo_bill_report t
WHERE
	t.create_date > str_to_date('20150501', '%Y%m%d')
AND (CONVERT(t.transation_id USING utf8) COLLATE utf8_unicode_ci) NOT IN (
	SELECT
		transation_id
	FROM
		ddo_msg
  where bill_state_code is not null