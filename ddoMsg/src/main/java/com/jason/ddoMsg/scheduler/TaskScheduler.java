/**
 * 
 */
package com.jason.ddoMsg.scheduler;

import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.task.ConsumeTurnoverTask;
import com.jason.ddoMsg.task.EventTask;
import com.jason.ddoMsg.task.NormalBillReportTask;
import com.jason.ddoMsg.task.NormalRequestTask;
import com.jason.ddoMsg.task.ExcepInterRequestTask;
import com.jason.ddoMsg.task.NormalUpChannelTask;
import com.jason.ddoMsg.task.ReDeliverReportTask;
import com.jason.ddoMsg.task.RepeatBillReportTask;
import com.jason.ddoMsg.task.RepeatSendDdoMsgTask;
import com.jason.ddoMsg.task.RepeatUpChannelTask;
import com.jason.ddoMsg.task.StatisticsTask;
import com.jason.ddoMsg.task.TimingRequestTask;
import com.jason.ddoMsg.task.UpdateChannelLimitTask;
import com.jason.ddoMsg.task.UpdateConsumeRecordTask;

/**
 * 任务调度类 处理任务的启动，停止，并行等逻揖
 * 
 * @author jasonzhang
 *
 */
public class TaskScheduler {
	private final static TaskScheduler instance = new TaskScheduler();
	
	private TaskScheduler(){}
	
	public final static TaskScheduler getInstance() {
		return instance;
	}

	private int normalRequestTaskNum;
	private NormalRequestTask[] normalRequestTaskArray;
	private NormalBillReportTask normalBillReportTask;
	private NormalUpChannelTask normalUpChannelTask;
	private ExcepInterRequestTask excepInterRequestTask;
	private ReDeliverReportTask reDeliverReportTask;
	private RepeatBillReportTask repeatBillReportTask;
	private RepeatSendDdoMsgTask repeatSendDdoMsgTask;
	private RepeatUpChannelTask repeatUpChannelTask;
	private TimingRequestTask timingRequestTask;
	private UpdateChannelLimitTask updateChannelLimitTask;
	private EventTask eventTask;
	private StatisticsTask statisticsTask;
	private UpdateConsumeRecordTask updateConsumeRecordTask;
	private ConsumeTurnoverTask consumeTurnoverTask;

//	private SingleTaskThread normalRequestTaskThread;
	private SingleTaskThread[] normalRequestTaskThreadArray;
	private SingleTaskThread normalBillReportTaskThread;
	private SingleTaskThread normalUpChannelTaskThread;

	private MultileTaskThread otherRequestTaskThread;
	private MultileTaskThread repeatTaskThread;
	private MultileTaskThread otherTaskThread;

	public void init() {
		this.normalRequestTaskNum = 3;
		this.normalRequestTaskArray = new NormalRequestTask[this.normalRequestTaskNum];
		for (int i=0; i<this.normalRequestTaskNum;i++) {
			this.normalRequestTaskArray[i] = new NormalRequestTask(i);
		}
		normalBillReportTask = new NormalBillReportTask();
		this.normalUpChannelTask = new NormalUpChannelTask();
		excepInterRequestTask = new ExcepInterRequestTask();
		reDeliverReportTask = new ReDeliverReportTask();
		repeatBillReportTask = new RepeatBillReportTask();
		repeatSendDdoMsgTask = new RepeatSendDdoMsgTask();
		repeatUpChannelTask = new RepeatUpChannelTask();
		timingRequestTask = new TimingRequestTask();
		updateChannelLimitTask = new UpdateChannelLimitTask();
		eventTask = new EventTask();
		statisticsTask = new StatisticsTask();
		updateConsumeRecordTask = new UpdateConsumeRecordTask();
		consumeTurnoverTask = new ConsumeTurnoverTask();
		
//		normalRequestTaskThread = new SingleTaskThread(normalRequestTask);
		this.normalRequestTaskThreadArray = new SingleTaskThread[this.normalRequestTaskNum];
		for (int i=0; i<this.normalRequestTaskNum;i++) {
			this.normalRequestTaskThreadArray[i] = new SingleTaskThread(this.normalRequestTaskArray[i]);
		}
		normalBillReportTaskThread = new SingleTaskThread(normalBillReportTask);
		this.normalUpChannelTaskThread = new SingleTaskThread(this.normalUpChannelTask);
		otherRequestTaskThread = new MultileTaskThread();
		otherRequestTaskThread.addTask(excepInterRequestTask);
		otherRequestTaskThread.addTask(timingRequestTask);
		otherRequestTaskThread.addTask(repeatSendDdoMsgTask);
		repeatTaskThread = new MultileTaskThread();
		repeatTaskThread.addTask(repeatBillReportTask);
		repeatTaskThread.addTask(reDeliverReportTask);
		repeatTaskThread.addTask(repeatUpChannelTask);
		otherTaskThread = new MultileTaskThread();
		otherTaskThread.addTask(updateChannelLimitTask);
		otherTaskThread.addTask(eventTask);
		otherTaskThread.addTask(statisticsTask);
		otherTaskThread.addTask(updateConsumeRecordTask);
		otherTaskThread.addTask(consumeTurnoverTask);

		if (!CacheManager.getInstance().getConfigCache().isStopAllTask()) {
			//如果启动任务状态为不启动则不启动任务
			this.startAllTask();
		}
	}

