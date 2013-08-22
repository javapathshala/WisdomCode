package com.jp.design.pattern.behaviour.command;

public class RunCommandPattern {

	public static void main(String[] args) {
		System.out.println("Initializing client....");
		RemoteControl rmt = new RemoteControl(new LightOnCommand());
		
		rmt.buttonPushed();
		rmt = new RemoteControl(new LightOffCommand());
		rmt.buttonPushed();
	}
}
