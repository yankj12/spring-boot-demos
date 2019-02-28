package com.yan.springboot.voltdb.schema;

import java.io.Serializable;

public class Town implements Serializable{

	private static final long serialVersionUID = 1L;

	private String town;
    
    private String state;
    
    private Integer stateNum;
    
    private String county;
    
    private Integer countyNum;
    
    private Integer elevation;

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getStateNum() {
		return stateNum;
	}

	public void setStateNum(Integer stateNum) {
		this.stateNum = stateNum;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public Integer getCountyNum() {
		return countyNum;
	}

	public void setCountyNum(Integer countyNum) {
		this.countyNum = countyNum;
	}

	public Integer getElevation() {
		return elevation;
	}

	public void setElevation(Integer elevation) {
		this.elevation = elevation;
	}
    
}
