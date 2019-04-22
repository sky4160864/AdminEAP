SELECT
#DISTINCT CONCAT('{"name":"',p2_0.DEVCOMPANY,'","longitude":"',p3.longitude,'","latitude":"',p3.latitude,'"},') ss
DISTINCT CONCAT(p2_0.DEVCOMPANY,',',p3.longitude,',',p3.latitude) ss
from pw02_enter_message p2_0
INNER JOIN
    /*根据最新办结时间itemendtime筛选*/
    (select DEVCOMPANY,max(ITEMENDTIME) ITEMENDTIME from pw02_enter_message group by DEVCOMPANY ) p2_1
    on(p2_1.DEVCOMPANY=p2_0.DEVCOMPANY and p2_1.ITEMENDTIME=p2_0.ITEMENDTIME)
INNER JOIN
    /*系统出现,多家企业办结时间一致的有多条记录，通过ITEMTYPE筛选*/
    (select DEVCOMPANY, ITEMENDTIME,max(ITEMTYPE) ITEMTYPE from pw02_enter_message group by creditcode,ITEMENDTIME ) p2_2
    on(p2_2.DEVCOMPANY=p2_0.DEVCOMPANY and p2_2.ITEMENDTIME=p2_0.ITEMENDTIME and p2_2.ITEMTYPE=p2_0.ITEMTYPE)
INNER JOIN pw18_info pw18
    on(p2_0.DATAID=pw18.dataid and pw18.fzdept='平湖市环境保护局')/*筛选桐乡企业*/
INNER JOIN pw03_base_info p3 on(p2_0.DATAID=p3.DATAID)
INNER JOIN pw01_enter_info p1 on(p1.enterid=p3.enterid )/*同家企业可能多条,需DISTINCT*/
