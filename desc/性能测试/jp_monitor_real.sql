DROP TABLE IF EXISTS jp_monitor_real;
CREATE TABLE `jp_monitor_real` (
  `mn` varchar(27) NOT NULL,
  `factor_code` varchar(6) NOT NULL,
  `mtime` datetime NOT NULL,
  `val_cou` int(11) DEFAULT NULL COMMENT '累计值',
  `val_min` int(11) DEFAULT NULL COMMENT '最小值',
  `val_avg` int(11) DEFAULT NULL COMMENT '平均值',
  `val_max` int(11) DEFAULT NULL COMMENT '最大值',
  `calc_avg` int(11) DEFAULT NULL COMMENT '平均值计算',
  `flag_mark` varchar(1) DEFAULT NULL COMMENT '数据标记',
  PRIMARY KEY (`mn`,`factor_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8



DROP PROCEDURE IF EXISTS pro_ist_jp_monitor_real;
DELIMITER //
CREATE PROCEDURE pro_ist_jp_monitor_real()
BEGIN
	declare beg_date datetime;
	declare end_date datetime;
	declare qry_mn varchar(27);
	declare qry_code varchar(7);
	declare bnum int default 0;
	declare pre_sql varchar(100);
  /*定义游标*/
	DECLARE done int;
  declare cur_mn cursor for select mn from jp_station_info;
  declare cur_factor_code cursor for select code from jp_factor
      where code in('w00000','w01001','w01018','w21003','w21011','w21001','w20116','w20117','w20121','w20122','w20120','w20123','w20115','w01020','w21016','w21017'
          ,'a21026','a21026Zs','a21002','a21002Zs','a34013','a34013Zs','a19001','a01012','a01013','a01011','a00000','a21005','a21005Zs','a21024','a21024Zs','a01014');
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

  SET pre_sql = 'INSERT INTO jp_monitor_real (mtime,mn,factor_code,val_avg,calc_avg) VALUES ';
  SET @sqlstr = pre_sql;

	/*第一个游标循环*/
	open cur_mn;  /*打开游标*/
	out_loop:LOOP
			FETCH cur_mn INTO qry_mn;
			IF done = 1 THEN
					LEAVE out_loop;
			END IF;

			-- 第二个游标循环
			open cur_factor_code;
			inner_loop:LOOP
					FETCH cur_factor_code INTO qry_code;
					IF done = 1 THEN
							LEAVE inner_loop;
					END IF;

					#设置起止结束日期
					SET beg_date = DATE_FORMAT('2019-01-01 0', '%Y-%m-%d %H');
					SET end_date = DATE_FORMAT('2019-01-01 1', '%Y-%m-%d %H');
					WHILE beg_date < end_date DO
							SET beg_date = DATE_ADD(beg_date,INTERVAL 1 HOUR);
							set bnum = bnum+1;
							#SET @sqlstr = concat(@sqlstr,'(','DATE_FORMAT(''',beg_date,''',''%Y-%m-%d''),'',''',qry_mn,''',''',qry_code,''',',round(rand()*100000,4)')');
							SET @sqlstr = concat(@sqlstr,'(DATE_FORMAT(''',beg_date,''',''%Y-%m-%d %H:%i:%S''),''',qry_mn,''',''',qry_code,''',',round(rand()*100000,4),',',round(rand()*100000,4),')');
							#打印输出
							#SELECT @sqlstr;
							IF MOD(bnum,5)=0 THEN
									prepare stmt from @sqlstr;
									EXECUTE stmt ;
									deallocate prepare stmt;
									SET @sqlstr = pre_sql;
							ELSE
									SET @sqlstr = concat(@sqlstr,',');
							end IF;
					END WHILE;
			end LOOP inner_loop;
			CLOSE cur_factor_code;
			SET done=0;-- 注意这里（重点）

	END LOOP out_loop;
	CLOSE cur_mn;
END; //
DELIMITER;

#truncate table jp_monitor_real
#select count(1) from jp_monitor_real
#call pro_ist_jp_monitor_real();




#创建定时任务event(事件)
DROP EVENT IF EXISTS event_jp_monitor_real;
DELIMITER //
CREATE EVENT event_jp_monitor_real
ON SCHEDULE EVERY 1 minute
ON COMPLETION PRESERVE
DO
BEGIN
update jp_monitor_real set val_avg=round(rand()*100000,4),calc_avg=round(rand()*100000,4),mtime=now() ;
END//