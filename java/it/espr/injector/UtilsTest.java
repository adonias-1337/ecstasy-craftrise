package it.espr.injector;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UtilsTest {

	@Test
	public void whenNameIsNullThenKeyContainsClassNameOnly() {
		assertThat(Utils.key(null, this.getClass())).isEqualTo(this.getClass().getCanonicalName());
	}

	@Test
	public void whenGettingKeyForInstanceItsTheSameAsForItsClass() {
		String test = "test";
		assertThat(Utils.key(null, test)).isEqualTo(Utils.key(null, test.getClass()));
	}

	@Test
	public void whenNameIsEmptyThenKeyContainsClassNameOnly() {
		assertThat(Utils.key(" ", this.getClass())).isEqualTo(this.getClass().getCanonicalName());
	}

	@Test
	public void whenNameIsNotEmptyThenKeyContainsBothNameAndClassName() {
		assertThat(Utils.key("my-key", this.getClass())).isEqualTo("my-key-" + this.getClass().getCanonicalName());
	}

	@Test
	public void whenValueIsNullThenIsEmptyIsTrue() {
		assertThat(Utils.isEmpty(null)).isTrue();
	}

	@Test
	public void whenValueIsEmptyStringThenIsEmptyIsTrue() {
		assertThat(Utils.isEmpty("   ")).isTrue();
	}

	@Test
	public void whenValueIsNotEmptyStringTheIsEmptyIsFalse() {
		assertThat(Utils.isEmpty("  df dfs ")).isFalse();
	}

	@Test
	public void whenAnnotationsContainsNamedAnnotationThenItsValueIsReturned() {
		String expected = "named value";

		List<Annotation> annotations = new ArrayList<>();

		annotations.add(mockAnnotation(Inject.class));
		annotations.add(mockAnnotation(Deprecated.class));

		Named named = this.mockAnnotation(Named.class);
		doReturn(expected).when(named).value();
		annotations.add(named);

		annotations.add(mockAnnotation(Singleton.class));

		String result = Utils.getAnnotationValue(Named.class, annotations.toArray(new Annotation[] {}));
		assertThat(result).isEqualTo(expected);
	}

	@Test
	public void whenAnnotationsContainsNamedAnnotationWithEmptyValueThenNullReturned() {
		List<Annotation> annotations = new ArrayList<>();

		annotations.add(mockAnnotation(Inject.class));
		annotations.add(mockAnnotation(Deprecated.class));

		Named named = this.mockAnnotation(Named.class);
		doReturn("  ").when(named).value();
		annotations.add(named);

		annotations.add(mockAnnotation(Singleton.class));

		String result = Utils.getAnnotationValue(Named.class, annotations.toArray(new Annotation[] {}));
		assertThat(result).isEqualTo(null);
	}

	@Test
	public void whenAnnotationsDontContainNamedAnnotationThenNullIsReturned() {
		List<Annotation> annotations = new ArrayList<>();

		annotations.add(mockAnnotation(Inject.class));
		annotations.add(mockAnnotation(Deprecated.class));
		annotations.add(mockAnnotation(Singleton.class));

		String result = Utils.getAnnotationValue(Named.class, annotations.toArray(new Annotation[] {}));
		assertThat(result).isNull();
	}

	@Test
	public void whenAnnotationNotSupportedThenReturnNull() {
		List<Annotation> annotations = new ArrayList<>();

		annotations.add(mockAnnotation(Inject.class));
		annotations.add(mockAnnotation(Deprecated.class));
		annotations.add(mockAnnotation(Singleton.class));

		String result = Utils.getAnnotationValue(Deprecated.class, annotations.toArray(new Annotation[] {}));
		assertThat(result).isNull();
	}

	@Test
	public void whenAnnotationsAreEmptyThenNullIsReturned() {
		List<Annotation> annotations = new ArrayList<>();

		String result = Utils.getAnnotationValue(Named.class, annotations.toArray(new Annotation[] {}));
		assertThat(result).isNull();
	}

	@Test
	public void whenAnnotationsAreNullThenNullIsReturned() {
		String result = Utils.getAnnotationValue(Named.class, null);
		assertThat(result).isNull();
	}

	@Test
	public void whenMemberHasPublicModifierThenTrueIsReturned() {
		Member member = mock(Member.class);
		doReturn(Modifier.ABSTRACT + Modifier.INTERFACE + Modifier.PUBLIC + Modifier.STATIC).when(member).getModifiers();
		assertThat(Utils.isPublic(member)).isTrue();
	}

	@Test
	public void whenMemberHasNoPublicModifierThenFalseIsReturned() {
		Member member = mock(Member.class);
		doReturn(Modifier.ABSTRACT + Modifier.INTERFACE + Modifier.STATIC).when(member).getModifiers();
		assertThat(Utils.isPublic(member)).isFalse();
	}

	private <Type extends Annotation> Type mockAnnotation(Class<Type> clazz) {
		Type annotation = mock(clazz);
		doReturn(clazz).when(annotation).annotationType();
		return annotation;
	}
}
