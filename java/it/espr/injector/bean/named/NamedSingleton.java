package it.espr.injector.bean.named;

import javax.inject.Named;
import javax.inject.Singleton;

import it.espr.injector.bean.SimpleInterface;

@Named("single")
@Singleton
public class NamedSingleton implements SimpleInterface {

}
