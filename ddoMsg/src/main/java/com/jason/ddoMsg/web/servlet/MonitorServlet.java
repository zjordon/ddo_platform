package com.jason.ddoMsg.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jason.ddoMsg.queue.BillReportQueue;
import com.jason.ddoMsg.queue.ChannelRequestQueue;
import com.jason.ddoMsg.queue.StatisticsQueue;

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
		StringBuilder builder = new StringBuilder();
		builder.append("requestQueueSize is ").append(requestQueueSize);
		builder.append("\r\nbillReportQueueSize is ").append(billReportQueueSize);
		builder.append("\r\nstatisticsQueueSize is ").append(statisticsQueueSize);
		response.getOutputStream().write(builder.toString().getBytes());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
