DROP TABLE IF EXISTS jp_monitor_day_calc;
CREATE TABLE jp_monitor_day_calc (
  mn varchar(27) NOT NULL,
  factor_code varchar(6) NOT NULL,
  mtime datetime NOT NULL,
  val_flag_all DECIMAL(16,6) DEFAULT NULL COMMENT '计算全部',
  val_flag_n DECIMAL(16,6) DEFAULT NULL COMMENT '只计算flag为n的数据',
  utime timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (mn,factor_code,mtime)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

####5☆☆☆☆☆ 如果UPDATE值不变并不会执行更新操作（utime没改变） 如果有更新则先删除再插入（ON DUPLICATE KEY UPDATE性质）
EXPLAIN
INSERT INTO jp_monitor_day_calc (mn,factor_code,mtime,val_flag_all)
select * from(
	SELECT mn, factor_code, DATE_FORMAT(mtime,'%Y-%m-%d') dtime,sum(val_avg) as val_all
	from jp_monitor_hour
	where mtime>=CURDATE() and mtime<=DATE_SUB(DATE_ADD(CURDATE(),INTERVAL 1 DAY),INTERVAL 1 SECOND)#防止跨分区
	group by mn, factor_code, DATE_FORMAT(mtime,'%Y-%m-%d')
) aa
ON DUPLICATE KEY UPDATE val_flag_all=aa.val_all;


#
#AAAAAAAABBBBBBBBCCCCCCCC199	w19002	2019-01-06	1184266.423500
select * from jp_monitor_day_calc where mn='AAAAAAAABBBBBBBBCCCCCCCC199' and factor_code='w19002' and mtime=date_format('2019-01-06','%Y-%m-%d')
#AAAAAAAABBBBBBBBCCCCCCCC199	w19002	2019-01-06 00:00:00	1184266.375000		2019-01-06 11:54:10  #float
AAAAAAAABBBBBBBBCCCCCCCC199	w19002	2019-01-06 00:00:00	1184266.423500		2019-01-06 12:26:12 #double、DECIMAL 一致

#select CURDATE(),DATE_ADD(CURDATE(),INTERVAL 1 DAY)