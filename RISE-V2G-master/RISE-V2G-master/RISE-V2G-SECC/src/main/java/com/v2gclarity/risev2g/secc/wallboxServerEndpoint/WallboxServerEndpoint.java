package com.v2gclarity.risev2g.secc.wallboxServerEndpoint;

import java.beans.EventHandler;
import java.io.IOException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.v2gclarity.risev2g.shared.v2gMessages.msgDef.ACEVSEStatusType;
import com.v2gclarity.risev2g.shared.v2gMessages.msgDef.EVSENotificationType;
import com.v2gclarity.risev2g.shared.v2gMessages.msgDef.MeterInfoType;
import com.v2gclarity.risev2g.shared.v2gMessages.msgDef.NotificationType;
import com.v2gclarity.risev2g.shared.v2gMessages.msgDef.PhysicalValueType;

import de.hsrm.cs.wallbox.shared.enums.MessageType;
import de.hsrm.cs.wallbox.shared.models.WallboxInterfaceMessage;

/**
 * Wallbox Websocket Server Endpoint 
 * Implementation according to Baeldung-Tutorial: https://www.baeldung.com/java-websockets
 * 
 * @author Fabian Birk
 */

@ClientEndpoint
@ServerEndpoint(value = "/v2gServer/", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class WallboxServerEndpoint {

	private Session session;
	private static Set<WallboxServerEndpoint> wallboxServerEndpoints = new CopyOnWriteArraySet<>();
	private Logger logger = LogManager.getLogger(this.getClass().getSimpleName());
	
	private WallboxServerModelSingleton wallboxServerModelInstance;

	public WallboxServerEndpoint() {
		wallboxServerModelInstance = WallboxServerModelSingleton.getInstance();
	}

	@OnOpen
	public void onOpen(Session session) throws IOException {
		this.session = session;
		System.out.println("Socket Connected: " + session);
		wallboxServerEndpoints.add(this);

		WallboxInterfaceMessage message = new WallboxInterfaceMessage("Connected", "", MessageType.sessionStartReq);
		broadcast(message);
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
		wallboxServerEndpoints.remove(this);
		getLogger().info(String.format("Session %s closed because of %s", session.getId(), closeReason));
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		// Do error handling here
	}

	public void sendDiscoveryReq(WallboxInterfaceMessage message) {
		broadcast(message);
	}

	private void broadcast(WallboxInterfaceMessage message) {
		wallboxServerEndpoints.forEach(endpoint -> {
			synchronized (endpoint) {
				try {
					endpoint.session.getBasicRemote().sendObject(message);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public void sendChargingStatusRes(WallboxInterfaceMessage wallboxInterfaceMessage) {
		broadcast(wallboxInterfaceMessage);		
	}

	public MeterInfoType getMeterInfo() {
		Queue<MeterInfoType> meterInfoQueue = wallboxServerModelInstance.getMeterInfo();
		if (meterInfoQueue.size() <= 1) {
			return meterInfoQueue.peek();
		}
		
		return wallboxServerModelInstance.getMeterInfo().poll();
	}

	public PhysicalValueType getACNominalVoltage() {
		return wallboxServerModelInstance.getACNominalVoltage();
	}

	public PhysicalValueType getMaxCurrent() {
		return wallboxServerModelInstance.getMaxCurrent();
	}

	public EVSENotificationType getEVSENotificationType() {
		return wallboxServerModelInstance.getEvseNotificationType();
	}

}
