package com.serviexpress.apirest.controller;

import java.util.HashMap;
import java.util.Map;

import com.khipu.ApiClient;
import com.khipu.ApiException;
import com.khipu.api.client.PaymentsApi;
import com.khipu.api.model.PaymentsCreateResponse;
import com.serviexpress.apirest.vo.ResultadoVO;

import org.jose4j.json.internal.json_simple.JSONObject;
import org.jose4j.lang.JoseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pago")
public class PagoController {
    ResultadoVO salida = new ResultadoVO();


    @GetMapping(value ="/pago")
    public PaymentsCreateResponse pago() throws JoseException,
            ApiException {
        System.out.println("entra");

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
        options.put("returnUrl", "http://localhost:4200/home/progresoreserva");
        options.put("cancelUrl", "http://localhost:4200/home/progresoreserva");
        options.put("pictureUrl", "https://github.com/juliojimenez-98/ServiExpressFront/raw/master/logo.jpg");
        options.put("notifyUrl", "http://localhost:4200/home/progresoreserva");
        options.put("notifyApiVersion", "1.3");

        PaymentsCreateResponse response;
 
            response = paymentsApi.paymentsPost("Compra de prueba de la API" // Motivo de la compra
                    , "CLP" // Monedas disponibles CLP, USD, ARS, BOB
                    , 200.0 // Monto
                    , options // campos opcionales
            );

            return response;
    }
 

    
}