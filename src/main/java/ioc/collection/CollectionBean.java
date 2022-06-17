package ioc.collection;

import java.util.*;

// 컬렉션을 Setter Injection 하는 방법
public class CollectionBean {

	// 1. List
	private List<String> list;
	
	public List<String> getList() {
		return list;
	}
	
	public void setList(List<String> list) {
		this.list = list;
	}
	
	// 2. Set
	private Set<String> set;
	
	public Set<String> getSet() {
		return set;
	}

	public void setSet(Set<String> set) {
		this.set = set;
	}

	// 3. Map
	private Map<String, Integer> map;

	public Map<String, Integer> getMap() {
		return map;
	}

	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}
	// 4. Properties
	private Properties prop;

	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}
	// setter, getter
	

		
}
