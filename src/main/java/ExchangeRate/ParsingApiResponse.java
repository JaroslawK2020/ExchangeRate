package ExchangeRate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Type;
import java.util.Map;

public class ParsingApiResponse {
    public static void parseNbpResponseToMap(String nbpResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> jsonMap = objectMapper.readValue(nbpResponse, new TypeReference<Map<String, Object>>() { //parsing to Map
                @Override
                public Type getType() {
                    return super.getType();
                }
            });
            parsingJsonMap(jsonMap);
        } catch (JsonProcessingException e) {
            System.out.println("JSON parsing exception: " + e.getStackTrace());
        }
    }


    public static void parsingJsonMap(Map jsonMapFromNbp) {
        CreateMySheet sheet = new CreateMySheet();
        sheet.createMySheet(jsonMapFromNbp.get("currency").toString());
        sheet.createHeaders(jsonMapFromNbp);
        sheet.createMyCells(jsonMapFromNbp);
    }
}
