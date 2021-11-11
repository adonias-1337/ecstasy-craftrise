package it.espr.injector;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import it.espr.injector.bean.BeanWithConstructorWithMultipleLevelDependencies;
import it.espr.injector.bean.BeanWithConstructorWithSingleLevelDependencies;
import it.espr.injector.bean.ComplexBean;
import it.espr.injector.bean.EmptyBean;
import it.espr.injector.bean.EmptyBeanWithConstructor;
import it.espr.injector.bean.SimpleInterface;
import it.espr.injector.bean.SingletonBean;
import it.espr.injector.bean.named.NamedEmptyBeanA;
import it.espr.injector.bean.named.NamedEmptyBeanB;
import it.espr.injector.bean.named.NamedSingleton;
import it.espr.injector.exception.BeanCreationExpection;
import it.espr.injector.exception.InjectingException;

public class BeanFactoryTest {

	private Configuration configuration = new Configuration() {
	};

	private BeanFactory beanFactory = new BeanFactory();

	private ClassInspector beanInspector = new ClassInspector(this.configuration.bindings);

	@Test
	public void instantiateListWithClasses() throws InjectingException {
		final List<?> beans = new ArrayList<>(Arrays.asList(new Class[] { EmptyBean.class, SingletonBean.class, EmptyBean.class }));
		Configuration configuration = new Configuration() {
			@Override
			protected void configure() {
				this.bind(beans).named("myList");
			}
		};
		configuration.configure();

		List<?> myList = beanFactory.create(new ClassInspector(configuration.bindings).inspect(List.class, "myList"));
		assertThat(myList).hasSize(3);
		assertThat(myList.get(0)).isInstanceOf(EmptyBean.class);
		assertThat(myList.get(1)).isInstanceOf(SingletonBean.class);
		assertThat(myList.get(2)).isInstanceOf(EmptyBean.class);
		
		List<?> myAnotherList = beanFactory.create(new ClassInspector(configuration.bindings).inspect(List.class, "myList"));
		assertSame(myList.get(1), myAnotherList.get(1));
	}

	@Test(expected=BeanCreationExpection.class)
	public void instantiateInterfaceWillThrowBeanCreationException() throws InjectingException {
		Bean<SimpleInterface> simpleInterfaceBean = new Bean<SimpleInterface>(null, "key", false, SimpleInterface.class, null, null, null);
		beanFactory.create(simpleInterfaceBean);
	}
	
	@Test
	public void instantiateListWithClassesWithDependencies() throws InjectingException {
		final List<?> beans = new ArrayList<>(Arrays.asList(new Class[] { BeanWithConstructorWithMultipleLevelDependencies.class, ComplexBean.class }));
		Configuration configuration = new Configuration() {
			@Override
			protected void configure() {
				this.bind(beans).named("myList");
				this.bind(SimpleInterface.class).to(NamedEmptyBeanA.class, NamedEmptyBeanB.class, NamedSingleton.class);
			}
		};
		configuration.configure();

		List<?> myList = beanFactory.create(new ClassInspector(configuration.bindings).inspect(List.class, "myList"));
		assertThat(myList).hasSize(2);
		assertThat(myList.get(0)).isInstanceOf(BeanWithConstructorWithMultipleLevelDependencies.class);
		assertThat(myList.get(1)).isInstanceOf(ComplexBean.class);
		
		BeanWithConstructorWithMultipleLevelDependencies bean1 = (BeanWithConstructorWithMultipleLevelDependencies) myList.get(0);
		assertThat(bean1.getEmptyBean()).isNotNull();
		assertThat(bean1.getBeanWithConstructorWithSingleLevelDependencies()).isNotNull();
		assertThat(bean1.getEmptyBeanWithConstructor()).isNotNull();
		
		ComplexBean bean2 = (ComplexBean) myList.get(1);
		assertThat(bean2.getBeanA()).isNotNull();
		assertThat(bean2.getBeanB()).isNotNull();
		assertThat(bean2.getBeanWithConstructorWithSingleLevelDependencies()).isNotNull();
		
	}

	@Test
	public void instantiateMaptWithClasses() throws InjectingException {
		final Map<String,Object> map = new HashMap<>();
		map.put("a", EmptyBean.class);
		map.put("b", SingletonBean.class);
		map.put("c", EmptyBean.class);
		
		Configuration configuration = new Configuration() {
			@Override
			protected void configure() {
				this.bind(map).named("myMap");
			}
		};
		configuration.configure();

		@SuppressWarnings("unchecked")
		Map<String,Object> myMap = beanFactory.create(new ClassInspector(configuration.bindings).inspect(Map.class, "myMap"));
		
		assertThat(myMap).hasSize(3);
		assertThat(myMap.get("a")).isInstanceOf(EmptyBean.class);
		assertThat(myMap.get("b")).isInstanceOf(SingletonBean.class);
		assertThat(myMap.get("c")).isInstanceOf(EmptyBean.class);
		
		@SuppressWarnings("unchecked")
		Map<String,Object> myOtherMap = beanFactory.create(new ClassInspector(configuration.bindings).inspect(Map.class, "myMap"));
		
		assertThat(myMap.get("a") == myOtherMap.get("a")).isTrue();
	}

