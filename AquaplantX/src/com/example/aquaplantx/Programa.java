package com.example.aquaplantx;

public class Programa implements Work{
	int num;
	public Programa(int n){
		num=n;
	}
	
	public int work(){
		GetTime.myLabel.setText("Programa" + num);
		GetTime.programa = num;
		GetTime.mymenu.setMenu(4);
		return 0;
	}
	
	
	public String toString(){
		return "Programa" + num;
	}

}
