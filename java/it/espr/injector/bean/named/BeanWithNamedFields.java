package it.espr.injector.bean.named;

import javax.inject.Inject;
import javax.inject.Named;

import it.espr.injector.bean.SimpleInterface;

public class BeanWithNamedFields {

	@Inject
	@Named("a")
	private SimpleInterface beanA;

	@Inject
	@Named("b")
	private SimpleInterface beanB;
}
