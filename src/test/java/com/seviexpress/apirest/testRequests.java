package com.seviexpress.apirest;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.MethodOrderer;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class testRequests {
	
	String name = "UsuarioDePrueba5";
	String idUser = "";
	String idClient = "";
	String idToken = "";
    
	@Test
	@Order(1)
	public void testSignUp() throws Exception {
		sendRequests test = new sendRequests();
		int response = 0;
		response = test.requestSignUp(this.name);
		assertEquals(201, response,"Response: "+response);
		}
	
	@Test
	@Order(2)
	public void testSignUpWork() throws Exception {
		sendRequests test = new sendRequests();
		int response = 0;
		response = test.requestSignUpWork(this.name);
		assertEquals(201, response,"Response: "+response);
	}
	
	@Test
	@Order(3)
	public void testLogin() throws Exception {
		sendRequests test = new sendRequests();
		String[] response = {"","",""};
		response = test.requestLogin(this.name);
		this.idUser = response[0];
		this.idToken = response[1];
		assertEquals("200", response[2],"Response: "+response[2]);
		}

	@Test
	@Order(4)
	@AfterEach
	public void testCreateClient() throws Exception {
		sendRequests test = new sendRequests();
		String[] response = {"","",""};
		if (this.idUser != "") {
			response = test.requestCreateClient(this.idUser);
			assertEquals("200", response[0],"Response: "+response[2]);
			this.idClient=response[2];
		}
	}
	
	
	@Test
	@Order(5)
	@AfterEach
	public void testUpdateClient() throws Exception {
		sendRequests test = new sendRequests();
		int response = 0;
		response = test.requestUpdateClient(this.idUser,this.idClient);
		assertEquals(200, response,"Response: "+response);	
	}
	
	@Test
	@Order(6)
	@AfterEach
	public void testGetAllClients() throws Exception {
		sendRequests test = new sendRequests();
		int response = 0;
		if (this.idToken != "") {
			response = test.requestGetAllClients(this.idToken);
			assertEquals(200, response,"Response: "+response);			
		}

	}
	
	@Test
	@Order(7)
	@AfterEach
	public void testGetClients() throws Exception {
		sendRequests test = new sendRequests();
		int response = 0;
		if (this.idToken != "") {
			response = test.requestGetClients(this.idToken);
			assertEquals(200, response,"Response: "+response);			
		}
	}
	
	
}
