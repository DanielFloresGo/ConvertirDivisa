package utilidades.simples;

public class ConversionDivisa {

    public Double calculaDivisa(String divisaOrigen, String divisaDestino, Double cantidad){
        Double doubleDivisaOrigen = Double.parseDouble(divisaOrigen);
        Double doubleDivisaDestino = Double.parseDouble(divisaDestino);

        Double resultado = cantidad * doubleDivisaOrigen * doubleDivisaDestino;
        return  resultado;
    }
}
