package be.xploregroup.joe.himself.configuration;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by bertswinnen on 26/09/15.
 */
@Configuration
public class MonitoringConfiguration {

    @Autowired
    private MetricRegistry registry;

    @Bean
    public JmxReporter jmxReporter() {
        JmxReporter reporter = JmxReporter.forRegistry(registry).build();
        reporter.start();
        return reporter;
    }

    /**
    @Bean
    public GraphiteReporter graphiteReporter() {
        Graphite graphite = new Graphite(new InetSocketAddress("localhost", 2003));
        GraphiteReporter reporter = GraphiteReporter.forRegistry(registry)
                .prefixedWith("boot").build(graphite);
        reporter.start(500, TimeUnit.MILLISECONDS);
        return reporter;
    }
    **/
}