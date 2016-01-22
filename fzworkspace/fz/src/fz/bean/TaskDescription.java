   package fz.bean;

public class TaskDescription {
	private long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	private Location taskPoint;
	private double taskRadius;
	private int gridNumber;//边长，正方形，总数为girdNumber*gridNumber
	public TaskDescription() {
	}
	public Location getTaskPoint() {
		return taskPoint;
	}
	public void setTaskPoint(Location taskPoint) {
		this.taskPoint = taskPoint;
	}
	public double getTaskRadius() {
		return taskRadius;
	}
	public void setTaskRadius(double taskRadius) {
		this.taskRadius = taskRadius;
	}
	public int getGridNumber() {
		return gridNumber;
	}
	public void setGridNumber(int gridNumber) {
		this.gridNumber = gridNumber;
	}
	public int getDataItemsNumberPerRoundPerGrid() {
		return dataItemsNumberPerRoundPerGrid;
	}
	public void setDataItemsNumberPerRoundPerGrid(int dataItemsNumberPerRoundPerGrid) {
		this.dataItemsNumberPerRoundPerGrid = dataItemsNumberPerRoundPerGrid;
	}
	public int getRoundNumber() {
		return roundNumber;
	}
	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}
	public double getTotalBudget() {
		return totalBudget;
	}
	public void setTotalBudget(double totalBudget) {
		this.totalBudget = totalBudget;
	}
	private int dataItemsNumberPerRoundPerGrid;
	private int roundNumber;
	private double totalBudget;
	private int recruitUserNum;
	public int getRecruitUserNum() {
		return recruitUserNum;
	}
	public void setRecruitUserNum(int recruitUserNum) {
		this.recruitUserNum = recruitUserNum;
	}
	

}
