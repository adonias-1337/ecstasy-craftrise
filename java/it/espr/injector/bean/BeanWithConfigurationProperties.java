package it.espr.injector.bean;

import javax.inject.Inject;
import javax.inject.Named;

public class BeanWithConfigurationProperties {

	@Inject
	@Named("my.first.configuration.property")
	private String firstProperty;

	private String secondProperty;

	public BeanWithConfigurationProperties(@Named("my.second.configuration.property") String secondProperty) {
		this.secondProperty = secondProperty;
	}

	public String getFirstProperty() {
		return firstProperty;
	}

	public String getSecondProperty() {
		return secondProperty;
	}
}
