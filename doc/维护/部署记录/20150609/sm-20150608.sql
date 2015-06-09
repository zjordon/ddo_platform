ALTER TABLE `sm_bill_request`
ADD COLUMN `post_url`  varchar(128) NULL COMMENT '提交到的地址' AFTER `deliver_id`;


CREATE TABLE sm_channel(
    id          VARCHAR(32)     NOT NULL,
    post_url    VARCHAR(128),
    PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8
COMMENT='渠道表'
;

