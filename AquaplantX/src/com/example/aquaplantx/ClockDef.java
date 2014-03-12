package com.example.aquaplantx;

public class ClockDef implements Work {
	public int work(){
		GetTime.mymenu.setMenu(3);
		return 0;
	}
	
	@Override
	public String toString(){
		return "Relógio"; 
	}
}
