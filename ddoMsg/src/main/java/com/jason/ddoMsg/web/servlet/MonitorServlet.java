package com.jason.ddoMsg.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.queue.BillReportQueue;
import com.jason.ddoMsg.queue.ChannelRequestQueue;
import com.jason.ddoMsg.queue.StatisticsQueue;
import com.jason.ddoMsg.queue.UpChannelQueue;

/**
 * Servlet implementation class MonitorServlet
 */
public class MonitorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonitorServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int requestQueueSize = ChannelRequestQueue.getInstance().getSize();
		int billReportQueueSize = BillReportQueue.getInstnace().getSize();
		int statisticsQueueSize = StatisticsQueue.getInstance().getSize();
		int upChannelQueueSize = UpChannelQueue.getInstance().getSize();
		boolean status = CacheManager.getInstance().getConfigCache().isStopAll();
		boolean taskStatus = CacheManager.getInstance().getConfigCache().isStopAllTask();
		StringBuilder builder = new StringBuilder();
		builder.append("requestQueueSize is ").append(requestQueueSize);
		builder.append("<br>billReportQueueSize is ").append(billReportQueueSize);
		builder.append("<br>statisticsQueueSize is ").append(statisticsQueueSize);
		builder.append("<br>upChannelSize is ").append(upChannelQueueSize);
		builder.append("<br>service running status is ").append(status ? "stop" : "running");
		builder.append("<br>task running status is ").append(taskStatus ? "stop" : "running");
		response.getOutputStream().write(builder.toString().getBytes());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
