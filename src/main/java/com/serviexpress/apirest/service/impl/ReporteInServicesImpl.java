package com.serviexpress.apirest.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.serviexpress.apirest.entity.ReporteIn;
import com.serviexpress.apirest.repository.ReporteInRepository;
import com.serviexpress.apirest.service.UniversalServices;

@Service("servireportein")
public class ReporteInServicesImpl extends UniversalServices<ReporteIn> {
	@Autowired
	@Qualifier("repositoriorportein")
	private ReporteInRepository repositorio;

	private static final Log logger = LogFactory.getLog(UniversalServices.class);

	@Override
	public ResponseEntity<?> crearVS(double monto, long idreserva) {
		logger.info("INSERT REPORT");
		Date date1 = new Date();
        Calendar c1 = Calendar.getInstance(); 
		c1.setTime(date1); 
		c1.add(Calendar.DATE, -1);
		date1 = c1.getTime();

		ReporteIn reporteIn= new ReporteIn();
		reporteIn.setIdreserva(idreserva);
		reporteIn.setValortotal(monto);
		reporteIn.setFecha(date1);
		repositorio.save(reporteIn);
		return null;


	}

	@Override
	public ResponseEntity<?> findVS() {
		logger.info("SEARCH REPORT");
		// repositorio.getIngreso();

		return null;


	}


}