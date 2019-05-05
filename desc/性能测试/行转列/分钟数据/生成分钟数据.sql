DROP PROCEDURE IF EXISTS pro_ist_jp_monitor_minute;
DELIMITER //
CREATE PROCEDURE pro_ist_jp_monitor_minute()
BEGIN
	declare beg_date datetime;
	declare end_date datetime;
	declare qry_mn varchar(27) default '11';
	declare qry_code varchar(7) default '00';
	declare bnum int default 0;
	declare pre_sql varchar(100);
  /*定义游标*/
	DECLARE done int;
  declare cur_factor_code cursor for select mn,factor_code from jp_factor_relation ;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

  SET pre_sql = 'INSERT INTO jp_monitor_minute (mtime,mn,factor_code,val_avg) VALUES ';
  SET @sqlstr = pre_sql;

	open cur_factor_code;  /*打开游标*/
	out_loop:LOOP
		FETCH cur_factor_code INTO qry_mn,qry_code;
		IF done = 1 THEN
				LEAVE out_loop;
		END IF;
		#设置起止结束日期
		SET beg_date = DATE_FORMAT('2019-01-01', '%Y-%m-%d');
		SET end_date = DATE_FORMAT('2019-01-02', '%Y-%m-%d');
		WHILE beg_date < end_date DO
				SET beg_date = DATE_ADD(beg_date,INTERVAL 10 MINUTE);
				set bnum = bnum+1;
				SET @sqlstr = concat(@sqlstr,'(DATE_FORMAT(''',beg_date,''',''%Y-%m-%d %H:%i:%S''),''',qry_mn,''',''',qry_code,''',',round(rand()*100,4),')');

				IF MOD(bnum,5)=0 THEN
						#打印输出
						SELECT @sqlstr;
						prepare stmt from @sqlstr;
						EXECUTE stmt ;
						deallocate prepare stmt;
						SET @sqlstr = pre_sql;
				ELSE
						SET @sqlstr = concat(@sqlstr,',');
				end IF;
		END WHILE;
	END LOOP out_loop;
	CLOSE cur_factor_code;
END; //
DELIMITER;


#truncate table jp_monitor_minute
#select count(1) from jp_monitor_minute
#call pro_ist_jp_monitor_minute();
