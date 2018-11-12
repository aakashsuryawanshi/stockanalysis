package com.stock.analysis.entities;

import java.util.HashSet;
import java.util.Set;


public class WatchList {
	private static WatchList myObj;
    Set<Company> list;
    static{
        myObj = new WatchList();
    }
     
    private WatchList(){
    	list = new HashSet<Company>();
    }
     
    public static WatchList getInstance(){
        return myObj;
    }

	public Set<Company> getList() {
		return list;
	}

	public void setList(Set<Company> list) {
		this.list = list;
	}
    
}
