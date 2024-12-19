package vista;

import com.google.gson.JsonObject;
import modelos.Divisa;
import utilidades.simples.ConsultaAPI;
import utilidades.simples.ConversionDivisa;
import utilidades.simples.StringToMap;

import java.util.ArrayList;
import java.util.Scanner;

public class Interfaz {
    String mensajeBienvenida = """
            ********************************
            Bienvenido al conversor de divisas :)
            Escoge una opción:""";
    String mensajeOpciones = """
                1) Dólar > Peso mexicano
                2) Peso mexicano > Dólar
                3) Dólar > Real brasileño
                4) Real brasileño > Dólar
                5) Dólar > Peso colombiano
                6) Peso colombiano > Dólar
                7) Salir
                Elija una opción válida
                ********************************
                """;
    String mensajeRespuesta;
    ArrayList<String> opciones = new ArrayList();
    ConversionDivisa conversionDivisa = new ConversionDivisa();
    String codigoDivisaOrigen;
    String codigoDivisaDestino;
    String nombreDivisaOrigen;
    String nombreDivisaDestino;
    Double cantidad;
    Double resultadoConversion;

    public void iniciarSesion(){
        Scanner lector = new Scanner(System.in);
        ConsultaAPI consultaAPI = new ConsultaAPI();
        StringToMap stringToMap = new StringToMap();
        opciones.add("1");
        opciones.add("2");
        opciones.add("3");
        opciones.add("4");
        opciones.add("5");
        opciones.add("6");
        opciones.add("7");

        while (true){
            System.out.println(mensajeBienvenida);
            System.out.println(mensajeOpciones);
            mensajeRespuesta = lector.nextLine();

            //Se evalua el texto ingresado corresponda con alguna opción guardada.
            if (opciones.contains(mensajeRespuesta)) {

                //Se verifica si se solicita el cierre con la opción 7
                if (mensajeRespuesta.equals("7") || mensajeRespuesta.equals("")){
                    System.out.println("Finalizando el programa...");
                    break;
                }

                //Evaliación de entradas para modificar variables.
                switch (mensajeRespuesta){
                    case "1":
                        codigoDivisaOrigen = "USD";
                        codigoDivisaDestino = "MXN";
                        nombreDivisaOrigen = "dolare(s)";
                        nombreDivisaDestino = "peso(s) mexicano(s)";
                        break;
                    case "2":
                        codigoDivisaOrigen = "MXN";
                        codigoDivisaDestino = "USD";
                        nombreDivisaOrigen = "peso(s) mexicano(s)";
                        nombreDivisaDestino = "dolare(s)";
                        break;
                    case "3":
                        codigoDivisaOrigen = "USD";
                        codigoDivisaDestino = "BRL";
                        nombreDivisaOrigen = "dolare(s)";
                        nombreDivisaDestino = "reale(s) brasileño(s)";
                        break;
                    case "4":
                        codigoDivisaOrigen = "BRL";
                        codigoDivisaDestino = "USD";
                        nombreDivisaOrigen = "reale(s) brasileño(s)";
                        nombreDivisaDestino = "dolare(s)";
                        break;
                    case "5":
                        codigoDivisaOrigen = "USD";
                        codigoDivisaDestino = "COP";
                        nombreDivisaOrigen = "dolare(s)";
                        nombreDivisaDestino = "peso(s) colombianos(s)";
                        break;
                    case "6":
                        codigoDivisaOrigen = "COP";
                        codigoDivisaDestino = "USD";
                        nombreDivisaOrigen = "peso(s) colombianos(s)";
                        nombreDivisaDestino = "dolare(s)";
                        break;
                }

                //Se emplea el metodo consultaAPI para buscar en la API, retorna un objeto divisa
                Divisa divisa = consultaAPI.buscarDivisa(codigoDivisaOrigen);

                if (divisa == null) {
                    System.out.println("Error en la obtención de datos del servidor: Exchange-Rate API");
                    System.out.println("Finalizando el programa...");
                    break;
                }
                //Se llama al metodo stringToMap para convertir valores clave-valor de String a un objeto json
                JsonObject jsonObject = stringToMap.stringToMap(divisa.conversion_rates().toString());

                System.out.println("Ingresa el valor que deseas convertir:");
                cantidad = lector.nextDouble();
                lector.nextLine();
                //Se ejecuta el metodo calculaDivisa y se manda como parametro los datos obtenidos de la API que se guardaron en el objeto json
                //esto con base a lo solicitado por el cliente.
                resultadoConversion = conversionDivisa.calculaDivisa(jsonObject.get(codigoDivisaOrigen).toString(), jsonObject.get(codigoDivisaDestino).toString(), cantidad);

                System.out.println(cantidad + " " + nombreDivisaOrigen + " es igual a " + resultadoConversion + " " + nombreDivisaDestino);
            }
        }



    }
}
