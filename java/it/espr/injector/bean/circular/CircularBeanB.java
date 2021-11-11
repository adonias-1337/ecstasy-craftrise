package it.espr.injector.bean.circular;

public class CircularBeanB {

	private CircularBeanA beanA;

	public CircularBeanB(CircularBeanA beanA) {
		super();
		this.beanA = beanA;
	}

	public CircularBeanA getBeanA() {
		return beanA;
	}
}
