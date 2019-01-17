#生成记录
INSERT jp_station_info
select CONCAT(SUBSTR(id,1,29),LPAD(SUBSTR(id,30)+1,3,0)) as id,
create_date_time,deleted,update_date_time,version,address,area_code,lat,lng,
CONCAT(SUBSTR(mn,1,24),LPAD(SUBSTR(mn,25)+1,3,0)) as mn,
CONCAT(SUBSTR(name,1,4),LPAD(SUBSTR(name,5)+1,3,0)) as name,
remark,st_code
 from jp_station_info where id in( select max(id) from jp_station_info )