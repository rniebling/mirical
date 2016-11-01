package de.nieblings.zipato.data;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class Endpoints {

	public static VirtualEndpoints[] getDevices(ZipatoSession session) {
		WebTarget webTarget = session.getClient().target(ZipatoSession.getREST_URL()).path("devices");

		Invocation.Builder invocationBuilder = webTarget
				.request(MediaType.APPLICATION_JSON);


		invocationBuilder.cookie(session.getCookie());
		Response response = invocationBuilder.get();
		Response respose2 = invocationBuilder.get();

		String output = response.readEntity(String.class);

		System.out.println("Output from Server .... \n");
		System.out.println(output);
		return respose2.readEntity(VirtualEndpoints[].class);
				
	}
	
	public static VirtualEndpoints[] getVirtualEndpoints(ZipatoSession session) {
		WebTarget webTarget = session.getClient().target(ZipatoSession.getREST_URL()).path("virtualEndpoints");

		Invocation.Builder invocationBuilder = webTarget
				.request(MediaType.APPLICATION_JSON);


		invocationBuilder.cookie(session.getCookie());
		Response response = invocationBuilder.get();
		Response respose2 = invocationBuilder.get();

		String output = response.readEntity(String.class);

		System.out.println("Output from Server .... \n");
		System.out.println(output);
		return respose2.readEntity(VirtualEndpoints[].class);
				
	}
	
	

}
