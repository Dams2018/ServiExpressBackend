package com.serviexpress.apirest.controller;


import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.khipu.ApiClient;
import com.khipu.ApiException;
import com.khipu.api.client.PaymentsApi;
import com.khipu.api.model.PaymentsCreateResponse;
import com.serviexpress.apirest.payload.response.PagoResponse;
import com.serviexpress.apirest.vo.ResultadoVO;


import org.jose4j.lang.JoseException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pago")
public class PagoController {
    ResultadoVO salida = new ResultadoVO();



    @PostMapping(value ="/pago")
    public PaymentsCreateResponse pago(@RequestBody @Valid PagoResponse pagoResponse) throws JoseException,
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
        options.put("returnUrl", "http://localhost:4200/pagoexito");
        options.put("cancelUrl", "http://localhost:4200/home/progresoreserva");
        options.put("pictureUrl", "https://github.com/juliojimenez-98/ServiExpressFront/raw/master/logo.jpg");
        options.put("notifyUrl", "http://localhost:4200/home/progresoreserva");
        options.put("notifyApiVersion", "1.3");
        options.put("idReserva", pagoResponse.getIdReserva());

        PaymentsCreateResponse response;
 System.out.println(pagoResponse.getValor());
            response = paymentsApi.paymentsPost("Pago del servicio "+pagoResponse.getServicio()+" incluido productos DEMO" // Motivo de la compra
                    , "CLP" // Monedas disponibles CLP, USD, ARS, BOB
                    , 30000.0000 // Monto
                    , options // campos opcionales
  
            );

            return response;
    }
 

    
}