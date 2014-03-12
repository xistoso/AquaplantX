package com.example.aquaplantx;

public class SetPumpTimes implements Work{
	public int work(){
		GetTime.mymenu.setMenu(6);
		return 0;
	}
	
	public String toString(){
		return "Dosagem e Bombas";
	}

}
