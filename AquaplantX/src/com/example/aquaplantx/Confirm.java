package com.example.aquaplantx;

public class Confirm implements Work {
	
	int num;

	ProcessData p;
	
	public Confirm(int n,ProcessData proc){
		num = n;
		p=proc;
	}
	public int work(){
		p.process();
		GetTime.mymenu.setMenu(num);
		return 0;
	}
	
	@Override
	public String toString(){
		return "Confirmar"; 
	}
}
