package com.seviexpress.apirest;

import java.net.*;
import java.io.*;



public class sendRequests {
	
	public int id_user = 0;
	public int accessToken = 0;
	public String username = "";

	
    public int requestSignUp(String name) throws Exception {
        URL url = new URL("http://127.0.0.1:8090/api/auth/signup");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
        String payload = "{\"name\":\""+name+"\",\"username\":\""+name+"\",\"email\":\"EmailTest@test.com"+name+"\",\"password\":\"1234567\",\"role\":\"2\"}";
        osw.write(payload);
        osw.flush();
        osw.close();
        return connection.getResponseCode();
      }
    
    public int requestSignUpWork(String name) throws Exception {
        URL url = new URL("http://127.0.0.1:8090/api/auth/signupwork");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
        String payload = "{\"name\":\""+"Admin"+name+"\",\"username\":\""+"Admin"+name+"\",\"email\":\"AdminEmailTest@test.com"+name+"\",\"password\":\"1234567\",\"role\":\"1\"}";
        osw.write(payload);
        osw.flush();
        osw.close();
        return connection.getResponseCode();
      }


    public String[] requestLogin(String name) throws Exception {
      URL url = new URL("http://127.0.0.1:8090/api/auth/signin");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.setDoInput(true);
      connection.setDoOutput(true);
      connection.setRequestProperty("Content-Type", "application/json");
      connection.setRequestProperty("Accept", "application/json");
      OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
      String payload = "{\"usernameOrEmail\":\""+name+"\",\"password\":\"1234567\"}";
      osw.write(payload);
      osw.flush();
      osw.close();
      String [] response = {"","",""};
      InputStream in = new BufferedInputStream(connection.getInputStream());
      BufferedReader reader = new BufferedReader(new InputStreamReader(in));
      String line = reader.readLine();
      String[] aux;
      aux = line.split(",",0);
      String[] aux1;
      aux1 = aux[0].split(":",0);
      response [0] = aux1[1];
      aux1 = aux[5].split(":",0);
      response[1] = aux1[1];
      response[2] = Integer.toString(connection.getResponseCode());
      return response;
    }
   
    public String[] requestCreateClient(String n) throws Exception {

        URL url = new URL("http://127.0.0.1:8090/api/entidad/cliente");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
        String payload = "{\"id_usuario\":\""+n+"\",\"rut\":\"1234567-8\",\"nombre\":\"User\",\"apellido\":\"Test\",\"telefono\":\"123456\",\"fechaNacimiento\":\"1989-09-10\"}";
        osw.write(payload);
        osw.flush();
        osw.close();
        InputStream in = new BufferedInputStream(connection.getInputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = reader.readLine();
        String[] response = {"0","0","0"};
        String[] aux = line.split(",",0);
        String[] aux2 = aux[1].split(":",0);
        response[0]= Integer.toString(connection.getResponseCode());
        response[1]=aux2[2];
        String[] aux3 = aux[2].split(":",0);
        response[2]=aux3[1];
        return response;
      }
    
    public int requestUpdateClient(String idUser, String idClient) throws Exception {
        URL url = new URL("http://127.0.0.1:8090/api/entidad/cliente");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
        String payload = "{\"idcliente\":\""+idClient+"\",\"id_usuario\":\""+idUser+"\",\"rut\":\"1234567-8\",\"nombre\":\"User2\",\"apellido\":\"Test2\",\"telefono\":\"123456\",\"fechaNacimiento\":\"1989-09-10\"}";
        osw.write(payload);
        osw.flush();
        osw.close();
        return connection.getResponseCode();
      }
    
    public int requestGetAllClients(String accessToken) throws Exception {
    	accessToken=accessToken.substring(1, accessToken.length()-1);
        URL url = new URL("http://127.0.0.1:8090/api/entidad/allclientes");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization","Bearer "+accessToken);
        connection.setRequestProperty("Content-Type", "application/json");
        System.out.println(connection.getResponseCode());
        return connection.getResponseCode();
      }
    
    public int requestGetClients(String accessToken) throws Exception {
    	accessToken=accessToken.substring(1, accessToken.length()-1);
        URL url = new URL("http://127.0.0.1:8090/api/entidad/clientes?page=0");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization","Bearer "+accessToken);
        connection.setRequestProperty("Content-Type", "application/json");
        System.out.println(connection.getResponseCode());
        return connection.getResponseCode();
      }
}