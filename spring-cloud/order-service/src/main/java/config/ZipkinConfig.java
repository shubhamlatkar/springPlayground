package config;

import brave.sampler.Sampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class ZipkinConfig {

    @Bean
    public Sampler defaultSampler() {
        return new Sampler() {
            @Override
            public boolean isSampled(long l) {
                return true;
            }
        };
    }
}
