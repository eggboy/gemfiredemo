package io.pivotal.springdata.pcf.gemfire.config;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import com.gemstone.gemfire.cache.client.ClientRegionShortcut;
import io.pivotal.spring.cloud.service.common.GemfireServiceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${gemfire.service.name}") private String gemfireServiceName;

    @Autowired GemfireServiceInfo gemfireServiceInfo;

    @Bean
    public GemfireTemplate getCustomerTemplate() {
        Properties props = new Properties();
        props.setProperty("security-client-auth-init", "io.pivotal.springdata.pcf.gemfire.PCFClientAuthInitialize.create");
        ClientCacheFactory ccf = new ClientCacheFactory(props);
        URI[] locators = gemfireServiceInfo.getLocators();
        for (URI locator : locators) {
            ccf.addPoolLocator(locator.getHost(), locator.getPort());
        }
        ClientCache client = ccf.create();
        Region r = client.createClientRegionFactory(ClientRegionShortcut.PROXY).create("Customer");

        return new GemfireTemplate(r);
    }

    @Bean GemfireServiceInfo gemfireServiceInfo() {
        CloudFactory cloudFactory = new CloudFactory();
        Cloud cloud = cloudFactory.getCloud();
        return (GemfireServiceInfo) cloud.getServiceInfo(gemfireServiceName);
    }
}
