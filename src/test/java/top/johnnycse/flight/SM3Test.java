package top.johnnycse.flight;

import cn.hutool.crypto.SmUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SM3Test {
    @Test
    public void test(){
        String text = "yujie";
        String digestHex = SmUtil.sm3(text);
        System.out.println("加密后：" + digestHex);
        System.out.println(digestHex.toUpperCase().equals("F0DDC8D1DD1F93B0495972E19CC160716B3F9B09B703D896810932903E15AA64"));
    }
}
