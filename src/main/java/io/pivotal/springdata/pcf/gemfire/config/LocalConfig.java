package io.pivotal.springdata.pcf.gemfire.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

/**
 * Created by Jay Lee on 3/28/16.
 */
@Configuration
@Profile("local")
@ImportResource(value = "client-cache.xml")
public class LocalConfig {
}
