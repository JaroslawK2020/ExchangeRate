package ExchangeRate;

import ExchangeRate.FrontEnd.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Type;
import java.util.Map;

public class ParsingApiResponse {
    static Map<String, Object> jsonMap;

    static void parseNbpResponseToMap(String nbpResponse) {
        Controller controller = new Controller();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonMap = objectMapper.readValue(nbpResponse, new TypeReference<Map<String, Object>>() { //parsing to Map
                @Override
                public Type getType() {
                    return super.getType();
                }
            });
            controller.setJson(jsonMap);
            System.out.println("parseNbpResponseToMap");
        } catch (JsonProcessingException e) {
            System.out.println("JSON parsing exception: " + e.getStackTrace());
        }
    }

    public static void parsingJsonMap() {
        double rand = Math.random();
        try {
            CreateMySheet sheet = new CreateMySheet();
            sheet.createMySheet(jsonMap.get("currency").toString() + " id " + rand);
            sheet.createHeaders(jsonMap);
            sheet.createMyCells(jsonMap);
            System.out.println("parsingJsonMap");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
