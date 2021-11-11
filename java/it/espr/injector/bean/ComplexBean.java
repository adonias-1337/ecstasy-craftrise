package it.espr.injector.bean;

import javax.inject.Inject;
import javax.inject.Named;

public class ComplexBean {

	@Inject
	@Named("a")
	private SimpleInterface beanA;

	private SimpleInterface beanB;

	@Inject
	@Named("single")
	private SimpleInterface namedSingleton;

	private SingletonBean singletonBean;

	@Inject
	private EmptyBeanWithConstructor emptyBeanWithConstructor;

	private BeanWithConstructorWithSingleLevelDependencies beanWithConstructorWithSingleLevelDependencies;

	public ComplexBean(@Named("b") SimpleInterface beanB, SingletonBean singletonBean,
			BeanWithConstructorWithSingleLevelDependencies beanWithConstructorWithSingleLevelDependencies) {
		super();
		this.beanB = beanB;
		this.singletonBean = singletonBean;
		this.beanWithConstructorWithSingleLevelDependencies = beanWithConstructorWithSingleLevelDependencies;
	}

	public SimpleInterface getBeanA() {
		return beanA;
	}

	public SimpleInterface getBeanB() {
		return beanB;
	}

	public SimpleInterface getNamedSingleton() {
		return namedSingleton;
	}

	public SingletonBean getSingletonBean() {
		return singletonBean;
	}

	public EmptyBeanWithConstructor getEmptyBeanWithConstructor() {
		return emptyBeanWithConstructor;
	}

	public BeanWithConstructorWithSingleLevelDependencies getBeanWithConstructorWithSingleLevelDependencies() {
		return beanWithConstructorWithSingleLevelDependencies;
	}
}
