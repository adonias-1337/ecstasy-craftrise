package it.espr.injector.bean;

public class BeanWithConstructorWithSingleLevelDependencies {

	private EmptyBean emptyBean;

	private EmptyBeanWithConstructor emptyBeanWithConstructor;

	public BeanWithConstructorWithSingleLevelDependencies(EmptyBean emptyBean, EmptyBeanWithConstructor emptyBeanWithConstructor) {
		super();
		this.emptyBean = emptyBean;
		this.emptyBeanWithConstructor = emptyBeanWithConstructor;
	}

	public EmptyBean getEmptyBean() {
		return emptyBean;
	}

	public EmptyBeanWithConstructor getEmptyBeanWithConstructor() {
		return emptyBeanWithConstructor;
	}
}
