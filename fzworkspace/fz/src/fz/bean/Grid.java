package fz.bean;

public class Grid {
	private Long id;
	private int row;
	private int col;
	private double lat;
	private double lon;
	public Long getId() {
		return id;
	}
	public Grid() {
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
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
	

}
