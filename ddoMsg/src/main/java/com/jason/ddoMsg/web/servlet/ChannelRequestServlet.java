package com.jason.ddoMsg.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jason.ddoMsg.externalInterface.ChannelRequestInterface;

/**
 * Servlet implementation class ChannelRequestServlet
 */
public class ChannelRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChannelRequestServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String pass = request.getParameter("password");
		String msisdn = request.getParameter("mobile");
		String instruct = request.getParameter("content");
		String productId = request.getParameter("productid");
		String dstTime = request.getParameter("dstime");
		
		String resp = ChannelRequestInterface.getInstance().receiveRequest(username, pass, msisdn, instruct, productId, dstTime, 1);
		response.getOutputStream().write(resp.getBytes());
		response.getOutputStream().flush();
	}

}
