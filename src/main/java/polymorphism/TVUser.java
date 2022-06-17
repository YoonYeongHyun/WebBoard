package polymorphism;

import org.springframework.context.event.AbstractApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {
	
	public static void main(String[] args) {
		/*
		인터페이스를 사용한 객체의 교체
		TV tv = new SamsungTV();
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
		tv = new LgTV();
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
		
		직접만든 BeanFactory 클래스 사용
		BeanFactory factory = new BeanFactory();
		TV tv = (TV)factory.getBean("lg");
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		*/
		
		//스프링에서 내포된 방법사용
		/*
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		System.out.println("------------------------------");
		TV tv1 = (TV)factory.getBean("tv_S");	
		tv1.powerOn();
		tv1.volumeUp();
		tv1.volumeDown();
		tv1.powerOff();
		
		System.out.println("------------------------------");
		TV tv2 = (TV)factory.getBean("tv_L");
		tv2.powerOn();
		tv2.volumeUp();
		tv2.volumeDown();
		tv2.powerOff();
		System.out.println("------------------------------");
		factory.close();
		*/
		
		/*
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		// 객체가 한번만 생성(싱글톤 패턴 적용) scope속성을 이용하여 변경
		TV tv1 = (TV)factory.getBean("tv_S");	
		TV tv2 = (TV)factory.getBean("tv_S");
		TV tv3 = (TV)factory.getBean("tv_S");
		factory.close();
		*/
		
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		TV tv_L = (TV)factory.getBean("tv_L");	
		tv_L.powerOn();
		tv_L.volumeUp();
		tv_L.volumeDown();
		tv_L.powerOff();
		factory.close();
	}
}
