package com.example.aquaplantx;

public class OBT implements Work{
	public void work(){
		GetTime.myLabel.setText(GetTime.mybt.openBT());
		if(GetTime.mybt.isOpened()){
			GetTime.mybt.read();
			GetTime.mymenu.setMenu(2);
		}
	}
	
	public String toString(){
		return "Connectar Bluetooth";
	}

}
