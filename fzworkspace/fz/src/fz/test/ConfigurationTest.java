package fz.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fz.bean.Grid;
import fz.bean.TaskExecRecord;
import fz.bean.User;
import fz.bean.UserParticipateType;
import fz.conf.Configuration;
import fz.dao.GridDao;
import fz.dao.RecordDao;
import fz.dao.TaskDao;
import fz.dao.UserDao;
import fz.helper.OutputHelper;
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
				System.out.println("是");
				isCount++;
			}
			else {
				System.out.println("否");
			}
		}
		System.out.println("是"+isCount+"个"+"/20");
	}
	public void tGaussian() {
		Configuration conf = Configuration.sharedConfiguration();
		for(int i = 0;i < 100;i++){
			System.out.println("感知开销为"+RandomHelper.nextGaussian());
		}
		
	}
	public void tUserDao(){
		UserDao userDao = new UserDao();
		Grid grid = new Grid();
		grid.setId(7L);
		List<User> users = userDao.listUsersInGrid(grid);
		System.out.println(users.size()+"ge zai 7 hao quyu");
	}
	public void tUserDaoUpdate(){
		Grid grid = new Grid();
		grid.setRow(11);
		grid.setCol(1);
		User newUser = new User(UserParticipateType.InterestType, 50, 1.0, grid);
		//newUser.setGridId(101);
		UserDao.addUser(newUser);
		grid.setCol(2);
		grid.updateGridId();
		newUser.setGridId(grid.getId());
		List<User> users = new ArrayList<>();
		users.add(newUser);
		UserDao.updateUserGridInfo(users);
		
	}
	public void tRandomNNum(){
		List<Integer> nNum = RandomHelper.rollNIntBetweenIncludeBoth(1, 9, 8);
		System.out.println(nNum.toString());
	}
	public void tUserLocation(){
		List<User> users = UserDao.listAllUsers();
		int edgeCount = 0;
		for (User user : users) {
			Grid grid = GridDao.listGridByGridId(user.getGridId());
			if(grid.isEdgeGrid()){
				edgeCount++;
			}
		}
		System.out.println(edgeCount+"/"+users.size());
	}
	public void tGrids(){
		List<Grid> grids = GridDao.listAllGrids();
		System.out.println(grids.size());
	}
	public void tRecord(){
//		double totalCost = RecordDao.totalCost();
//		double totalReward = RecordDao.totalReward();
//		System.out.println("totalCost"+ totalCost);
//		System.out.println("totalReward"+ totalReward);
//		System.out.println("totalDataItems"+ RecordDao.totalDataItems());
//		System.out.println("totaltasks"+ TaskDao.totalCompletedTasks());
//		long totaldataitems = 0;
//		List<Long>  totaldata = new ArrayList<>();
		List<Double>  totalcost = new ArrayList<>();
		List<Double>  totalreward = new ArrayList<>();
		List<Long> totalCompletedTaskInRound = new ArrayList<>();
		for(int i = 1;i<=10;i++){
			
			//totalcost.add(RecordDao.totalCostInRound(i));
			//totalreward.add(RecordDao.totalRewardInRound(i));
			totalCompletedTaskInRound.add(TaskDao.totalCompletedTasksInRound(i));
		}
		//String reString = "costPerRound\n"+totalcost.toString()+"\n------------"+"reward\n"+totalreward.toString();
		System.out.println(totalCompletedTaskInRound.toString());
		//OutputHelper.saveString2File(totalCompletedTaskInRound.toString(), "src/itemsNumPerRound.txt");
		
	}
	public void tUserNumber(){
//		double totalCost = RecordDao.totalCost();
//		double totalReward = RecordDao.totalReward();
//		System.out.println("totalCost"+ totalCost);
//		System.out.println("totalReward"+ totalReward);
//		System.out.println("totalDataItems"+ RecordDao.totalDataItems());
//		System.out.println("totaltasks"+ TaskDao.totalCompletedTasks());
//		long totaldataitems = 0;
//		List<Long>  totaldata = new ArrayList<>();
		List<Long> userNumberInRound = new ArrayList<>();
		for(int i = 1;i<=10;i++){
			
			//totalcost.add(RecordDao.totalCostInRound(i));
			//totalreward.add(RecordDao.totalRewardInRound(i));
			userNumberInRound.add(RecordDao.totalUsersInRound(i));
		}
		//String reString = "costPerRound\n"+totalcost.toString()+"\n------------"+"reward\n"+totalreward.toString();
		System.out.println(userNumberInRound.toString());
		//OutputHelper.saveString2File(totalCompletedTaskInRound.toString(), "src/itemsNumPerRound.txt");
		
	}
	public void tUserReward(){
//		double totalCost = RecordDao.totalCost();
//		double totalReward = RecordDao.totalReward();
//		System.out.println("totalCost"+ totalCost);
//		System.out.println("totalReward"+ totalReward);
//		System.out.println("totalDataItems"+ RecordDao.totalDataItems());
//		System.out.println("totaltasks"+ TaskDao.totalCompletedTasks());
//		long totaldataitems = 0;
//		List<Long>  totaldata = new ArrayList<>();
		List<Double> userReward = RecordDao.totalRewardOfUsers();
		
		//String reString = "costPerRound\n"+totalcost.toString()+"\n------------"+"reward\n"+totalreward.toString();
		System.out.println(userReward.toString());
		//OutputHelper.saveString2File(totalCompletedTaskInRound.toString(), "src/itemsNumPerRound.txt");
		
	}
	public void testEntry(){
		//tRandomNNum();
		//tUserLocation();
		//tGrids();
		//tUserNumber();
		System.out.println(RecordDao.totalUsers());
		tUserNumber();
	}

}
