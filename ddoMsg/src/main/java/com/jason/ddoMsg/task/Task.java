/**
 * 
 */
package com.jason.ddoMsg.task;

/**
 * @author jasonzhang
 *
 */
public interface Task {

	int execute();
	void start();
	void stop();
	boolean isRunning();
	boolean isStop();
}
