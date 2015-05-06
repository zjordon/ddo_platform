package com.jason.ddoMsg.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;

import com.jason.ddoMsg.cache.CacheException;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.scheduler.TaskScheduler;

/**
 * Servlet implementation class StopServiceServlet
 */
public class StopServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(StopServiceServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StopServiceServlet() {
        super();
        
    }
    
    private List<String> enableIps;
    
    @Override
	public void init() throws ServletException {
		super.init();
		String[] ips = StringUtils.split(this.getInitParameter("enableIp"), ',');
		this.enableIps = new ArrayList<String>(ips.length);
		for (String ip: ips) {
			this.enableIps.add(ip);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// do nothing
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fromIp = request.getRemoteAddr();
		logger.info("from ip is " + fromIp);
		if (this.isEnableIp(fromIp)) {
			String command = request.getParameter("command");
			String respMsg = null;
			if ("controlStopRequest".equals(command)) {
				//把全局变量设置为停止或启动状态
				boolean runStatus = !CacheManager.getInstance().getConfigCache().isStopAll();
				logger.info("control the service " + (runStatus ? "stop" : "start"));
				try {
					CacheManager.getInstance().getConfigCache().setStopAll(runStatus);
				} catch (CacheException e) {
					e.printStackTrace();
				}
				
				respMsg = "success";
			} else if ("controlStopTask".equals(command)) {
				boolean runStatus = !CacheManager.getInstance().getConfigCache().isStopAllTask();
				logger.info("control the task " + (runStatus ? "stop" : "start"));
				try {
					CacheManager.getInstance().getConfigCache().setStopAllTask(runStatus);
				} catch (CacheException e) {
					e.printStackTrace();
				}
				//停止各种定时任务
				if (runStatus) {
					TaskScheduler.getInstance().stopAllTask();
				} else {
					TaskScheduler.getInstance().startAllTask();
				}
				
				respMsg = "success";
			}  else if ("status".equals(command)) {
				boolean status = CacheManager.getInstance().getConfigCache().isStopAll();
				if (status) {
					respMsg = "stop";
				} else {
					respMsg = "running";
				}
				
			}
			response.getOutputStream().write(respMsg.getBytes());
		} else {
			response.getOutputStream().write("do not allow access".getBytes());
		}
		
	}

	
	private boolean isEnableIp(String ip) {
		return this.enableIps.contains(ip);
	}
}
