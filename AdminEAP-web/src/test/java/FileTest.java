import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by Huang Jianhai on 2019/1/3.
 */
public class FileTest {

    @Test
    public void tt() throws IOException {
        File file = new File("E:\\Program Files\\gitProject\\AdminEAP\\AdminEAP-web\\src\\main\\resources\\query\\jpro.xml");
        if(!file.exists()){
            file.createNewFile();
        }
    }
}
