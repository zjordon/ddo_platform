update ddo_msg set bill_state_code = '-1' where bill_state_code is null;
ALTER TABLE `ddo_msg`
MODIFY COLUMN `bill_state_code`  varchar(8) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '-1' COMMENT '计费状态编码' AFTER `send_time`;
INSERT INTO `` (`ID`, `typecode`, `typename`, `typepid`, `typegroupid`) VALUES ('402848814d749ed0014d7517abbe000b', '-1', '未返', NULL, '402848814ce57629014ce5948a2a000d');