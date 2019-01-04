import com.cnpc.framework.utils.DateUtil;
import com.cnpc.util.UtilComm;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Huang Jianhai on 2019/1/4.
 */
public class CreateTest {
    @Test
    public void crt_station_inf(){
        StringBuilder ist = new StringBuilder("insert into jp_station_info (mn)values");
        for (int i = 0; i < 200; i++) {
            //System.out.println("AAAAAAAABBBBBBBBCCCCCCCC"+ UtilComm.pad(String.valueOf(i),3));
            ist.append("('AAAAAAAABBBBBBBBCCCCCCCC").append(UtilComm.pad(String.valueOf(i),3)).append("'),");
        }
        ist.append("(XX);");
        System.out.println(ist.toString());
    }

    @Test
    public void crt_month_data() throws ParseException {
        StringBuilder ist = new StringBuilder();
        Date begTime = DateUtil.parse("2019-05-01",DateUtil.YYYY_MM_DD);
        Calendar time = Calendar.getInstance();
        time.setTime(begTime);
        for (int i = 0; i < 31*24; i++) {
            //System.out.println("AAAAAAAABBBBBBBBCCCCCCCC"+ UtilComm.pad(String.valueOf(i),3));
            ist.append("DATE_FORMAT('").append(DateUtil.format(time.getTime(),"yyyy-MM-dd HH")).append("','%Y-%m-%d %H'),");
            time.add(Calendar.HOUR_OF_DAY,1);
        }

        System.out.println(ist.toString());
    }
}