    public void startAllTask() {
		ThreadUCExceptionHandler exceptionHandler = new ThreadUCExceptionHandler();
		
//		for (int i=0; i<this.normalRequestTaskNum; i++) {
//			(this.normalRequestTaskThreadArray[i]).start();
//			Thread normalRequestTaskThreadWrapper = new Thread(this.normalRequestTaskThreadArray[i]);
//			normalRequestTaskThreadWrapper.setDaemon(true);
//			normalRequestTaskThreadWrapper.setUncaughtExceptionHandler(exceptionHandler);
//			normalRequestTaskThreadWrapper.start();
//			
//		}
		this.startNormalRequestTaskThread();
		
		normalBillReportTaskThread.start();
		Thread normalBillReportTaskThreadWrapper = new Thread(normalBillReportTaskThread);
		normalBillReportTaskThreadWrapper.setDaemon(true);
		normalBillReportTaskThreadWrapper.setUncaughtExceptionHandler(exceptionHandler);
		normalBillReportTaskThreadWrapper.start();
		
		this.normalUpChannelTaskThread.start();
		Thread normalUpChannelTaskThreadWrapper = new Thread(normalUpChannelTaskThread);
		normalUpChannelTaskThreadWrapper.setDaemon(true);
		normalUpChannelTaskThreadWrapper.setUncaughtExceptionHandler(exceptionHandler);
		normalUpChannelTaskThreadWrapper.start();
		
		otherRequestTaskThread.start();
		Thread otherRequestTaskThreadWrapper = new Thread(otherRequestTaskThread);
		otherRequestTaskThreadWrapper.setDaemon(true);
		otherRequestTaskThreadWrapper.setUncaughtExceptionHandler(exceptionHandler);
		otherRequestTaskThreadWrapper.start();
		
		repeatTaskThread.start();
		Thread repeatTaskThreadWrapper = new Thread(repeatTaskThread);
		repeatTaskThreadWrapper.setDaemon(true);
		repeatTaskThreadWrapper.setUncaughtExceptionHandler(exceptionHandler);
		repeatTaskThreadWrapper.start();
		
		otherTaskThread.start();
		Thread otherTaskThreadWrapper = new Thread(otherTaskThread);
		otherTaskThreadWrapper.setDaemon(true);
		otherTaskThreadWrapper.setUncaughtExceptionHandler(exceptionHandler);
		otherTaskThreadWrapper.start();
	}

	public void stopAllTask() {
		for (int i=0;i<this.normalRequestTaskNum;i++) {
			(this.normalRequestTaskThreadArray[i]).stop();
		}
		this.normalBillReportTaskThread.stop();
		this.normalUpChannelTaskThread.stop();
		this.otherRequestTaskThread.stop();
		this.repeatTaskThread.stop();
		this.otherTaskThread.stop();
		
//		this.normalRequestTaskThread = null;
//		this.normalBillReportTaskThread = null;
//		this.otherRequestTaskThread = null;
//		this.repeatTaskThread = null;
//		this.otherTaskThread = null;
	}

	public void startTask(String taskName) {
		if ("NormalRequestTask".equals(taskName)) {
//			this.normalRequestTask.start();
//			this.startNormalRequestTaskThread();
			this.startNormalBillReportTaskThread();
		} else if ("NormalBillReportTask".equals(taskName)) {
			this.normalBillReportTask.start();
			this.startNormalBillReportTaskThread();
		} else if ("ExcepInterRequestTask".equals(taskName)) {
			this.excepInterRequestTask.start();
			this.startOtherRequestTaskThread();
		} else if ("timingRequestTask".equals(taskName)) {
			this.timingRequestTask.start();
			this.startOtherRequestTaskThread();
		} else if ("repeatSendDdoMsgTask".equals(taskName)) {
			this.repeatSendDdoMsgTask.start();
			this.startOtherRequestTaskThread();
		} else if ("repeatBillReportTask".equals(taskName)) {
			this.repeatBillReportTask.start();
			this.startRepeatTaskThread();
		} else if ("reDeliverReportTask".equals(taskName)) {
			this.reDeliverReportTask.start();
			this.startRepeatTaskThread();
		} else if ("repeatUpChannelTask".equals(taskName)) {
			this.repeatUpChannelTask.start();
			this.startRepeatTaskThread();
		} else if ("updateChannelLimitTask".equals(taskName)) {
			this.updateChannelLimitTask.start();
			this.startOtherTaskThread();
		} else if ("statisticsTask".equals(taskName)) {
			this.statisticsTask.start();
			this.startOtherTaskThread();
		} else if ("updateConsumeRecordTask".equals(taskName)) {
			this.updateConsumeRecordTask.start();
			this.startOtherTaskThread();
		} else if ("consumeTurnoverTask".equals(taskName)) {
			this.consumeTurnoverTask.start();
			this.startOtherTaskThread();
		} else if ("normalUpChannelTask".equals(taskName)) {
			this.normalUpChannelTaskThread.start();
			this.startNormalUpChannelTaskThread();
		}
	}

