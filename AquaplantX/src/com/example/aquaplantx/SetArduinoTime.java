package com.example.aquaplantx;

public class SetArduinoTime implements Work{
	public void work(){
    	GetTime.mybt.setTime();
	}
	public String toString(){
		return "Acerta Hora";
	}
}
