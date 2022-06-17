package ioc.collection;

import java.util.*;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import polymorphism.TV;

public class CollectionTest {
	public static void main(String[] args) {
		AbstractApplicationContext factory = new GenericXmlApplicationContext("collectionContext.xml");
		CollectionBean bean = (CollectionBean)factory.getBean("listBean");	
		List<String> list = bean.getList();
		System.out.println(list);
		
		bean = (CollectionBean)factory.getBean("setBean");	
		Set<String> set = bean.getSet();
		System.out.println(set);
		
		bean = (CollectionBean)factory.getBean("mapBean");	
		Map<String, Integer> map = bean.getMap();
		System.out.println(map);

		bean = (CollectionBean)factory.getBean("propBean");	
		Properties prop = bean.getProp();
		System.out.println(prop);
		factory.close();
	}
}
