package com.deptempgateway.controller;



import com.splunk.HttpService;
import com.splunk.SSLSecurityProtocol;
import com.splunk.Service;
import com.splunk.ServiceArgs;

public class SplunkConn {
   
  public static Service getService() {
      HttpService.setSslSecurityProtocol(SSLSecurityProtocol.TLSv1_2);
      // Create a map of arguments and add login parameters
      ServiceArgs loginArgs = new ServiceArgs();
      loginArgs.setUsername("satya1919");
      loginArgs.setPassword("Satya@1919");
      loginArgs.setHost("LAPTOP-E0E1VIO5");
      loginArgs.setPort(8089);
     
      // Create a Service instance and log in with the argument map
      Service service = Service.connect(loginArgs);
    return service;
  }
   
}