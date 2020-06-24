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
	public ResponseEntity<?> obtenerEncuesta() {
		logger.info("OBTENIENDO ENCUESTA");
		try {
		JSONObject lista = new JSONObject();
		// primera encuesta
		Long exelente = repositorio.countByNombreAndTipo("servicio", "exelente");
		Long bueno = repositorio.countByNombreAndTipo("servicio", "bueno");
		Long regular = repositorio.countByNombreAndTipo("servicio", "regular");
		Long malo = repositorio.countByNombreAndTipo("servicio", "malo");
		Long muymalo = repositorio.countByNombreAndTipo("servicio", "muymalo");

		Long lento = repositorio.countByNombreAndTipo("tiempo", "lento");
		Long normal = repositorio.countByNombreAndTipo("tiempo", "normal");
		Long rapido = repositorio.countByNombreAndTipo("tiempo", "rapido");

		Long recomendaria = repositorio.countByNombreAndTipo("recomendacion", "recomendaria");
		Long talvez = repositorio.countByNombreAndTipo("recomendacion", "talvez");
		Long norecomendaria = repositorio.countByNombreAndTipo("recomendacion", "norecomendaria");

	
			Date date1 = new SimpleDateFormat("dd/MM/yy").parse("21/06/20");

			// long test = repositorio.countByNombreAndTipoAndFechaBetween("servicio", "exelente",
			// date1, date1);
		JSONObject servicio = new JSONObject();
		servicio.put("exelente", +exelente);
		servicio.put("bueno", +bueno);
		servicio.put("regular", +regular);
		servicio.put("malo", +malo);
		// servicio.put("muymalo", +muymalo);
		// servicio.put("test", +test);

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