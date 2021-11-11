package it.espr.injector.bean.circular;

public class CircularBeanA {

	private CircularBeanB beanB;

	public CircularBeanA(CircularBeanB beanB) {
		super();
		this.beanB = beanB;
	}

	public CircularBeanB getBeanB() {
		return beanB;
	}
}
