truncate table ddo_channel_statistics_month;
truncate table ddo_exist_cmsisdn_month;
truncate table ddo_full_statistics_month;
truncate table ddo_exist_fmsisdn_month;
truncate table ddo_province_statistics_month;
truncate table ddo_exist_pmsisdn_month;
update ddo_send_record set state = 0;
update ddo_send_result_record set state = 0;
update ddo_bill_result_record set state = 0;