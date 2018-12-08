package crdm.nomenclature.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class DeleteAspect {
	
	@Before("execution( * crdm.nomenclature.service.RequestServiceImpl.delete(..))")
	public void rollback(JoinPoint jp) {
		System.out.println("request was deleted");
	}
}
