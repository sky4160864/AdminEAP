import com.cnpc.util.UtilComm;
import org.testng.annotations.Test;

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
}
