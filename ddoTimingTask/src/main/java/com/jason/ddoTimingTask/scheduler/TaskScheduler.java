/**
 * 
 */
package com.jason.ddoTimingTask.scheduler;

import com.jason.ddoTimingTask.task.SendMessageTask;
import com.jason.ddoTimingTask.task.StatisticsTask;

/**
 * 任务调度类 处理任务的启动，停止，并行等逻揖
 * @author jasonzhang
 *
 */
public class TaskScheduler {

private final static TaskScheduler instance = new TaskScheduler();
	
	private TaskScheduler(){}
	
	public final static TaskScheduler getInstance() {
		return instance;
	}
	
	private SendMessageTask sendMessageTask;
	private StatisticsTask statisticsTask;
	
	private MultileTaskThread multileTaskThrea;
	public void init() {
		this.sendMessageTask = new SendMessageTask();
		this.statisticsTask = new StatisticsTask();
		
		this.multileTaskThrea = new MultileTaskThread();
		
		this.multileTaskThrea.addTask(sendMessageTask);
		this.multileTaskThrea.addTask(statisticsTask);
		this.startAllTask();
		
	}
	
	public void startAllTask() {
		ThreadUCExceptionHandler exceptionHandler = new ThreadUCExceptionHandler();
		Thread threadWrapper = new Thread(multileTaskThrea);
		threadWrapper.setUncaughtExceptionHandler(exceptionHandler);
		threadWrapper.start();
	}
}
