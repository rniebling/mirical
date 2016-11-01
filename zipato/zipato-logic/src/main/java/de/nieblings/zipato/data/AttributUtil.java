package de.nieblings.zipato.data;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.nieblings.zipato.data.attribut.Attribut;
import de.nieblings.zipato.data.attribut.ValueSet;
import de.nieblings.zipato.data.devices.State;

public class AttributUtil {

	public static Attribut[] getAttribute(ZipatoSession session) {

		WebTarget webTargetDevice = session.getClient()
				.target(ZipatoSession.getREST_URL()).path("attributes/full")
				.queryParam("full", "true");
		Invocation.Builder invocationBuilderDevice = webTargetDevice
				.request(MediaType.APPLICATION_JSON);
		invocationBuilderDevice.cookie(session.getCookie());

		/*
		 * Form form = new Form(); form.param("grant_type", "password");
		 * 
		 * Response response = invocationBuilderDevice.put(Entity.entity(form,
		 * MediaType.APPLICATION_FORM_URLENCODED));
		 */
		Response response = invocationBuilderDevice.get();

		String outputStr = response.readEntity(String.class);

		System.out.println("Output from Server .... \n");
		System.out.println(outputStr);

		Response responseDevice = invocationBuilderDevice.get();
		return responseDevice.readEntity(Attribut[].class);

	}

	public static State getStatus(ZipatoSession session, String link) {

		WebTarget webTargetDevice = session.getClient()
				.target(ZipatoSession.getREST_URL())
				.path("/devices/" + link + "/status");
		Invocation.Builder invocationBuilderDevice = webTargetDevice
				.request(MediaType.APPLICATION_JSON);
		invocationBuilderDevice.cookie(session.getCookie());

		Response response = invocationBuilderDevice.get();

		String outputStr = response.readEntity(String.class);

		System.out.println("Output from Server .... \n");
		System.out.println(outputStr);

		if (outputStr != null && outputStr.length() > 0) {
			Response responseDevice = invocationBuilderDevice.get();
			return responseDevice.readEntity(State.class);
		}
		return null;

	}

	public static void setValue(ZipatoSession session, final Attribut attribut,
			final String value) {

		WebTarget webTargetDevice = session
				.getClient()
				.target(ZipatoSession.getREST_URL() + "attributes/"
						+ attribut.getUuid()).path("value");
		Invocation.Builder invocationBuilder2 = webTargetDevice
				.request(MediaType.APPLICATION_JSON);
		invocationBuilder2.cookie("JSESSIONID", session.getJessionid());

		ValueSet valueS = new ValueSet();
		valueS.setValue(value);

		Entity<ValueSet> ent = Entity
				.entity(valueS, MediaType.APPLICATION_JSON);
		Response response = invocationBuilder2.put(ent);

		String output = response.readEntity(String.class);

		System.out.println("Output from Server .... \n");
		System.out.println(output);
	}

}
