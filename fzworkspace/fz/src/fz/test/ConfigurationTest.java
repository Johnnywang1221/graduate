package fz.test;

import fz.conf.Configuration;
import junit.framework.TestCase;

public class ConfigurationTest extends TestCase {
	public void testConfig() {
		Configuration conf = Configuration.sharedConfiguration();
		System.out.println(conf);
	}

}
