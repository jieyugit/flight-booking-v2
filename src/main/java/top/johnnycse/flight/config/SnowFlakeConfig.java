package top.johnnycse.flight.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.johnnycse.flight.utils.SnowFlakeUtil;

@Configuration
public class SnowFlakeConfig {
    @Bean
    public SnowFlakeUtil snowFlakeUtil() {
        return new SnowFlakeUtil(1, 1);
    }
}
