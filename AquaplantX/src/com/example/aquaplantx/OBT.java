package com.example.aquaplantx;

public class OBT implements Work{
	public void work(){
		GetTime.myLabel.setText(GetTime.mybt.openBT());
	}
	
	public String toString(){
		return "Connectar Bluetooth";
	}

}
