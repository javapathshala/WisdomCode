/**
 * 
 */
package com.jp.quartz.basic;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author dimit.chadha
 * 
 */
public class BasicQuartz {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// specify the job' s details..
		JobDetail job = JobBuilder.newJob(HelloJob.class)
				.withIdentity("testJob").build();
		// specify the running period of the job

		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
								.withIntervalInSeconds(10).repeatForever())
				.build();

		try {
			// Grab the Scheduler instance from the Factory
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			// and start it off
			scheduler.start();
			scheduler.scheduleJob(job, trigger);
			//scheduler.shutdown();
		} catch (SchedulerException se) {
			se.printStackTrace();
		}
	}

}
