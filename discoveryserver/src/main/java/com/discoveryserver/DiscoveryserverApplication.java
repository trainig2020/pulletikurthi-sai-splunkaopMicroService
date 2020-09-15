package com.discoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import com.aop.aspect.AopAspect;
import com.splunk.HttpService;
import com.splunk.Receiver;
import com.splunk.SSLSecurityProtocol;
import com.splunk.Service;
import com.splunk.ServiceArgs;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryserverApplication {

	public static void main(String[] args) {
		AopAspect log=new AopAspect();
		SpringApplication.run(DiscoveryserverApplication.class, args);
		HttpService.setSslSecurityProtocol(SSLSecurityProtocol.TLSv1_2);
        
        ServiceArgs loginArgs = new ServiceArgs();
        loginArgs.setUsername("satya1919");
        loginArgs.setPassword("Satya@1919");
        loginArgs.setHost("LAPTOP-E0E1VIO5");
        loginArgs.setPort(8089);
        
       
        Service service = Service.connect(loginArgs);	        
        Receiver receiver=service.getReceiver();
       receiver.log("main", "Discovery  Server");
        
	}

}
