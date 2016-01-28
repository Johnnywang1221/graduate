package fz.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fz.bean.Direction;
import fz.bean.Grid;
import fz.bean.Task;
import fz.bean.User;
import fz.conf.Configuration;
import fz.helper.RandomHelper;

public class IncentiveDBA extends IncentiveBase {
	public List<User> selectUsers(List<User> users, Task task){
		//ѡ������С�ڽ������û��������û����ǹ������û�
				List<User> willingUsers = new ArrayList<>();
				for (User user : users) {
					if(user.getPsCost()<=computeReward(task, user)){
						willingUsers.add(user);
					}
				}
				List<User> selectedUsers = new ArrayList<>();
				int selectedNum = task.getRemainDataNumber();
				if (willingUsers!=null && willingUsers.size()!= 0) {
					if (willingUsers.size()<=selectedNum) {
						selectedUsers.addAll(willingUsers);
					}else{
						List<Integer> indexList = RandomHelper.rollNIntBetweenIncludeBoth(0, willingUsers.size()-1, selectedNum);
						for (Integer integer : indexList) {
							selectedUsers.add(willingUsers.get(integer));
						}
					}
				}
				
				
				return selectedUsers;
	}
	public double computeReward(Task task, User user){
		double budget = task.getRemainSubBudget();
		int dataItemNum = task.getRemainDataNumber();
		double reward = budget/dataItemNum;
		
		//task.setRemainSubBudget(task.getRemainSubBudget()-reward);
		return reward;
	}
	public double allocateReward(Task task, List<User> users){
		
		double budget = task.getRemainSubBudget();
		int dataItemNum = task.getRemainDataNumber();
		double reward = budget/dataItemNum;		
		task.setRemainSubBudget(task.getRemainSubBudget()-reward*users.size());
		task.setRemainDataNumber(task.getRemainDataNumber() - users.size());
		return reward;
	}
	
	public void moveUser(User user){
		//Ĭ��Ϊ�������ģ�ͣ����������д
		//�û�������ߵ����ڸ��ӣ����߲���
//		int row = user.getGridBelongto().getRow();
//		int col = user.getGridBelongto().getCol();
		int edgeNumber = Configuration.getInstance().getGridNumber();
		Grid grid = user.getGridBelongto();
		Direction direction = RandomHelper.nextDirection();
		switch (direction) {
		case Top:
			if (grid.isEdgeGridDirection(Direction.Top)) {
				//do nothing
				grid.setRow(edgeNumber);
			}
			else{
				grid.setRow(grid.getRow()-1);
			}
			break;
		case Left:
			if (grid.isEdgeGridDirection(Direction.Left)) {
				grid.setCol(Configuration.getInstance().getGridNumber());
				
			}
			else {
				grid.setCol(grid.getCol()-1);
			}
			break;
		case Bottom:
			if (grid.isEdgeGridDirection(Direction.Bottom)) {
				grid.setRow(1);
			}
			else {
				grid.setRow(grid.getRow() + 1);
			}
			break;
		case Right:
			if (grid.isEdgeGridDirection(Direction.Right)) {
				grid.setCol(1);
			}
			else {
				grid.setCol(grid.getCol() + 1);
			}
			break;

		default:
			break;
		}
		grid.updateGridId();
	}

}
