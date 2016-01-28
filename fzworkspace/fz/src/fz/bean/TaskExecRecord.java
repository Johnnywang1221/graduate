package fz.bean;

public class TaskExecRecord {
	private long id;

	private int roundNum;
	private int timeSlotNum;
	private long userId;
	private long gridId;
	private double cost;
	private double reward;
	
	public TaskExecRecord() {
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public int getRoundNum() {
		return roundNum;
	}
	public void setRoundNum(int roundNum) {
		this.roundNum = roundNum;
	}
	public int getTimeSlotNum() {
		return timeSlotNum;
	}
	public void setTimeSlotNum(int timeSlotNum) {
		this.timeSlotNum = timeSlotNum;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getGridId() {
		return gridId;
	}
	public void setGridId(long gridId) {
		this.gridId = gridId;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getReward() {
		return reward;
	}
	public void setReward(double reward) {
		this.reward = reward;
	}
	

}
