package fz.bean;

import fz.bean.Location;

public class Task {
	private long id;
	private long gridId;
	private long taskDescriptionId;
	
	private int roundNumber;//µÚ¼¸ÂÖ
	private double subBudget;
	private int dataItemNumber;
	private int remainDataNumner;
	private double remainSubBudger;
	public Task(long gridId, long taskDescriptionId, int roundNumber, double subBudget, int dataItemNumber) {
		super();
		this.gridId = gridId;
		this.taskDescriptionId = taskDescriptionId;
		this.roundNumber = roundNumber;
		this.subBudget = subBudget;
		this.dataItemNumber = dataItemNumber;
	}
	public int getRemainDataNumner() {
		return remainDataNumner;
	}
	public void setRemainDataNumner(int remainDataNumner) {
		this.remainDataNumner = remainDataNumner;
	}
	public double getRemainSubBudger() {
		return remainSubBudger;
	}
	public void setRemainSubBudger(double remainSubBudger) {
		this.remainSubBudger = remainSubBudger;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Task() {
	}
	public long getGridId() {
		return gridId;
	}
	public void setGridId(long gridId) {
		this.gridId = gridId;
	}
	public long getTaskDescriptionId() {
		return taskDescriptionId;
	}
	public void setTaskDescriptionId(long taskDescriptionId) {
		this.taskDescriptionId = taskDescriptionId;
	}
	public int getRoundNumber() {
		return roundNumber;
	}
	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}
	public double getSubBudget() {
		return subBudget;
	}
	public void setSubBudget(double subBudget) {
		this.subBudget = subBudget;
	}
	public int getDataItemNumber() {
		return dataItemNumber;
	}
	public void setDataItemNumber(int dataItemNumber) {
		this.dataItemNumber = dataItemNumber;
	}
	
	
	

}
