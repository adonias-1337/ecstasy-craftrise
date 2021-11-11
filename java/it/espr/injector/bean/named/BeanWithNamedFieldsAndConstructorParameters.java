package it.espr.injector.bean.named;

import javax.inject.Inject;
import javax.inject.Named;

import it.espr.injector.bean.EmptyBean;
import it.espr.injector.bean.SimpleInterface;

public class BeanWithNamedFieldsAndConstructorParameters {

	private EmptyBean emptyBean;

	@Inject
	@Named("a")
	private SimpleInterface beanA;

	private SimpleInterface beanB;

	public BeanWithNamedFieldsAndConstructorParameters(EmptyBean emptyBean, @Named("b") SimpleInterface beanB) {
		super();
		this.emptyBean = emptyBean;
		this.beanB = beanB;
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
