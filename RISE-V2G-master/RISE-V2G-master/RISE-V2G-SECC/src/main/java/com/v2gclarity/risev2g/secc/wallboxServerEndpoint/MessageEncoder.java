package com.v2gclarity.risev2g.secc.wallboxServerEndpoint;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;

public class MessageEncoder implements Encoder.Text<WallboxInterfaceMessage> {

	private static Gson gson = new Gson();
	@Override
	public void init(EndpointConfig config) {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public String encode(WallboxInterfaceMessage message) throws EncodeException {
		return gson.toJson(message);
	}

}
