/*分页查询优化https://www.cnblogs.com/ivictor/p/5099870.html*/
/*记录数*/
select count(DISTINCT mtime) from jp_monitor_minute
where mn='AAAAAAAABBBBBBBBCCCCCCCC000'
	and mtime>=DATE_FORMAT('2019-05-01','%Y-%m-%d')
	and mtime<DATE_ADD(DATE_FORMAT('2019-05-31','%Y-%m-%d'),INTERVAL 1 DAY)

select * from jp_factor_relation where mn='AAAAAAAABBBBBBBBCCCCCCCC000' order by factor_order

explain
select mn,mtime,
	max(case factor_code when 'v01' then val_avg else null end ) 'v01',
	max(case factor_code when 'v02' then val_avg else null end ) 'v02',
	max(case factor_code when 'v03' then val_avg else null end ) 'v03'
from jp_monitor_minute
where mn='AAAAAAAABBBBBBBBCCCCCCCC000'
	and mtime>=DATE_FORMAT('2019-05-01','%Y-%m-%d')
	and mtime<DATE_ADD(DATE_FORMAT('2019-07-01','%Y-%m-%d'),INTERVAL 1 DAY)
GROUP BY mn,mtime

SELECT * FROM jp_monitor_minute PARTITION (p201901) where mn='AAAAAAAABBBBBBBBCCCCCCCC000' ;

/*1.执行一条sql*/
/*2.获取query_id*/
SHOW PROFILES

/*3,4执行一次后INFORMATION_SCHEMA.PROFILING中的记录就没了*/
/*3.通过query_id查*/
show profile for query 151;
/*4.通过query_id查【推荐】*/
SELECT STATE, SUM(DURATION) AS Total_R,
    ROUND(
      100 * SUM(DURATION) /
      (SELECT SUM(DURATION)
       FROM INFORMATION_SCHEMA.PROFILING
       WHERE QUERY_ID = @query_id
      ), 2) AS Pct_R,
    COUNT(*) AS Calls,
    SUM(DURATION) / COUNT(*) AS "R/Call"
  FROM INFORMATION_SCHEMA.PROFILING
  WHERE QUERY_ID = 191
  GROUP BY STATE
  ORDER BY Total_R DESC;

/*优化参考
https://github.com/sky4160864/study/blob/1cf50b53a987254fa65f450074db22f5385772e1/db/mysql/%E5%88%86%E6%9E%90%E6%B5%8B%E8%AF%95/show%E5%91%BD%E4%BB%A4#L12
*/