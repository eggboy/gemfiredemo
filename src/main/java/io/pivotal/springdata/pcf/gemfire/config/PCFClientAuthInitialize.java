package io.pivotal.springdata.pcf.gemfire.config;

import com.gemstone.gemfire.LogWriter;
import com.gemstone.gemfire.distributed.DistributedMember;
import com.gemstone.gemfire.security.AuthInitialize;
import com.gemstone.gemfire.security.AuthenticationFailedException;
import io.pivotal.spring.cloud.service.common.GemfireServiceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;

import java.util.Properties;

public class PCFClientAuthInitialize implements AuthInitialize {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private static final String USER_NAME = "security-username";
    private static final String PASSWORD = "security-password";

    private GemfireServiceInfo serviceInfo;

    private PCFClientAuthInitialize() {
        String gemfireServiceName = "p-gemfire";

        CloudFactory cloudFactory = new CloudFactory();
        Cloud cloud = cloudFactory.getCloud();

        serviceInfo = (GemfireServiceInfo) cloud.getServiceInfo("p-gemfire");
    }

    public static AuthInitialize create() {
        return new PCFClientAuthInitialize();
    }

    @Override
    public void close() {
    }

    @Override
    public Properties getCredentials(Properties arg0, DistributedMember arg1,
        boolean arg2) throws AuthenticationFailedException {
        String username = serviceInfo.getUsername();
        String password = serviceInfo.getPassword();

        Properties props = new Properties();
        props.put(USER_NAME, username);
        props.put(PASSWORD, password);
        return props;
    }

    @Override
    public void init(LogWriter arg0, LogWriter arg1)
        throws AuthenticationFailedException {
    }
}
