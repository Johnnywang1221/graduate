package fz.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import fz.bean.Task;
import fz.bean.User;


public class IncentiveBidding extends IncentiveBase {
	public List<User> selectUsers(List<User> users, Task task){
		//选出开销小于奖励的用户，假设用户都是功利型用户
		List<User> willingUsers = new ArrayList<>(users);
		Collections.sort(willingUsers);
		List<User> selectedUsers = new ArrayList<>();
		int selectedNum = task.getRemainDataNumber();
		if (willingUsers!=null && willingUsers.size()!= 0) {
			if (willingUsers.size()<=selectedNum) {
				selectedUsers.addAll(willingUsers);
			}else{
				selectedUsers = willingUsers.subList(0, task.getRemainDataNumber());
			}
		}
		task.setRemainDataNumber(task.getRemainDataNumber() - selectedUsers.size());
		
		return selectedUsers;
	}
	public double allocateReward(Task task, User user){
		
		double reward = user.getPsCost();
		
		task.setRemainSubBudget(task.getRemainSubBudget()-reward);
		return reward;
	}


}
