package ExchangeRate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

public class HttpConnection {
    private final static String NBP_API_TEMPLATE_SEVERAL_DAYS = "http://api.nbp.pl/api/exchangerates/rates/a/%s/%s/%s/";
    public void connectWithNbp(String currencyCode, LocalDate startDate, LocalDate endDate) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(String.format(NBP_API_TEMPLATE_SEVERAL_DAYS, currencyCode.toLowerCase(), startDate, endDate))).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(ParsingApiResponse::parseNbpResponseToMap)
                .join();
        System.out.println("connectWithNbp");
    }
}
