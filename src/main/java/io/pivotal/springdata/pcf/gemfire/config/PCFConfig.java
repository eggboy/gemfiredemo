package io.pivotal.springdata.pcf.gemfire.config;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import com.gemstone.gemfire.cache.client.ClientRegionShortcut;
import io.pivotal.spring.cloud.service.common.GemfireServiceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.gemfire.GemfireTemplate;

import java.net.URI;
import java.util.Properties;

/**
 * Created by Jay Lee on 3/28/16.
 */
@Configuration
@Profile("cloud")
public class PCFConfig {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private String gemfireServiceName = "p-gemfire";

    @Bean Cloud getCloud() {
        CloudFactory cloudFactory = new CloudFactory();
        return cloudFactory.getCloud();
    }

    @Bean
    public GemfireTemplate getCustomerTemplate() {
        Cloud cloud = getCloud();

        GemfireServiceInfo gemfireServiceInfo = (GemfireServiceInfo) cloud.getServiceInfo(gemfireServiceName);

        Properties props = new Properties();
        props.setProperty("security-client-auth-init", "io.pivotal.springdata.pcf.gemfire.config.PCFClientAuthInitialize.create");

        ClientCacheFactory ccf = new ClientCacheFactory(props);
        URI[] locators = gemfireServiceInfo.getLocators();
        for (URI locator : locators) {
            ccf.addPoolLocator(locator.getHost(), locator.getPort());
        }
        ClientCache client = ccf.create();
        client.setCopyOnRead(true);

        Region r = client.createClientRegionFactory(ClientRegionShortcut.PROXY).create("Customer");

        return new GemfireTemplate(r);
    }

}
