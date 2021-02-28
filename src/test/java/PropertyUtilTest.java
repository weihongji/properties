import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class PropertyUtilTest {
	PropertyUtil properties = PropertyUtil.getInstance();

	@Before
	public void setUp() throws Exception {
		String address = "address";
		if (properties.containsKey(address)) {
			properties.getWrappedProperties().remove(address);
			properties.flush();
		}
	}

	@Test
	public void getProperty() {
		assertEquals("Jesse Wei", properties.getProperty("name"));
	}

	@Test
	public void getPropertyWithDefault() {
		assertEquals("xi'an", properties.getProperty("address", "xi'an"));
	}

	@Test
	public void getPropertyNames() {
		Set<String> names = properties.getPropertyNames();
		assertTrue("Existing fields: " + names.toString(), names.containsAll(Arrays.asList("name", "age", "email")));
	}

	@Test
	public void containsKey() {
		assertTrue(properties.containsKey("name"));
	}

	@Test
	public void setProperty() {
		String address = "xi'an";
		properties.setProperty("address", address);
		try {
			properties.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(address, properties.getProperty("address"));
	}

	@Test
	public void showAllProperties() {
		for (String key : properties.getPropertyNames()) {
			System.out.printf("%s: %s\r\n", key, properties.getProperty(key));
		}
	}
}