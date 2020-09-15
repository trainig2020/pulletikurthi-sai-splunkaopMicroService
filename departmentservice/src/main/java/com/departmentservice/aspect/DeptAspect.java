
package com.departmentservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.aop.aspect.AopAspect;
import com.aop.aspect.SplunkConn;
import com.splunk.Receiver;
import com.splunk.Service;

@Aspect

@Component
public class DeptAspect {

	Service service = SplunkConn.getService();
	Receiver receiver = service.getReceiver();
	AopAspect log=new AopAspect();

	@Pointcut("within(@org.springframework.stereotype.Repository *)"
			+ " || within(@org.springframework.stereotype.Service *)"
			+ " || within(@org.springframework.web.bind.annotation.RestController *)")
	public void springBeanPointcut() { // Method is empty as this is just a
		// Pointcut, the implementations are in the advices.
	}
	
	@Pointcut("within(com.departmentservice..*)" +
            " || within(com.departmentservice.service..*)" +
            " || within(com.departmentservice.controller..*)")
        public void PackagePointcutDepartment() {
            // Method is empty as this is just a Pointcut, the implementations are in the advices.
        }

        
        @Before("PackagePointcutDepartment() && springBeanPointcut()")
        public void beforeAdviceDepartment(JoinPoint joinPoint) {
    		log.beforeAdviceDepartment(joinPoint);
        }
        @After("PackagePointcutDepartment() && springBeanPointcut()")
        public void afterAdviceDepartment(JoinPoint joinPoint) {
    		log.afterAdviceDepartment(joinPoint);
        }
    
}