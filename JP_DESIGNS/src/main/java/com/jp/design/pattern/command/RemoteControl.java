package com.jp.design.pattern.command;

public class RemoteControl {
	
	Command command;

	public RemoteControl(Command comm) {
		this.command = comm;
		System.out.println("Initializing Remote...");
	}
	public void buttonPushed() {
		command.execute();
	}
}
