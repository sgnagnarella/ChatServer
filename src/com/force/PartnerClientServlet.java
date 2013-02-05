package com.force;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sforce.ws.ConnectionException;




public class PartnerClientServlet extends HttpServlet{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req,  HttpServletResponse res) {
    	try {
			PartnerClient partnerClient = new PartnerClient();
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}
