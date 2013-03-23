package com.example.aquaplantx;

public class CloseBT implements Work{
	public void work(){
		GetTime.myLabel.setText(GetTime.mybt.closeBT());
		GetTime.mymenu.setMenu(1);
	}
	
	@Override
	public String toString(){
		return "Desconnectar Bluetooth"; 
	}

}
