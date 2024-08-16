package load.config;

import history.config.HistoryConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(HistoryConfig.class)
public class LoadConfig {

}
