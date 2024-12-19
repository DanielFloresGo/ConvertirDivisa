package utilidades.simples;

import com.google.gson.Gson;
import modelos.Divisa;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaAPI {

    public Divisa buscarDivisa(String divisa){

        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/5fbea56f05292999622bf770/latest/" + divisa);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        HttpResponse<String> response = null;

        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e){
            throw new RuntimeException(e);
        }

        return new Gson().fromJson(response.body(), Divisa.class);
    }

}
