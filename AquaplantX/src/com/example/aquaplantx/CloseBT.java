package com.example.aquaplantx;

public class CloseBT implements Work{
	public int work(){
		GetTime.myLabel.setText(GetTime.mybt.closeBT());
		GetTime.mymenu.setMenu(1);
		return 0;
	}
	
	@Override
	public String toString(){
		return "Desconnectar Bluetooth"; 
	}

}
