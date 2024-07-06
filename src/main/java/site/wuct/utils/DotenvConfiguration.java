package site.wuct.utils;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotenvConfiguration {

    @Bean
    public Dotenv dotenv() {
        return Dotenv.configure()
                     .ignoreIfMissing()
                     .load();
    }
}
