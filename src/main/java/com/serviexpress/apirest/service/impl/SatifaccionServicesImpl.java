package com.serviexpress.apirest.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

import com.serviexpress.apirest.entity.Satifaccion;
import com.serviexpress.apirest.payload.Encuesta;
import com.serviexpress.apirest.repository.SatifaccionRepository;
import com.serviexpress.apirest.service.UniversalServices;

@Service
public class SatifaccionServicesImpl extends UniversalServices<Satifaccion> {
	@Autowired
	private SatifaccionRepository repositorio;

	private static final Log logger = LogFactory.getLog(UniversalServices.class);

	@Override
	public ResponseEntity<?> crearwithList(List<Encuesta> generico) {
		logger.info("CREANDO ENCUESTA SATIFACCION");
		try {
			for (Encuesta encuesta : generico) {

				Satifaccion satifaccion = new Satifaccion();

				satifaccion.setNombre(encuesta.getNombre());
				satifaccion.setTipo(encuesta.getTipo());
				satifaccion.setFecha(encuesta.getFecha());

				System.out.println("SATI "+satifaccion.getFecha());

				repositorio.save(satifaccion);

			}
			logger.info("ENCUESTA CREADA");
			return ResponseEntity.ok(generico);
		} catch (Exception e) {
			logger.error("HUBO UN ERROR");
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}

	}

	@Override
	public ResponseEntity<?> obtenerEncuesta( Date fechaini, Date fechafin) {
		logger.info("OBTENIENDO ENCUESTA");
		try {
		JSONObject lista = new JSONObject();
		// primera encuesta
		Long exelente = repositorio.countByNombreAndTipoAndFechaBetween("servicio", "exelente",fechaini, fechafin);
		Long bueno = repositorio.countByNombreAndTipoAndFechaBetween("servicio", "bueno",fechaini, fechafin);
		Long regular = repositorio.countByNombreAndTipoAndFechaBetween("servicio", "regular",fechaini, fechafin);
		Long malo = repositorio.countByNombreAndTipoAndFechaBetween("servicio", "malo",fechaini, fechafin);
		Long muymalo = repositorio.countByNombreAndTipoAndFechaBetween("servicio", "muymalo",fechaini, fechafin);

		Long lento = repositorio.countByNombreAndTipoAndFechaBetween("tiempo", "lento",fechaini, fechafin);
		Long normal = repositorio.countByNombreAndTipoAndFechaBetween("tiempo", "normal",fechaini, fechafin);
		Long rapido = repositorio.countByNombreAndTipoAndFechaBetween("tiempo", "rapido",fechaini, fechafin);

		Long recomendaria = repositorio.countByNombreAndTipoAndFechaBetween("recomendacion", "recomendaria",fechaini, fechafin);
		Long talvez = repositorio.countByNombreAndTipoAndFechaBetween("recomendacion", "talvez",fechaini, fechafin);
		Long norecomendaria = repositorio.countByNombreAndTipoAndFechaBetween("recomendacion", "norecomendaria",fechaini, fechafin);

	


			//long test = repositorio.countByNombreAndTipoAndFechaBetween("servicio", "bueno",
			//fechaini, fechafin);
		JSONObject servicio = new JSONObject();
		servicio.put("exelente", +exelente);
		servicio.put("bueno", +bueno);
		servicio.put("regular", +regular);
		servicio.put("malo", +malo);
		servicio.put("muymalo", +muymalo);
		//servicio.put("test", +test);

		// segunda encuesta

		JSONObject tiempo = new JSONObject();
		tiempo.put("lento", +lento);
		tiempo.put("normal", +normal);
		tiempo.put("rapido", +rapido);

		// tercera encuesta

		JSONObject recomendacion = new JSONObject();
		recomendacion.put("recomendaria", +recomendaria);
		recomendacion.put("talvez", +talvez);
		recomendacion.put("norecomendaria", +norecomendaria);

		lista.put("servicio", servicio);
		lista.put("tiempo", tiempo);
		lista.put("recomendacion", recomendacion);
		return new ResponseEntity<Object>(lista, HttpStatus.OK);
	} catch (Exception e) {

		return new ResponseEntity<Object>(e.getMessage(), HttpStatus.CONFLICT);
	
	}


		// return repositorio.countByNombre("servicio");
	}

}