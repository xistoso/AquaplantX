package com.example.aquaplantx;

public class ClockDef implements Work {
	public void work(){
		GetTime.mymenu.setMenu(3);
	}
	
	@Override
	public String toString(){
		return "Relógio"; 
	}
}
