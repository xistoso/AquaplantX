package com.example.aquaplantx;

public class Back implements Work {
	
	int num;
	
	public Back(int n){
		num = n;
	}
	public void work(){
		GetTime.mymenu.setMenu(num);
	}
	
	@Override
	public String toString(){
		return "Voltar"; 
	}
}
