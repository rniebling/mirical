package de.nieblings.zipato.data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;



public class Login {
	

	public static  ZipatoSession get(String user, String passwort) {
		String jessionid=new String();
		Client client = ClientBuilder.newClient(new ClientConfig()
				.register(LoggingFilter.class));
		final ZipatoSession session = new ZipatoSession(client);
		
		WebTarget webTarget = client.target(ZipatoSession.getREST_URL()).path("user/init");

		Invocation.Builder invocationBuilder = webTarget
				.request(MediaType.APPLICATION_JSON);

		Response responseStrg = invocationBuilder.get();

		String out = responseStrg.readEntity(String.class);
		System.out.println("Out:" + out);
		Response response = invocationBuilder.get();
		LoginResponse instance = response.readEntity(LoginResponse.class);
		System.out.println("Status:" + response.getStatus());
		jessionid=instance.getJsessionid();
		System.out.println("SessionId:" + instance.getJsessionid());
		System.out.println("Nonce:" + instance.getNonce());
		System.out.println("Succsess:" + instance.getSuccess());

		final String token = toSHA1(instance.getNonce() + toSHA1(passwort));
		webTarget = client.target(ZipatoSession.getREST_URL()).path("user/login");

		Invocation.Builder invocationBuilder2 = webTarget
				.queryParam("username", "user")
				.queryParam("token", token).request(MediaType.APPLICATION_JSON);
		invocationBuilder2.cookie("JSESSIONID",jessionid);
		Response response2 = invocationBuilder2.get();
	
		String output = response2.readEntity(String.class);

		System.out.println("Output from Server .... \n");
		System.out.println(output);
		System.out.println("Cookies:" + response.getCookies());
		session.setJessionid(jessionid);
		return session;
	}

	public static String toSHA1(final String input) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] result = md.digest(input.getBytes());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < result.length; i++) {
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16)
					.substring(1));
		}

		return sb.toString();
	}

}