CREATE TABLE jp_monitor_real (
  mid int(11) NOT NULL,
  mn varchar(27) NOT NULL,
  factor_code varchar(6) NOT NULL,
  mtime datetime NOT NULL,
  val_cou int(11) DEFAULT NULL COMMENT '累计值',
  val_min int(11) DEFAULT NULL COMMENT '最小值',
  val_avg int(11) DEFAULT NULL COMMENT '平均值',
  val_max int(11) DEFAULT NULL COMMENT '最大值',
  flag_mark varchar(1) DEFAULT NULL COMMENT '数据标记',
	PRIMARY KEY (mn,factor_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8