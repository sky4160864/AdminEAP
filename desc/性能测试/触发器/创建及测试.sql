#《Mysql高性能》说不要用触发器，并发中mvvc会有问题
DROP TABLE IF EXISTS jp_monitor_day_calc;
CREATE TABLE jp_monitor_day_calc (
  mn varchar(27) NOT NULL,
  factor_code varchar(6) NOT NULL,
  mtime datetime NOT NULL,
  val_flag_all double(16,6) DEFAULT NULL COMMENT '计算全部',
  val_flag_n double(16,6) DEFAULT NULL COMMENT '只计算flag为n的数据',
  utime timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (mn,factor_code,mtime)
) ENGINE=InnoDB DEFAULT CHARSET=utf8


DELIMITER //
CREATE TRIGGER tgr_jp_monitor_hour AFTER INSERT ON jp_monitor_hour 
FOR EACH ROW
BEGIN
INSERT INTO jp_monitor_day_calc(mn,factor_code,mtime,val_flag_all) values(NEW.mn,NEW.factor_code,DATE_FORMAT(NEW.mtime,'%Y-%m-%d'),NEW.val_avg) 
	ON DUPLICATE KEY UPDATE val_flag_all=val_flag_all+NEW.val_avg;
END //
DELIMITER;

insert into jp_monitor_hour(mn,factor_code,mtime,val_avg) values ('111','w001',DATE_FORMAT('2020-01-01 00','%Y-%m-%d %H'),1);
insert into jp_monitor_hour(mn,factor_code,mtime,val_avg) values ('222','w001',DATE_FORMAT('2020-01-01 00','%Y-%m-%d %H'),1);
insert into jp_monitor_hour(mn,factor_code,mtime,val_avg) values ('111','w001',DATE_FORMAT('2020-01-01 01','%Y-%m-%d %H'),1);

drop trigger tgr_jp_monitor_hour;