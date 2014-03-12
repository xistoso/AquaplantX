package com.example.aquaplantx;

public class SetArduinoTime implements Work{
	public int work(){
    	GetTime.mybt.setTime();
    	return 0;
	}
	public String toString(){
		return "Acerta Hora";
	}
}
