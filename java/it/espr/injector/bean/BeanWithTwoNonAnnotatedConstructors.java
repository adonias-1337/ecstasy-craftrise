package it.espr.injector.bean;

public class BeanWithTwoNonAnnotatedConstructors {

	private String name;

	private Object object;

	public BeanWithTwoNonAnnotatedConstructors(Object object) {
		this.object = object;
	}

	public BeanWithTwoNonAnnotatedConstructors(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Object getObject() {
		return object;
	}
}
