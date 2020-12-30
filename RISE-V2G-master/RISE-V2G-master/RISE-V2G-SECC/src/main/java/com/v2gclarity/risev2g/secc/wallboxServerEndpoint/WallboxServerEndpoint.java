package com.v2gclarity.risev2g.secc.wallboxServerEndpoint;

import java.io.IOException;
import java.util.HashMap;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ServerEndpoint(value = "/v2gServer/", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class WallboxServerEndpoint {

	private HashMap<String, Session> sessions;
	private Logger logger = LogManager.getLogger(this.getClass().getSimpleName());


	public WallboxServerEndpoint() {
		this.sessions = new HashMap<String, Session>();
	}

	@OnOpen
	public void onOpen(Session session) throws IOException {
		sessions.put(session.getId(), session);
	}

	@OnMessage
	public void onMessage(Session session, WallboxInterfaceMessage message) throws IOException {
		switch (message.getMessageType()) {
		case sessionStartReq:

			break;
		case sessionStopReq:
			break;
		case requestStatus:
			break;
		default:
			break;
		}
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) throws IOException {
		sessions.remove(session.getId());
        getLogger().info(String.format("Session %s closed because of %s", session.getId(), closeReason));
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		// Do error handling here
	}

	public void sendDiscoveryReq(WallboxInterfaceMessage message) {
		sendToAllSessions(message);
	}

	private void sendToAllSessions(WallboxInterfaceMessage message) {
		sessions.forEach((k, v) -> v.getUserProperties().put(message.getMessageType().toString(), message));
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

}
