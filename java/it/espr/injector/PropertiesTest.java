package it.espr.injector;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PropertiesTest {

	@InjectMocks
	private Properties properties;

	@Test
	public void whenDefaultFileExistsPropertiesAreLoaded() {
		Map<String, String> props = properties.load();
		assertThat(props).isNotEmpty();
	}

	@Test
	public void whenSpecifiedFileExistsPropertiesAreLoaded() {
		Map<String, String> props = properties.load("/configuration.properties");
		assertThat(props).isNotEmpty();
	}

	@Test
	public void emptyPropertiesAreNotLoaded() {
		Map<String, String> props = properties.load("/configuration.properties");
		assertThat(props).isNotEmpty();
		assertThat(props).doesNotContainKey("empty.property");
	}

	@Test
	public void emptyLinesAreSkippedWhenLoading() {
		Map<String, String> props = properties.load("/configuration.properties");
		assertThat(props).hasSize(2);
	}

	@Test
	public void whenLoadingEmptyFileThenEmptyMapIsReturned() {
		Map<String, String> props = properties.load("/empty.properties");
		assertThat(props).isEmpty();
	}

	@Test
	public void whenSpecifiedFileWithoutTrailingSlashExistsPropertiesAreLoaded() {
		Map<String, String> props = properties.load("configuration.properties");
		assertThat(props).isNotEmpty();
	}

	@Test
	public void whenSpecifiedFileDoesntExistEmptyPropertiesAreReturned() {
		Map<String, String> props = properties.load("wrong.file");
		assertThat(props).isEmpty();
	}

}
