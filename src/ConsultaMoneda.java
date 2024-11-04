import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoneda {

    private Moneda  buscaMoneda(){

        String url = "https://v6.exchangerate-api.com/v6/ca78ecafed3811bba6235acb/latest/USD";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
          return new Gson().fromJson(response.body(), Moneda.class);
        } catch (IOException | InterruptedException e ) {
            throw new RuntimeException(e);
        }
    }

    public  static void mostrarMenu(){
        System.out.println( """
                
                1) Dolar a Peso argentino
                2) Peso argentino a Dolar
                3) Dolar a Real brasileño
                4) Real brasileño a dolar
                5) Dolar a Peso colombiano
                6) Peso colombiano a dolar
                7) Salir
                """);
    }

    public  String calcularMoneda(int opcion,double cantidad ){
        return  switch (opcion){
            case 1 -> dolarA_Divisa(cantidad, "ARS") ;
            case 2 -> divisaA_Dolar(cantidad,"ARS");
            case 3 -> dolarA_Divisa(cantidad, "BRL") ;
            case 4 -> divisaA_Dolar(cantidad, "BRL") ;
            case 5 -> dolarA_Divisa(cantidad, "COP") ;
            case 6 -> divisaA_Dolar(cantidad, "COP") ;

            default -> throw new IllegalStateException("Valor inesperado: " + opcion);
        };

    }

    public String dolarA_Divisa(Double cantidaAConverir,String currencyCode){
       Double total = cantidaAConverir * this.buscaMoneda().conversion_rates().get(currencyCode);
       return String.format("El valor %.2f [USD] equivale a un total de =>> %.2f [%s]",cantidaAConverir,total,currencyCode);
    }
    public String divisaA_Dolar(Double cantidaAConverir,String currencyCode){

        Double total= cantidaAConverir / this.buscaMoneda().conversion_rates().get(currencyCode);
        return String.format("El valor %.2f [%s] equivale a un total de =>> %.2f [USD]",cantidaAConverir,currencyCode,total);
    }

}