	@Test
	public void instantiateMaptWithInstancesAndClasses() throws InjectingException {
		final Map<String,Object> map = new HashMap<>();
		map.put("a", new EmptyBean());
		map.put("b", SingletonBean.class);
		map.put("c", EmptyBean.class);
		
		Configuration configuration = new Configuration() {
			@Override
			protected void configure() {
				this.bind(map).named("myMap");
			}
		};
		configuration.configure();

		@SuppressWarnings("unchecked")
		Map<String,Object> myMap = beanFactory.create(new ClassInspector(configuration.bindings).inspect(Map.class, "myMap"));
		
		assertThat(myMap).hasSize(3);
		assertThat(myMap.get("a")).isInstanceOf(EmptyBean.class);
		assertThat(myMap.get("b")).isInstanceOf(SingletonBean.class);
		assertThat(myMap.get("c")).isInstanceOf(EmptyBean.class);
	}

	@Test
	public void instantiateListWithInstancesAndClasses() throws InjectingException {
		final List<?> beans = new ArrayList<>(Arrays.asList(new Object[] { new EmptyBean(), SingletonBean.class, EmptyBean.class }));
		Configuration configuration = new Configuration() {
			@Override
			protected void configure() {
				this.bind(beans).named("myList");
			}
		};
		configuration.configure();

		List<?> myList = beanFactory.create(new ClassInspector(configuration.bindings).inspect(List.class, "myList"));
		assertThat(myList).hasSize(3);
		assertThat(myList.get(0)).isInstanceOf(EmptyBean.class);
		assertThat(myList.get(1)).isInstanceOf(SingletonBean.class);
		assertThat(myList.get(2)).isInstanceOf(EmptyBean.class);
	}

	@Test
	public void instantiateEmptyBean() throws InjectingException {
		EmptyBean bean = beanFactory.create(beanInspector.inspect(EmptyBean.class));
		assertThat(bean).isNotNull();
	}

	@Test
	public void instantiateEmptyBeanWithConstructor() throws InjectingException {
		EmptyBeanWithConstructor bean = beanFactory.create(beanInspector.inspect(EmptyBeanWithConstructor.class));
		assertThat(bean).isNotNull();
	}

	@Test
	public void instantiateBeanWithConstructorWithSingleLevelDependencies() throws InjectingException {
		BeanWithConstructorWithSingleLevelDependencies bean = beanFactory.create(beanInspector.inspect(BeanWithConstructorWithSingleLevelDependencies.class));
		assertThat(bean).isNotNull();
	}

	@Test
	public void BeanWithConstructorWithMultipleLevelDependencies() throws InjectingException {
		BeanWithConstructorWithMultipleLevelDependencies bean = beanFactory.create(beanInspector.inspect(BeanWithConstructorWithMultipleLevelDependencies.class));
		assertThat(bean).isNotNull();
	}

	@Test
	public void whenInstantiatingSignletonBeanAlwaysReturnSameInstance() throws InjectingException {
		Bean<SingletonBean> singletonBean = beanInspector.inspect(SingletonBean.class);

		SingletonBean singleton1 = beanFactory.create(singletonBean);
		SingletonBean singleton2 = beanFactory.create(singletonBean);
		assertThat(singleton1).isSameAs(singleton2);
	}

	@Test
	public void instantiateBeanWithMultipleDependenciesAndAnnotations() throws InjectingException {
		Bean<SingletonBean> singletonBean = beanInspector.inspect(SingletonBean.class);

		SingletonBean singleton1 = beanFactory.create(singletonBean);
		SingletonBean singleton2 = beanFactory.create(singletonBean);
		assertThat(singleton1).isSameAs(singleton2);
	}

	@Test
	public void instantiateComplexBean() throws InjectingException {
		configuration.bind(SimpleInterface.class).to(NamedEmptyBeanA.class, NamedEmptyBeanB.class, NamedSingleton.class);
		Bean<ComplexBean> complexBean = beanInspector.inspect(ComplexBean.class);

		ComplexBean complexBean1 = beanFactory.create(complexBean);
		ComplexBean complexBean2 = beanFactory.create(complexBean);
		assertThat(complexBean1).isNotSameAs(complexBean2);

		assertDifferent(complexBean1.getBeanA(), complexBean2.getBeanA());
		assertDifferent(complexBean1.getBeanB(), complexBean2.getBeanB());
		assertDifferent(complexBean1.getBeanWithConstructorWithSingleLevelDependencies(), complexBean2.getBeanWithConstructorWithSingleLevelDependencies());
		assertDifferent(complexBean1.getEmptyBeanWithConstructor(), complexBean2.getEmptyBeanWithConstructor());

		assertSame(complexBean1.getNamedSingleton(), complexBean2.getNamedSingleton());
		assertSame(complexBean1.getSingletonBean(), complexBean2.getSingletonBean());
	}

	private void assertNotNull(Object o1, Object o2) {
		assertThat(o1).isNotNull();
		assertThat(o2).isNotNull();
	}

	private void assertSame(Object o1, Object o2) {
		assertNotNull(o1, o2);
		assertThat(o1).isSameAs(o2);
	}

	private void assertDifferent(Object o1, Object o2) {
		assertNotNull(o1, o2);
		assertThat(o1).isNotSameAs(o2);
	}

}
