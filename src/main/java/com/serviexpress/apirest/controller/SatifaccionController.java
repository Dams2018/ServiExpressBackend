package com.serviexpress.apirest.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.serviexpress.apirest.payload.Encuesta;
import com.serviexpress.apirest.payload.RangoFecha;
import com.serviexpress.apirest.payload.Response.IngresoVsEgreso;
import com.serviexpress.apirest.service.MyBatisService;
import com.serviexpress.apirest.service.impl.ReporteInServicesImpl;
import com.serviexpress.apirest.service.impl.SatifaccionServicesImpl;

import org.jose4j.json.internal.json_simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/satifaccion")
public class SatifaccionController {


	@Autowired
	MyBatisService myBatisService;

	@Autowired
	SatifaccionServicesImpl satifaccionServicesImpl;

	@Autowired
	@Qualifier("servireportein")
	ReporteInServicesImpl reporteInServicesImpl;

	@PutMapping("/ingresarencuesta")
	public ResponseEntity<?> agregarEncuesta(@RequestBody @Valid final List<Encuesta> encuesta) {

		
		return ResponseEntity.ok(satifaccionServicesImpl.crearwithList(encuesta));
	}

	@PostMapping(value = "/encuesta")
	public ResponseEntity<?> obtener(@RequestBody @Valid final RangoFecha rangofecha) {
		System.out.println(rangofecha.getFechaini());

		System.out.println(rangofecha.getFechafin());
		// String date = fecha;
		// LocalDateTime localdatetime = LocalDateTime.parse(date);

		// System.out.println("origional date as string: " + date);
		// System.out.println("generated LocalDateTime: " + localdatetime);
		// //LocalTime lt = LocalTime.parse( fecha ) ;

		return ResponseEntity.ok(satifaccionServicesImpl.obtenerEncuesta(rangofecha.getFechaini(), rangofecha.getFechafin()));

	}

	@GetMapping(value = "/reportein")
	public ResponseEntity<?> obtenerReporteIn() {

		List<String> meses= new ArrayList<>();
		List<String> valorEgreso= new ArrayList<>();
		List<String> valorIngreso= new ArrayList<>();
		JSONObject lista = new JSONObject();

		List <IngresoVsEgreso> egreso = myBatisService.getEgreso();
		List <IngresoVsEgreso> ingreso = myBatisService.getIngreso();


		for (IngresoVsEgreso ingresos : ingreso) {
			valorIngreso.add(ingresos.getTotal());
		}

		for (IngresoVsEgreso egresos : egreso) {

			if (egresos.getFecha().substring(0, 2).equals("01")) {
				meses.add("Enero");
				valorEgreso.add(egresos.getTotal());
			} else if(egresos.getFecha().substring(0, 2).equals("02")) {
				meses.add("Febrero");
				valorEgreso.add(egresos.getTotal());
			}else if(egresos.getFecha().substring(0, 2).equals("03")) {
				meses.add("Marzo");
				valorEgreso.add(egresos.getTotal());
			}else if(egresos.getFecha().substring(0, 2).equals("04")) {
				meses.add("Abril");
				valorEgreso.add(egresos.getTotal());
			}else if(egresos.getFecha().substring(0, 2).equals("05")) {
				meses.add("Mayo");
				valorEgreso.add(egresos.getTotal());
			}else if(egresos.getFecha().substring(0, 2).equals("06")) {
				meses.add("Junio");
				valorEgreso.add(egresos.getTotal());
			}else if(egresos.getFecha().substring(0, 2).equals("07")) {
				meses.add("Julio");
				valorEgreso.add(egresos.getTotal());
			}else if(egresos.getFecha().substring(0, 2).equals("08")) {
				meses.add("Agosto");
				valorEgreso.add(egresos.getTotal());
			}else if(egresos.getFecha().substring(0, 2).equals("09")) {
				meses.add("Septiembre");
				valorEgreso.add(egresos.getTotal());
			}else if(egresos.getFecha().substring(0, 2).equals("10")) {
				meses.add("Octubre");
				valorEgreso.add(egresos.getTotal());
			}else if(egresos.getFecha().substring(0, 2).equals("11")) {
				meses.add("Noviembre");
				valorEgreso.add(egresos.getTotal());
			}else if(egresos.getFecha().substring(0, 2).equals("12")) {
				meses.add("Diciembre");
				valorEgreso.add(egresos.getTotal());
			}
		
			
		}
		

		lista.put("meses", meses);
		lista.put("valorEgreso", valorEgreso);
		lista.put("valorIngreso", valorIngreso);

		return ResponseEntity.ok(lista);
	}
}