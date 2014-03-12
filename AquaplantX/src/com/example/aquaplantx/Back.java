package com.example.aquaplantx;

public class Back implements Work {
	
	int num;
	
	public Back(int n){
		num = n;
	}
	public int work(){
		GetTime.mymenu.setMenu(num);
		return 0;
	}
	
	@Override
	public String toString(){
		return "Voltar"; 
	}
}
