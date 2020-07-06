package com.serviexpress.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.annotation.PostConstruct;

import com.khipu.ApiClient;
import com.khipu.ApiException;
import com.khipu.api.client.PaymentsApi;
import com.khipu.api.model.PaymentsCreateResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@SpringBootApplication
@EntityScan(basePackageClasses = { ServiApplication.class, Jsr310JpaConverters.class })

public class ServiApplication {

	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

	}

	public static void main(String[] args) {
		SpringApplication.run(ServiApplication.class, args);

		test();
	
	}

	private static void test(){
		int receiverId = 321928;
		String secretKey = "d459137b7f946289b888f4732d472c415fbb185f";

		ApiClient apiClient = new ApiClient();
		apiClient.setKhipuCredentials(receiverId, secretKey);
		apiClient.setPlatform("demo-client", "2.0");
		
		// apiClient.setDebugging(true);
		PaymentsApi paymentsApi = new PaymentsApi();
		paymentsApi.setApiClient(apiClient);

		Map<String, Object> options = new HashMap<>();
		options.put("transactionId", "MTI-100");
		options.put("returnUrl", "http://mi-ecomerce.com/backend/return");
		options.put("cancelUrl", "http://mi-ecomerce.com/backend/cancel");
		options.put("pictureUrl", "https://github.com/juliojimenez-98/ServiExpressFront/raw/master/logo.jpg");
		options.put("notifyUrl", "http://mi-ecomerce.com/backend/notify");
		options.put("notifyApiVersion", "1.3");

		PaymentsCreateResponse response;
		try {
			response = paymentsApi.paymentsPost("Compra de prueba de la API" // Motivo de la compra
					, "CLP" // Monedas disponibles CLP, USD, ARS, BOB
					, 200.0 // Monto
					, options // campos opcionales
			);

			System.out.println(response);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
