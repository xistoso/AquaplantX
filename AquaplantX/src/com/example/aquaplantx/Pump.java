package com.example.aquaplantx;


public class Pump implements Work{
	int num;
	public Pump(int n){
		num=n;
	}
	
	public int work(){
		GetTime.bomba = num;
		
		return 7;
	}
	
	public String toString(){
		return "Bomba" + num;
	}

}
