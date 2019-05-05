DROP PROCEDURE IF EXISTS pro_ist_jp_monitor_hour;
DELIMITER //
CREATE PROCEDURE pro_ist_jp_monitor_hour()
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
  declare cur_factor_code cursor for select code from jp_factor where id <205;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

  SET pre_sql = 'INSERT INTO jp_monitor_hour (mtime,mn,factor_code,val_avg) VALUES ';
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
					SET beg_date = DATE_FORMAT('2019-01-01', '%Y-%m-%d');
					SET end_date = DATE_FORMAT('2020-01-01', '%Y-%m-%d');
					WHILE beg_date < end_date DO
							SET beg_date = DATE_ADD(beg_date,INTERVAL 1 HOUR);
							set bnum = bnum+1;
							#SET @sqlstr = concat(@sqlstr,'(','DATE_FORMAT(''',beg_date,''',''%Y-%m-%d''),'',''',qry_mn,''',''',qry_code,''',',round(rand()*100000,4)')');
							SET @sqlstr = concat(@sqlstr,'(DATE_FORMAT(''',beg_date,''',''%Y-%m-%d %H:%i:%S''),''',qry_mn,''',''',qry_code,''',',round(rand()*100000,4),')');
							#打印输出
							#SELECT @sqlstr;
							IF MOD(bnum,500)=0 THEN
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


#ALTER TABLE jp_monitor_hour ADD PRIMARY KEY (mtime,mn,factor_code);
#ALTER TABLE jp_monitor_hour DROP PRIMARY KEY;
#测试 设置MOD500为7秒 #一个月用时376秒
#truncate table jp_monitor_hour
#select count(1) from jp_monitor_hour
#call pro_ist_jp_monitor_hour();