package com.test.samples;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

public class WebSocketHandler extends AbstractWebSocketHandler  {
	
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessionList.add(session);
		 for (WebSocketSession sess : sessionList) {
		    	System.out.println(sess.getId());
	 			sess.sendMessage(new TextMessage(session.getId() + "가  입장"));
		   }
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
	    System.out.println("New Text Message Received");
	    
	    for (WebSocketSession sess : sessionList) {
	    	System.out.println(sess.getId());
 			sess.sendMessage(new TextMessage(session.getId() + "가 보냄 2 : " + message.getPayload()));
 		}
	    
	}

	@Override
	protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws IOException {
	    System.out.println("New Binary Message Received");
	    
	    for (WebSocketSession sess : sessionList) {
	    	sess.sendMessage(new TextMessage(session.getId() + "가 보냄 2 : " + message.getPayload()));
 			sess.sendMessage(message);
 		}

	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessionList.remove(session);
		
	   for (WebSocketSession sess : sessionList) {
	    	System.out.println(sess.getId());
 			sess.sendMessage(new TextMessage(session.getId() + "가  퇴장"));
	   }
	}
}
