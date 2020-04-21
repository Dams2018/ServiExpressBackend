package com.serviexpress.apirest.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Scope;

public class Util {

	public static String getCurrentTimeStamp() { 
	    SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy;HH:mm:ss");//dd/MM/yyyy 
	    Date now = new Date(); 
	    String strDate = sdfDate.format(now); 
	    return strDate; 
	}
	
	@Scope("singleton")
	public static class Codigos {
		static Codigos instance;

		// ERRORES
		public static final String PASSWORDSNOCOINCIDENTES = "ERR-428;El servidor requiere que la petición del navegador sea condicional (la idUsuario se a modificado o no existe)";
		public static final String CLIENTEEXISTE = "ERR-400;El cliente id ya existe en nuestra base de datos)";
		public static final String MALPARAMETROS = "ERR-406 ;El servidor no es capaz de devolver los datos en ninguno de los formatos aceptados por el cliente, revisar parametros de envio.)";
		// FIN ERROR
		// OK
		public static final String PASSWORDOK = "OK-200;El cambio de contraseña pudo efectuarse correctamente";
		//CODIGOS TIPO DE OPERACION
		public static final int TIPO_OPERACION_GENERACION_CUOTA = 1;


		public static Codigos getInstance() {
			if (instance == null) {
				instance = new Codigos();
			}
			return instance;
		}

		public Codigos() {

		}
	}
	
	//FORMAT DATE
	public static String formatDate(Date date) { 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        String strDate = dateFormat.format(date);  
        //System.out.println("Converted String: " + strDate); 
        return strDate;
	}
	
	//FORMAT DATE
	public static String formatDateCon(Date date) { 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        String strDate = dateFormat.format(date);  
        //System.out.println("Converted String: " + strDate); 
        return strDate;
	}
	
}
