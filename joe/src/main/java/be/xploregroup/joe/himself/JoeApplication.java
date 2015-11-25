package be.xploregroup.joe.himself;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by bertswinnen on 26/09/15.
 */
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class JoeApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JoeApplication.class);
    }

    public static void main(String[] args) {
        new JoeApplication().configure(
                new SpringApplicationBuilder(JoeApplication.class)).run(args);
    }
}
