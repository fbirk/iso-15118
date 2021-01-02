package com.v2gclarity.risev2g.secc.wallboxServerEndpoint;

import javax.websocket.DecodeException;
import javax.websocket.Decoder.Text;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;

import de.hsrm.cs.wallbox.shared.models.WallboxInterfaceMessage;

public class MessageDecoder implements Text<WallboxInterfaceMessage> {

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
	public WallboxInterfaceMessage decode(String s) throws DecodeException {
		return gson.fromJson(s, WallboxInterfaceMessage.class);
	}

	@Override
	public boolean willDecode(String s) {
		return (s != null);
	}

}
