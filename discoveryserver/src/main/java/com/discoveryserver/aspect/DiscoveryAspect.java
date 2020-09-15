//package com.discoveryserver.aspect;
//
//import java.util.Arrays;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
//import com.aop.aspect.AopAspect;
//import com.aop.aspect.SplunkConn;
//import com.splunk.Receiver;
//import com.splunk.Service;
//
//@Aspect
//@Component
//public class DiscoveryAspect {
//	
//	
//	Service service= SplunkConn.getService();
//	Receiver receiver = service.getReceiver();
//	AopAspect log=new AopAspect();
//	
//	 
//    /**
//     * Pointcut that matches all repositories, services and Web REST endpoints.
//     */
//    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
//        " || within(@org.springframework.stereotype.Service *)" +
//        " || within(@org.springframework.web.bind.annotation.RestController *)")
//    public void springBeanPointcut() {
//        // Method is empty as this is just a Pointcut, the implementations are in the advices.
//    }
//    @Pointcut("within(com.discoveryserver..*)" +
//            " || within(com.discoveryserver.aspect..*)" 
//            )
//    public void PackagePointcutDiscovery() {
//        // Method is empty as this is just a Pointcut, the implementations are in the advices.
//    }
//
//    
//    @Before("PackagePointcutDiscovery() && springBeanPointcut()")
//    public void beforeAdviceDiscovery(JoinPoint joinPoint) {
//		log.beforeAdviceDiscovery(joinPoint);
//    }
//    @After("PackagePointcutDiscovery() && springBeanPointcut()")
//    public void afterAdviceDiscovery(JoinPoint joinPoint) {
//         log .afterAdviceDiscovery(joinPoint);
//
//    }
//}
