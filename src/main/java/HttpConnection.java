import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

public class HttpConnection {

    private final static String NBP_API_TEMPLATE = "http://api.nbp.pl/api/exchangerates/rates/a/%s/%s/";
    private final static String NBP_API_TEMPLATE2 = "http://api.nbp.pl/api/exchangerates/rates/a/%s/%s/%s/";

    static LocalDate dateFriday = LocalDate.of(2021,2,25);
    static LocalDate dateTuesday = LocalDate.of(2021,2,9);




    public void connectWithNbp(){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(String.format(NBP_API_TEMPLATE2,"usd",dateTuesday,dateFriday))).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse:: body)
//                .thenAccept(System.out::println)
//                .thenAccept(HttpConnection::parseNbpResponseToMap)
                .thenAccept(ParsingApiResponse::parseNbpResponseToMap)
                .join();
    }
}
