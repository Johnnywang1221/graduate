package fz.test;

import java.util.Random;

import fz.conf.Configuration;
import fz.helper.RandomHelper;
import junit.framework.TestCase;

public class ConfigurationTest extends TestCase {
	public void testConfig() {
		Configuration conf = Configuration.sharedConfiguration();
		System.out.println(conf);
		double pro = 0.2;
		int isCount = 0;
		for(int i = 0;i < 100;i++){
			if(RandomHelper.rollWithProbability(pro)){
				System.out.println("ÊÇ");
				isCount++;
			}
			else {
				System.out.println("·ñ");
			}
		}
		System.out.println("ÊÇ"+isCount+"¸ö"+"/20");
	}

}
