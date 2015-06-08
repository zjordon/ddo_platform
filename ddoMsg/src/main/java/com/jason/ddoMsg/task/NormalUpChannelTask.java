/**
 * 
 */
package com.jason.ddoMsg.task;

import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.msg.UpChannel;
import com.jason.ddoMsg.queue.UpChannelQueue;
import com.jason.ddoMsg.task.handler.UpChannelHandler;

/**
 * @author jasonzhang
 *
 */
public class NormalUpChannelTask extends AbstractTask {
	
	private static final Logger logger = Logger.getLogger(NormalUpChannelTask.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoMsg.task.AbstractTask#executeTask()
	 */
	@Override
	protected int executeTask() {
		logger.info("start execute NormalUpChannelTask");
		List<UpChannel> list = UpChannelQueue.getInstance().getUpChannels(500);
		
		if (!list.isEmpty()) {
			for (UpChannel upChannel : list) {
				UpChannelHandler.getInstance().handle(upChannel.getDdoMsg(),
						upChannel.getInstruct(), upChannel.getUpUrl(), null);
			}
		}
		logger.info("start execute NormalUpChannelTask");
		return list.size();
	}

}
