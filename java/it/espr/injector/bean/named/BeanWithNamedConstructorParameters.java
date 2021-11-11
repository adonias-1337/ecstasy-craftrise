package it.espr.injector.bean.named;

import javax.inject.Named;

import it.espr.injector.bean.EmptyBean;
import it.espr.injector.bean.SimpleInterface;

public class BeanWithNamedConstructorParameters {

	private EmptyBean emptyBean;

	private SimpleInterface beanA;

	private SimpleInterface beanB;

	public BeanWithNamedConstructorParameters(EmptyBean emptyBean, @Named("a") SimpleInterface beanA, @Named("b") SimpleInterface beanB) {
		super();
		this.emptyBean = emptyBean;
		this.beanB = beanB;
		this.beanA = beanA;
	}

	public EmptyBean getEmptyBean() {
		return emptyBean;
	}

	public SimpleInterface getBeanA() {
		return beanA;
	}

	public SimpleInterface getBeanB() {
		return beanB;
	}
}
