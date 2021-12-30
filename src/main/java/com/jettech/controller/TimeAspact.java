package com.jettech.controller;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspact {
	private long endTimeByAfter;

	/**
	 * 定义环绕通知的表达式
	 *
	 * @Pointcut 声明切入点表达式
	 */
	@Pointcut("execution(public * com.jettech.controller.UserController.selectTotalCount(..))")
	public void TimeTest() {

	}

	/**
	 * 定义前置通知的表达式
	 *
	 * @Pointcut 声明切入点表达式
	 */
	@Pointcut("execution(public * com.jettech.controller.UserController.selectAll(..))")
	public void beforeTimeTest() {

	}

	/**
	 * 定义后置通知的表达式
	 *
	 * @Pointcut 声明切入点表达式
	 */
	@Pointcut("execution(public * com.jettech.controller.UserController.selectAll(..))")
	public void afterTimeTest() {

	}

	@Pointcut("execution(public * com.jettech.controller.UserController.selectAll(..))")
	public void myNotice(){

	}

	/**
	 * 环绕通知
	 *
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("TimeTest()")
	public Object doTimeTest(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.nanoTime(); // 获取开始时间（单位：纳秒）
		System.out.println("执行环绕通知前的纳秒时间为：" + startTime);
		Object object = null;
		try {
			object = joinPoint.proceed();
			long endTime = System.nanoTime(); // 获取结束时间（单位：纳秒）
			System.out.println("执行环绕通知后的纳秒时间为：" + endTime);
			long naTime = endTime - startTime;
			System.out.println("程序运行时间为：" + naTime + "纳秒");
			System.out.println("程序运行时间为：" + (naTime / 1000) + "微秒");
			System.out.println("程序运行时间为：" + (naTime / 1000 / 1000) + "毫秒");
			System.out.println("程序运行时间为：" + (naTime / 1000 / 1000 / 1000) + "秒");
		} catch (Exception e) {
			System.out.println("我就看看是不是进入了异常");
			e.printStackTrace();
		}
		return object;
	}

	/**
	 * 前置通知
	 *获取传入参数
	 * @throws Exception
	 */
	@Before(value = "beforeTimeTest()")
	public void doBeforeTimeTest(JoinPoint joinPoint) throws Exception {
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		for (Object arg : args) {
			System.out.println("before："+methodName+"："+arg);
		}

	}

	/**
	 * 后置通知
	 *获取接口执行后参数，
	 * @throws
	 */
	@After(value = "afterTimeTest()")
	public void doAfterTimeTest(JoinPoint joinPoint)  {
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		for (Object arg : args) {
			System.out.println("after："+methodName+"---"+arg);
		}
	}


	/**
	 * 返回值后通知
	 * @param joinPoint
	 * @param returnValue
	 */
	@AfterReturning(value = "myNotice()",returning = "returnValue")
	public void doAtferRetrning(JoinPoint joinPoint, Object returnValue){
		String methodName = joinPoint.getSignature().getName();

		System.out.println(returnValue);
	}

	/**
	 * 如果你想同时拥有多个切入点的话，可以使用逻辑操作符 “&&”，“||”等，如下所示：
	 * @param joinPoint
	 */
	@After(value = "beforeTimeTest() || afterTimeTest()")
	public void pushAccountInfo(JoinPoint joinPoint){

	}
}

