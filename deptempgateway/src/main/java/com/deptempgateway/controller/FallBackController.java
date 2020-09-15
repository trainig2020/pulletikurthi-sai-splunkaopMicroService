package com.deptempgateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.splunk.Receiver;
import com.splunk.Service;

import reactor.core.publisher.Mono;

@RestController
public class FallBackController {
	Service service= SplunkConn.getService();
	Receiver receiver = service.getReceiver();

	@RequestMapping("/deartmentfallback")
	public Mono<String> deptFallBack()
	{
		receiver.log("main", "Department Fall Back Event in GateWay Service");
		return  Mono.just("Its taking too much time to respond or down !please try again later");
		
	}

}
