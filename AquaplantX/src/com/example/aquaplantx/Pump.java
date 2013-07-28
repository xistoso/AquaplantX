package com.example.aquaplantx;

public class Pump implements Work{
	int num;
	public Pump(int n){
		num=n;
	}
	
	public void work(){
		GetTime.timeLabel.setText("Bomba" + num);
		GetTime.bomba = num;
		GetTime.mymenu.setMenu(7);
	}
	
	public String toString(){
		return "Bomba" + num;
	}

}
