package fz.bean;

import fz.conf.Configuration;

public class Grid {
	//private static final int edge = Configuration.getInstance().getGridNumber(); 
	private long id;
	private int row;
	private int col;
	private double lat;
	private double lon;
	private int userInGridCount;
	public void clearUser(){
		userInGridCount = 0;
	}
	public void addUser() {
		userInGridCount++;
		
	}
	public boolean isFull() {
		return userInGridCount == Configuration.getInstance().getDataItemNumber();
	}
	public long getId() {
		return id;
	}
	public Grid() {
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
		//updateGridId();
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
		//updateGridId();
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public void updateGridId(){
		this.id = (row-1)*10 + col;
	}
	public boolean isEdgeGrid(){
		int edge = Configuration.getInstance().getGridNumber(); 
		if (row == 1 || row == edge || col == 1 || col == edge) {
			return true;
		}
		return false;
	}
	public boolean isEdgeGridDirection (Direction direction) {
		int edge = Configuration.getInstance().getGridNumber(); 
		if(isEdgeGrid()){
			switch (direction) {
			case Top:
				return row == 1;
				
			case Left:
				return col == 1;
				
			case Bottom:
				return row == edge;
				
			case Right:
				return col == edge;
				
				
			default:
				return false;
				
			}
		}
		return false;
	}
	public boolean isTopLeftCorner(){
		return isEdgeGridDirection(Direction.Top) && isEdgeGridDirection(Direction.Left);
	}
	public boolean isBottomLeftCorner(){
		return isEdgeGridDirection(Direction.Bottom) && isEdgeGridDirection(Direction.Left);
	}
	public boolean isTopRightCorner(){
		return isEdgeGridDirection(Direction.Top) && isEdgeGridDirection(Direction.Right);
	}
	public boolean isBottomRightCorner(){
		return isEdgeGridDirection(Direction.Bottom) && isEdgeGridDirection(Direction.Right);
	}
	public boolean next2Grid(Grid grid){
		boolean result = false;
		if(grid.getCol()==this.col){
			int difRow = grid.getRow()-this.row;
			difRow = difRow>0?difRow:-difRow;
			if(difRow == 1 || difRow == (Configuration.getInstance().getGridNumber()-1)){
				result =  true;
			}
		}
		if(grid.getRow() == this.row){
			int difCol = grid.getCol()-this.col;
			difCol = difCol>0?difCol:-difCol;
			if(difCol == 1 || difCol == (Configuration.getInstance().getGridNumber()-1)){
				result =  true;
			}
		}
		return result;
	}

}
