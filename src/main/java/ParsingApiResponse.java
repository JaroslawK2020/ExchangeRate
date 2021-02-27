import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
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

           /* CreateMySheet sheet = new CreateMySheet();
            System.out.println("jsonMap: " + jsonMap);
            ArrayList ratesList = (ArrayList) jsonMap.get("rates");
            System.out.println("ratesListClass: " + ratesList.getClass());
            System.out.println("ratesListElementClass: " + ratesList.get(0).getClass());
            HashMap hashMap =(HashMap) ratesList.get(0);//cast ratesList to hashMap*/

        }catch (JsonProcessingException e){
            System.out.println(e.getStackTrace());
        }


    }

    public static void parsingJsonMap(Map jsonMapFromNbp){
        System.out.println("jsonMapFromNbp keySet: " + jsonMapFromNbp.keySet());
//        System.out.println(jsonMapFromNbp);
//        ArrayList ratesList = (ArrayList) jsonMapFromNbp.get("rates");
//        System.out.println("ratesList size: " + ratesList.size());
//        HashMap hashMap =(HashMap) ratesList.get(0);
//        System.out.println("hashMap size:" + hashMap.size());
//        System.out.println("hashMap keySet: " + hashMap.keySet());
        CreateMySheet sheet = new CreateMySheet();
        sheet.createMySheet("test");
        sheet.createHeaders(jsonMapFromNbp);
        sheet.createCell(jsonMapFromNbp);
//        sheet.createCellsFromArray((ArrayList) jsonMapFromNbp.get("rates"));

    }
}
