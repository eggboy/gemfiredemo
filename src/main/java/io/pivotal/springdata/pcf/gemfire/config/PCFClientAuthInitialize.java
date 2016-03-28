package io.pivotal.springdata.pcf.gemfire.config;

import com.gemstone.gemfire.LogWriter;
import com.gemstone.gemfire.distributed.DistributedMember;
import com.gemstone.gemfire.security.AuthInitialize;
import com.gemstone.gemfire.security.AuthenticationFailedException;
import io.pivotal.spring.cloud.service.common.GemfireServiceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class PCFClientAuthInitialize implements AuthInitialize {

    @Autowired GemfireServiceInfo gemfireServiceInfo;

    public static final String USER_NAME = "security-username";
    public static final String PASSWORD = "security-password";
/*

    public GemfireServiceInfo serviceInfo;

    private PCFClientAuthInitialize() {
        CloudFactory cloudFactory = new CloudFactory();
        Cloud cloud = cloudFactory.getCloud();
        serviceInfo = (GemfireServiceInfo) cloud.getServiceInfo(gemfireServiceName);
    }
*/

    public static AuthInitialize create() {
        return new PCFClientAuthInitialize();
    }

    @Override
    public void close() {
    }

    @Override
    public Properties getCredentials(Properties arg0, DistributedMember arg1,
        boolean arg2) throws AuthenticationFailedException {
        Properties props = new Properties();

        String username = gemfireServiceInfo.getUsername();
        String password = gemfireServiceInfo.getPassword();
        props.put(USER_NAME, username);
        props.put(PASSWORD, password);

        return props;
    }

    @Override
    public void init(LogWriter arg0, LogWriter arg1)
        throws AuthenticationFailedException {
    }
}
