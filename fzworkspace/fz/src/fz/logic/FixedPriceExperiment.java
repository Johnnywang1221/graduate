package fz.logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import fz.bean.Grid;
import fz.bean.Task;
import fz.bean.TaskDescription;
import fz.bean.TaskExecRecord;
import fz.bean.User;
import fz.bean.UserParticipateType;
import fz.conf.Configuration;
import fz.dao.GridDao;
import fz.dao.RecordDao;
import fz.dao.TaskDao;
import fz.dao.UserDao;
import fz.helper.RandomHelper;

public class FixedPriceExperiment {
	private TaskDescription taskDescription;
	private Configuration configuration;
	private List<Grid> grids;
	private List<User> recruitUsers;
	private int currentRoundNumber;
	private int currentTimeSlot;
	private double subBudget;
	public FixedPriceExperiment(){
		this.grids = new ArrayList<>();
		this.recruitUsers = new ArrayList<>();
		this.configuration = Configuration.sharedConfiguration();
		subBudget = configuration.getTotalBudget()/configuration.getRoundNumber()/(configuration.getGridNumber()*configuration.getGridNumber());

	}
	void initExperiment(){
		//建立空间布局
		int gridNumber = configuration.getGridNumber();
		for(int i = 0;i < gridNumber;i++){
			for(int j = 0;j < gridNumber;j++){
				Grid grid = new Grid();
				grid.setRow(i+1);
				grid.setCol(j+1);
				grids.add(grid);
			}
		}
		GridDao.addGrids(grids);
		//参与者生成
		int userNum = configuration.getRecruitUserNumber();
		//imr为兴趣型用户和功利性用户的数量比值，ir为兴趣型用户占总体比值 
		double imr = configuration.getInterestMoneyRatio();
		double ir = 1-1/(1+imr);
		for(int i = 0;i<userNum;i++){
			
			User user = null;
			double psCost = RandomHelper.nextGaussian();
			int gridIdx = RandomHelper.rollIntBetweenIncludeBoth(0, gridNumber*gridNumber-1);
			if (RandomHelper.rollWithProbability(ir)) {
				//生成兴趣型用户
				user = new User(UserParticipateType.InterestType, psCost, configuration.getRoIThresholdInterestType(), (Grid)grids.get(gridIdx));
				
			}else {
				user = new User(UserParticipateType.MoneyType, psCost, configuration.getRoIThresholdMoneyType(), (Grid)grids.get(gridIdx));
			}
			recruitUsers.add(user);
		}
		//grid 和  user 信息写入数据库

		
		UserDao.addUsers(recruitUsers);
	}
	
	public void participatorySensing(){

		//重复roundnumber次
		for(currentRoundNumber = 1;currentRoundNumber <= configuration.getRoundNumber();currentRoundNumber++ ){
			grids = GridDao.listAllGrids();
			Set<Task> tasks = new HashSet<>();
			System.out.println("---------------第"+currentRoundNumber+"轮任务----------------");
			
			//按grid发布任务
			for (Grid grid : grids) {
				//生成任务
				Task task = new Task();
				task.setRoundNumber(currentRoundNumber);
				task.setGridBelongTo(grid);
				task.setGridId(grid.getId());
				//task.setCurrentTimeSlot(currentTimeSlot);
				task.setDataItemNumber(configuration.getDataItemNumber());
				task.setSubBudget(subBudget);
				task.setRemainDataNumber(task.getDataItemNumber());
				task.setRemainSubBudget(task.getSubBudget());
				task.setCompleted(false);
				
				tasks.add(task);
				TaskDao.addTask(task);
				//查询此grid中的user			
			}
			
			for(currentTimeSlot = 1;currentTimeSlot<=configuration.getTimeSlotPerRound();currentTimeSlot++){
				if(tasks.size()==0){
					System.out.println("---------------第"+currentRoundNumber+"轮任务执行完毕，任务集合为空----------------");
					break;
				}else{
					//timeslot开始
					//移动用户
					System.out.println("---------------第"+currentTimeSlot+"个时间片----------------");
					IncentiveBase incentiveBase = new IncentiveBase();
					for(User user: recruitUsers){
						incentiveBase.moveUser(user);
						
					}
					UserDao.updateUserGridInfo(recruitUsers);
					//遍历还在运行的任务
					Iterator<Task> iterator = tasks.iterator();
					while(iterator.hasNext())
					{
						Task taskInExecuting = iterator.next();
						//
						Grid grid = taskInExecuting.getGridBelongTo();
						
						List<User> usersInThisGrid = UserDao.listUsersInGrid(grid);
						//用户选择策略
						List<User> selectedUsers = incentiveBase.selectUsers(usersInThisGrid, taskInExecuting);
						//被选中的用户执行任务
						for (User user : selectedUsers) {
							//生成一个任务执行清单
							TaskExecRecord record = new TaskExecRecord();
							//产生开销
							record.setGridId(grid.getId());
							record.setUserId(user.getId());
							record.setRoundNum(currentRoundNumber);
							record.setTimeSlotNum(currentTimeSlot);
							record.setCost(user.getPsCost());
							record.setReward(incentiveBase.allocateReward(taskInExecuting, user));
							RecordDao.addRecord(record);
						}
						if (taskInExecuting.getRemainDataNumber() == 0) {
							iterator.remove();
							taskInExecuting.setCompleted(true);
							TaskDao.updateTaskInfo(taskInExecuting);
						}
						
						
						
												
					}
				}
			}
		if(tasks.size()>0){
			for (Task task : tasks) {
				TaskDao.updateTaskInfo(task);
			}
		}
			
		}
		

	}

}
