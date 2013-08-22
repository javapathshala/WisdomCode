package com.jp.design.pattern.behaviour.observer;


public interface Subject {
	
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();
	

}
