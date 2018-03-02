package ru.dz.restapi.configurations.root;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import ru.dz.restapi.configurations.root.db.DataSourceConfig;

@Configuration
@Import({DataSourceConfig.class})
@EnableAsync
@EnableAspectJAutoProxy
@PropertySources(
        {@PropertySource("classpath:spring/${spring.active.profiles:dev}/db.properties")}
)
@ComponentScan(value = "ru.dz.restapi", excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "ru\\.dz\\.restapi\\.((configurations)|(web))\\..*"))
public class ApplicationConfig {

    @Bean
    public MappingJackson2HttpMessageConverter jacksonHttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Hibernate5Module());
        converter.setObjectMapper(objectMapper);
        return converter;
    }
}
