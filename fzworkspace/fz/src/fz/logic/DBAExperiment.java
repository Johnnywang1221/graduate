package fz.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import fz.bean.Grid;
import fz.bean.Task;
import fz.bean.TaskExecRecord;
import fz.bean.User;
import fz.bean.UserParticipateType;
import fz.conf.Configuration;
import fz.dao.GridDao;
import fz.dao.RecordDao;
import fz.dao.TaskDao;
import fz.dao.UserDao;
import fz.helper.RandomHelper;

public class DBAExperiment {
	private Configuration configuration;
	private List<Grid> grids;
	private List<User> recruitUsers;
	private int currentRoundNumber;
	private int currentTimeSlot;
	private double subBudget;
	public DBAExperiment(){
		this.grids = new ArrayList<>();
		this.recruitUsers = new ArrayList<>();
		this.configuration = Configuration.sharedConfiguration();
		subBudget = configuration.getTotalBudget()/configuration.getRoundNumber()/(configuration.getGridNumber()*configuration.getGridNumber());

	}
	void initExperiment(){
		//�����ռ䲼��
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
		//����������
		int userNum = configuration.getRecruitUserNumber();
		//imrΪ��Ȥ���û��͹������û���������ֵ��irΪ��Ȥ���û�ռ�����ֵ 
		double imr = configuration.getInterestMoneyRatio();
		double ir = 1-1/(1+imr);
		for(int i = 0;i<userNum;i++){
			
			User user = null;
			double psCost = RandomHelper.nextGaussian();
			int gridIdx = RandomHelper.rollIntBetweenIncludeBoth(0, gridNumber*gridNumber-1);
			if (RandomHelper.rollWithProbability(ir)) {
				//������Ȥ���û�
				user = new User(UserParticipateType.InterestType, psCost, configuration.getRoIThresholdInterestType(), (Grid)grids.get(gridIdx));
				
			}else {
				user = new User(UserParticipateType.MoneyType, psCost, configuration.getRoIThresholdMoneyType(), (Grid)grids.get(gridIdx));
			}
			recruitUsers.add(user);
		}
		//grid ��  user ��Ϣд�����ݿ�

		
		UserDao.addUsers(recruitUsers);
	}
	
	public void participatorySensing(){

		//�ظ�roundnumber��
		for(currentRoundNumber = 1;currentRoundNumber <= configuration.getRoundNumber();currentRoundNumber++ ){
			grids = GridDao.listAllGrids();
			Set<Task> tasks = new HashSet<>();
			System.out.println("---------------��"+currentRoundNumber+"������----------------");
			
			//��grid��������
			for (Grid grid : grids) {
				//��������
				Task task = new Task();
				task.setRoundNumber(currentRoundNumber);
				task.setGridBelongTo(grid);
				task.setGridId(grid.getId());
				//task.setCurrentTimeSlot(currentTimeSlot);
				task.setDataItemNumber(configuration.getDataItemNumber());
				task.setSubBudget(subBudget/2);
				task.setRemainDataNumber(task.getDataItemNumber());
				task.setRemainSubBudget(task.getSubBudget());
				task.setCompleted(false);
				
				tasks.add(task);
				TaskDao.addTask(task);
				//��ѯ��grid�е�user			
			}
			
			for(currentTimeSlot = 1;currentTimeSlot<=configuration.getTimeSlotPerRound();currentTimeSlot++){
				if(tasks.size()==0){
					System.out.println("---------------��"+currentRoundNumber+"������ִ����ϣ����񼯺�Ϊ��----------------");
					break;
				}else{
					//timeslot��ʼ
					//�ƶ��û�
					System.out.println("---------------��"+currentTimeSlot+"��ʱ��Ƭ----------------");
					//����Ԥ��
					Iterator<Task> iterator = tasks.iterator();
					while(iterator.hasNext())
					{
						Task taskInExecuting = iterator.next();
						//����task��Ԥ��
						taskInExecuting.setRemainSubBudget(taskInExecuting.getRemainSubBudget()+subBudget*Math.pow(0.5, configuration.getTimeSlotPerRound()-currentTimeSlot+2));
						//
						
						//TaskDao.updateTaskInfo(taskInExecuting);
						
					}
					for(Grid grid:grids){
						grid.clearUser();
					}
					IncentiveDBA incentiveBase = new IncentiveDBA();
					
					for(User user: recruitUsers){
						List<Task> nearByTasks = new ArrayList<>();
						Grid grid = user.getGridBelongto();
						for(Task task:tasks){
							if(task.getGridId()==grid.getId()){
								continue;
							}
							if(task.getGridBelongTo().next2Grid(grid)){
								nearByTasks.add(task);
							}
						}
						List<Task> nearByTasksCopy = new ArrayList<>(nearByTasks);
						
						if(nearByTasksCopy!=null && nearByTasksCopy.size()>0){
							Collections.sort(nearByTasksCopy);
							boolean flag = false;
							for(Task mostAttractiveTask:nearByTasksCopy){
								Grid g = mostAttractiveTask.getGridBelongTo();
								if(!g.isFull()){
									user.setGridBelongto(g);
									g.addUser();
									flag = true;
								}
							}
							if(!flag){
								incentiveBase.moveUser(user);
							}
							
						}
						else {
							incentiveBase.moveUser(user);
						}
						
						
					}
					UserDao.updateUserGridInfo(recruitUsers);
					//�����������е�����
					Iterator<Task> iterator2 = tasks.iterator();
					while(iterator2.hasNext())
					{
						Task taskInExecuting = iterator2.next();
						Grid grid = taskInExecuting.getGridBelongTo();
						
						List<User> usersInThisGrid = UserDao.listUsersInGrid(grid);
						//�û�ѡ�����
						List<User> selectedUsers = incentiveBase.selectUsers(usersInThisGrid, taskInExecuting);
						//��ѡ�е��û�ִ������
						if(selectedUsers!=null && selectedUsers.size()>0){
							double reward = incentiveBase.allocateReward(taskInExecuting, selectedUsers);
							for (User user : selectedUsers) {
								//����һ������ִ���嵥
								TaskExecRecord record = new TaskExecRecord();
								//��������
								record.setGridId(grid.getId());
								record.setUserId(user.getId());
								record.setRoundNum(currentRoundNumber);
								record.setTimeSlotNum(currentTimeSlot);
								record.setCost(user.getPsCost());
								record.setReward(reward); 
								RecordDao.addRecord(record);
							}
						}
						
						if (taskInExecuting.getRemainDataNumber() == 0) {
							iterator2.remove();
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
