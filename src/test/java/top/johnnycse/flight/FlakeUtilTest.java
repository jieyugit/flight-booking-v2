package top.johnnycse.flight;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.johnnycse.flight.utils.SnowFlakeUtil;

@SpringBootTest
public class FlakeUtilTest {
    @Test
    public void test(){
        SnowFlakeUtil snowFlakeUtil = new SnowFlakeUtil(12, 13);
        System.out.println(snowFlakeUtil.getNextId());
        System.out.println(snowFlakeUtil.getNextId());
    }
}
