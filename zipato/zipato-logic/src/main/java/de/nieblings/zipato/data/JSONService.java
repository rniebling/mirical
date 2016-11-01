package de.nieblings.zipato.data;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;


import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;



public class JSONService {
	 
	public static void main(String[] args) {
 
		get();
	}
 
	private static void get(){
		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
		String restURL="https://my.zipato.com:443/zipato-web/v2/";

		WebTarget webTarget = client.target(restURL).path("security/session/init//{param}");
		 
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.get();
		 
		Employees employees = response.readEntity(Employees.class);
		List<Employee> listOfEmployees = employees.getEmployeeList();
		     
		System.out.println(response.getStatus());
		System.out.println(Arrays.toString( listOfEmployees.toArray(new Employee[listOfEmployees.size()]) ));
		 
	}

 
}