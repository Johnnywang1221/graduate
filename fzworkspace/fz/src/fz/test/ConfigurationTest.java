package fz.test;

import java.util.List;
import java.util.Random;

import fz.bean.Grid;
import fz.bean.User;
import fz.conf.Configuration;
import fz.dao.UserDao;
import fz.helper.RandomHelper;
import junit.framework.TestCase;

public class ConfigurationTest extends TestCase {
	public void tConfig() {
		Configuration conf = Configuration.sharedConfiguration();
		System.out.println(conf);
		double pro = 0.2;
		int isCount = 0;
		for(int i = 0;i < 100;i++){
			if(RandomHelper.rollWithProbability(pro)){
				System.out.println("��");
				isCount++;
			}
			else {
				System.out.println("��");
			}
		}
		System.out.println("��"+isCount+"��"+"/20");
	}
	public void tGaussian() {
		Configuration conf = Configuration.sharedConfiguration();
		for(int i = 0;i < 100;i++){
			System.out.println("��֪����Ϊ"+RandomHelper.nextGaussian());
		}
		
	}
	public void tUserDao(){
		UserDao userDao = new UserDao();
		Grid grid = new Grid();
		grid.setId(7L);
		List<User> users = userDao.listUsersInGrid(grid);
		System.out.println(users.size()+"ge zai 7 hao quyu");
	}
	
	public void testEntry(){
		tUserDao();
	}

}
