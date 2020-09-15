package com.aop.aspect;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.splunk.Receiver;
import com.splunk.Service;

@Aspect
@Component
public class AopAspect {
	
	
	Service service= SplunkConn.getService();
	Receiver receiver = service.getReceiver();
	
	 
    /**
     * Pointcut that matches all repositories, services and Web REST endpoints.
     */
    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
        " || within(@org.springframework.stereotype.Service *)" +
        " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Pointcut that matches all Spring beans in the application's main packages.
     */
    @Pointcut("within(com.employeeservice..*)" +
        " || within(com.employeeservice.service..*)" +
        " || within(com.employeeservice.controller..*)")
    public void PackagePointcutEmployee() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    
    @Before("PackagePointcutEmployee() && springBeanPointcut()")
    public void beforeAdviceEmployee(JoinPoint joinPoint) {
		String pacakage = "Before method In Employee:" + joinPoint.getSignature().getDeclaringTypeName();
		String method = joinPoint.getSignature().getName();
		String args = Arrays.toString(joinPoint.getArgs());
		receiver.log("main", pacakage+method+args);
    }
    @After("PackagePointcutEmployee() && springBeanPointcut()")
    public void afterAdviceEmployee(JoinPoint joinPoint) {
		String pacakage = "After method In Employee:" + joinPoint.getSignature().getDeclaringTypeName();
		String method = joinPoint.getSignature().getName();
		//String result = (String) joinPoint.proceed();
		receiver.log("main", pacakage+method);
    }
    
    
    @Pointcut("within(com.departmentservice..*)" +
            " || within(com.departmentservice.service..*)" +
            " || within(com.departmentservice.controller..*)")
        public void PackagePointcutDepartment() {
            // Method is empty as this is just a Pointcut, the implementations are in the advices.
        }

        
        @Before("PackagePointcutDepartment() && springBeanPointcut()")
        public void beforeAdviceDepartment(JoinPoint joinPoint) {
    		String pacakage = "Before method In Department:" + joinPoint.getSignature().getDeclaringTypeName();
    		String method = joinPoint.getSignature().getName();
    		String args = Arrays.toString(joinPoint.getArgs());
    		receiver.log("main", pacakage+method+args);
        }
        @After("PackagePointcutDepartment() && springBeanPointcut()")
        public void afterAdviceDepartment(JoinPoint joinPoint) {
    		String pacakage = "After method In Department:" + joinPoint.getSignature().getDeclaringTypeName();
    		String method = joinPoint.getSignature().getName();
    		//String result = (String) joinPoint.proceed();
    		receiver.log("main", pacakage+method);
        }
    
        @Pointcut("within(com.deptempservice..*)" +
                " || within(com.deptempservice.service..*)" +
                " || within(com.deptempservice.controller..*)")
            public void PackagePointcutClient() {
                // Method is empty as this is just a Pointcut, the implementations are in the advices.
            }

            
            @Before("PackagePointcutClient() && springBeanPointcut()")
            public void beforeAdviceClient(JoinPoint joinPoint) {
        		String pacakage = "Before method In Client:" + joinPoint.getSignature().getDeclaringTypeName();
        		String method = joinPoint.getSignature().getName();
        		String args = Arrays.toString(joinPoint.getArgs());
        		receiver.log("main", pacakage+method+args);
            }
            @After("PackagePointcutClient() && springBeanPointcut()")
            public void afterAdviceClient(JoinPoint joinPoint) {
        		String pacakage = "After method In Client:" + joinPoint.getSignature().getDeclaringTypeName();
        		String method = joinPoint.getSignature().getName();
        		//String result = (String) joinPoint.proceed();
        		receiver.log("main", pacakage+method);
            }
    
            @Pointcut("within(com.discoveryserver..*)" +
                    " || within(com.discoveryserver.aspect..*)" 
                    )
                public void PackagePointcutDiscovery() {
                    // Method is empty as this is just a Pointcut, the implementations are in the advices.
                }

                
                @Before("PackagePointcutDiscovery() && springBeanPointcut()")
                public void beforeAdviceDiscovery(JoinPoint joinPoint) {
            		String pacakage = "Before method In Discovery:" + joinPoint.getSignature().getDeclaringTypeName();
            		String method = joinPoint.getSignature().getName();
            		String args = Arrays.toString(joinPoint.getArgs());
            		receiver.log("main", pacakage+method+args);
                }
                @After("PackagePointcutDiscovery() && springBeanPointcut()")
                public void afterAdviceDiscovery(JoinPoint joinPoint) {
            		String pacakage = "After method In Discovery:" + joinPoint.getSignature().getDeclaringTypeName();
            		String method = joinPoint.getSignature().getName();
            		//String result = (String) joinPoint.proceed();
            		receiver.log("main", pacakage+method);
                }
}