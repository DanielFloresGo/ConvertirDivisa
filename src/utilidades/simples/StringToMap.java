package utilidades.simples;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class StringToMap {

    public JsonObject stringToMap(String divisa){
        JsonObject jsonObject = JsonParser.parseString(divisa).getAsJsonObject();
        return jsonObject;
    }
}