	public void stopTask(String taskName) {
		if ("NormalRequestTask".equals(taskName)) {
//			this.normalRequestTask.stop();
			for (int i=0; i<this.normalRequestTaskNum; i++) {
				(this.normalRequestTaskArray[i]).stop();
			}
		} else if ("NormalBillReportTask".equals(taskName)) {
			this.normalBillReportTask.stop();
		} else if ("ExcepInterRequestTask".equals(taskName)) {
			this.excepInterRequestTask.stop();
		} else if ("timingRequestTask".equals(taskName)) {
			this.timingRequestTask.stop();
		} else if ("repeatSendDdoMsgTask".equals(taskName)) {
			this.repeatSendDdoMsgTask.stop();
		} else if ("repeatBillReportTask".equals(taskName)) {
			this.repeatBillReportTask.stop();
		} else if ("reDeliverReportTask".equals(taskName)) {
			this.reDeliverReportTask.stop();
		} else if ("repeatUpChannelTask".equals(taskName)) {
			this.repeatUpChannelTask.stop();
		} else if ("updateChannelLimitTask".equals(taskName)) {
			this.updateChannelLimitTask.stop();
		}else if ("statisticsTask".equals(taskName)) {
			this.statisticsTask.stop();
		}else if ("updateConsumeRecordTask".equals(taskName)) {
			this.updateConsumeRecordTask.stop();
		} else if ("consumeTurnoverTask".equals(taskName)) {
			this.consumeTurnoverTask.stop();
		} else if ("normalUpChannelTask".equals(taskName)) {
			this.normalUpChannelTaskThread.stop();
		}
	}

	private void startNormalRequestTaskThread() {
//		if (this.normalRequestTaskThread.isStop()) {
//			Thread thread = new Thread(normalRequestTaskThread);
//			thread.setUncaughtExceptionHandler(new ThreadUCExceptionHandler());
//			thread.start();
//		}
		ThreadUCExceptionHandler exceptionHandler = new ThreadUCExceptionHandler();
		for (int i=0; i<this.normalRequestTaskNum; i++) {
			(this.normalRequestTaskArray[i]).start();
			(this.normalRequestTaskThreadArray[i]).start();
			Thread normalRequestTaskThreadWrapper = new Thread(this.normalRequestTaskThreadArray[i]);
			normalRequestTaskThreadWrapper.setDaemon(true);
			normalRequestTaskThreadWrapper.setUncaughtExceptionHandler(exceptionHandler);
			normalRequestTaskThreadWrapper.start();
			
		}
	}

	private void startNormalBillReportTaskThread() {
		if (this.normalBillReportTaskThread.isStop()) {
			Thread thread = new Thread(normalBillReportTaskThread);
			thread.setUncaughtExceptionHandler(new ThreadUCExceptionHandler());
			thread.start();
		}
	}
	
	private void startNormalUpChannelTaskThread() {
		if (this.normalUpChannelTaskThread.isStop()) {
			Thread thread = new Thread(normalUpChannelTaskThread);
			thread.setUncaughtExceptionHandler(new ThreadUCExceptionHandler());
			thread.start();
		}
	}
	
	private void startOtherRequestTaskThread() {
		if (this.otherRequestTaskThread.isStop()) {
			Thread thread = new Thread(otherRequestTaskThread);
			thread.setUncaughtExceptionHandler(new ThreadUCExceptionHandler());
			thread.start();
		}
	}
	
	private void startOtherTaskThread() {
		if (this.otherTaskThread.isStop()) {
			Thread thread = new Thread(otherTaskThread);
			thread.setUncaughtExceptionHandler(new ThreadUCExceptionHandler());
			thread.start();
		}
	}
	
	private void startRepeatTaskThread() {
		if (this.repeatTaskThread.isStop()) {
			Thread thread = new Thread(repeatTaskThread);
			thread.setUncaughtExceptionHandler(new ThreadUCExceptionHandler());
			thread.start();
		}
	}
	
	public void destory() {
		this.stopAllTask();
	}

}
