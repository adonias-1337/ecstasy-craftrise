package it.espr.injector.bean;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

public class BeanWithConfiguredFields {

	@Inject
	@Named("map a")
	private Map<String,String> mapA;

	@Inject
	@Named("map b")
	private Map<String,String> mapB;
	
	@Inject
	@Named("list a")
	private List<Integer> listA;

	@Inject
	@Named("list b")
	private List<Integer> listB;
	
	@Inject
	@Named("bean a")
	private EmptyBean beanA;
	
	private EmptyBean beanB;

	public BeanWithConfiguredFields(@Named("bean b") EmptyBean beanB) {
		super();
		this.beanB = beanB;
	}

	public Map<String, String> getMapA() {
		return mapA;
	}

	public Map<String, String> getMapB() {
		return mapB;
	}

	public List<Integer> getListA() {
		return listA;
	}

	public List<Integer> getListB() {
		return listB;
	}

	public EmptyBean getBeanA() {
		return beanA;
	}

	public EmptyBean getBeanB() {
		return beanB;
	}
}
