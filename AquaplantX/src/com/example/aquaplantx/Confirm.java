package com.example.aquaplantx;

public class Confirm implements Work {
	
	int num;

	ProcessData p;
	
	public Confirm(int n,ProcessData proc){
		num = n;
		p=proc;
	}
	public void work(){
		p.process();
		GetTime.mymenu.setMenu(num);
	}
	
	@Override
	public String toString(){
		return "Confirmar"; 
	}
}
