/**
 * 
 */
package com.jason.ddoTimingTask.task;

/**
 * 统计任务
 * @author jasonzhang
 *
 */
public class StatisticsTask extends AbstractTask {

	/* (non-Javadoc)
	 * @see com.jason.ddoTimingTask.task.AbstractTask#executeTask()
	 */
	@Override
	protected int executeTask() {
		int executeNum = 0;
		int tempNum = (new SendRecordStatisticsTask()).execute();
		executeNum += tempNum;
		tempNum = (new SendResultRecordStatTask()).execute();
		executeNum += tempNum;
		tempNum = (new BillResultRecordStatTask()).execute();
		executeNum += tempNum;
		return executeNum;
	}

}
