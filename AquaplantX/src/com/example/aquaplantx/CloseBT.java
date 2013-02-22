package com.example.aquaplantx;

public class CloseBT implements Work{
	public void work(){
		GetTime.myLabel.setText(GetTime.mybt.closeBT());
	}
	
	@Override
	public String toString(){
		return "Desconnectar Bluetooth"; 
	}

}
