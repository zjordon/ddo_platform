ALTER TABLE `ddo_consume_record`
ADD UNIQUE INDEX `idx_ms_crecord` USING BTREE (`sum_month`, `msisdn`) ;
INSERT INTO `t_s_function` (`ID`, `functioniframe`, `functionlevel`, `functionname`, `functionorder`, `functionurl`, `parentfunctionid`, `iconid`, `desk_iconid`, `functiontype`) VALUES ('402848814d899ace014d8a06ec1c0002', NULL, 1, '扣费限制', '4', 'consumeController.do?consume', '402848814d1c8e30014d1c904bb80001', '8a8ab0b246dc81120146dc8180460000', '8a8ab0b246dc81120146dc8180dd001e', 0);