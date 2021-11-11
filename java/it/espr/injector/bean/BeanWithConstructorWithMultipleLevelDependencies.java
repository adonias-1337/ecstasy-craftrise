package it.espr.injector.bean;

public class BeanWithConstructorWithMultipleLevelDependencies {

	private final EmptyBean emptyBean;

	private final EmptyBeanWithConstructor emptyBeanWithConstructor;

	private final BeanWithConstructorWithSingleLevelDependencies beanWithConstructorWithSingleLevelDependencies;

	public BeanWithConstructorWithMultipleLevelDependencies(EmptyBean emptyBean, EmptyBeanWithConstructor emptyBeanWithConstructor,
			BeanWithConstructorWithSingleLevelDependencies beanWithConstructorWithSingleLevelDependencies) {
		super();
		this.emptyBean = emptyBean;
		this.emptyBeanWithConstructor = emptyBeanWithConstructor;
		this.beanWithConstructorWithSingleLevelDependencies = beanWithConstructorWithSingleLevelDependencies;
	}

	public EmptyBean getEmptyBean() {
		return emptyBean;
	}

	public EmptyBeanWithConstructor getEmptyBeanWithConstructor() {
		return emptyBeanWithConstructor;
	}

	public BeanWithConstructorWithSingleLevelDependencies getBeanWithConstructorWithSingleLevelDependencies() {
		return beanWithConstructorWithSingleLevelDependencies;
	}
}
