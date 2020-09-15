package com.springconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

import com.splunk.HttpService;
import com.splunk.Receiver;
import com.splunk.SSLSecurityProtocol;
import com.splunk.Service;
import com.splunk.ServiceArgs;

@SpringBootApplication
@EnableConfigServer
public class AspringconfigserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(AspringconfigserverApplication.class, args);
		
		 HttpService.setSslSecurityProtocol(SSLSecurityProtocol.TLSv1_2);
	        
	        ServiceArgs loginArgs = new ServiceArgs();
	        loginArgs.setUsername("satya1919");
	        loginArgs.setPassword("Satya@1919");
	        loginArgs.setHost("LAPTOP-E0E1VIO5");
	        loginArgs.setPort(8089);
	        
	       
	        Service service = Service.connect(loginArgs);	        
	        Receiver receiver=service.getReceiver();
	       receiver.log("main", "Spring Config Server");
	        
	        
	    }
	}


