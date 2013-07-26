/**
 * 
 */
package com.jp.quartz.spring.basic;

import com.jp.quartz.spring.tasks.FirstTask;
import com.jp.quartz.spring.tasks.SecondTask;

/**
 * @author dimit.chadha
 * 
 */
public class SchedulerService implements ISchedulerService {

	private FirstTask firstTask;
	private SecondTask secondTask;

	public void executeFirstTask() {
		getFirstTask().execute();
	}

	public void executeSecondTask() {
		getSecondTask().execute();
	}

	public FirstTask getFirstTask() {
		return firstTask;
	}

	public void setFirstTask(FirstTask firstTask) {
		this.firstTask = firstTask;
	}

	public SecondTask getSecondTask() {
		return secondTask;
	}

	public void setSecondTask(SecondTask secondTask) {
		this.secondTask = secondTask;
	}

}
