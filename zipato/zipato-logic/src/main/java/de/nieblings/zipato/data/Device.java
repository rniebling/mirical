package de.nieblings.zipato.data;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.nieblings.zipato.data.devices.State;

public class Device {

	public static State[] getStatuses(ZipatoSession session,String link) {
		
		WebTarget webTargetDevice = session.getClient().target(link).path("config");
		Invocation.Builder invocationBuilderDevice = webTargetDevice
				.request(MediaType.APPLICATION_JSON);
		invocationBuilderDevice.cookie(session.getCookie());
		
		Response response = invocationBuilderDevice.get();

		String outputStr = response.readEntity(String.class);

		System.out.println("Output from Server .... \n");
		System.out.println(outputStr);

		Response responseDevice = invocationBuilderDevice.get();
		return responseDevice.readEntity(State[].class);
		
				
	}
	
	public static State[] getStatus(ZipatoSession session,String link) {
		
		WebTarget webTargetDevice = session.getClient().target(link).path("status");
		Invocation.Builder invocationBuilderDevice = webTargetDevice
				.request(MediaType.APPLICATION_JSON);
		invocationBuilderDevice.cookie(session.getCookie());
		
		Response response = invocationBuilderDevice.get();

		String outputStr = response.readEntity(String.class);

		System.out.println("Output from Server .... \n");
		System.out.println(outputStr);

		Response responseDevice = invocationBuilderDevice.get();
		return responseDevice.readEntity(State[].class);
		
				
	}
	

}
