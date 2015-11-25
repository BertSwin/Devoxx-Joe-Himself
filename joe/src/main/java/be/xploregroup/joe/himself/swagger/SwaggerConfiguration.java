package be.xploregroup.joe.himself.swagger;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by bertswinnen on 26/09/15.
 */
@Configuration
@EnableSwagger
public class SwaggerConfiguration {

    private SpringSwaggerConfig springSwaggerConfig;

    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    @Bean
    public SwaggerSpringMvcPlugin swaggerSpringMvcPluginFactory() {
        ApiInfo apiInfo = new ApiInfo("Spring RESTful Greeting Service with Swagger integration", "", "", "", "", "");
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(apiInfo).includePatterns("/.*");
    }
}
