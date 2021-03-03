import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ChoseDateAndCurrency {
    public String choseCurrency() {
        System.out.println("Please input currency: ");
        Scanner scanner = new Scanner(System.in);
        String inputCurrency = scanner.nextLine();

        return inputCurrency;
    }

    public LocalDate choseStartDate() {
        Scanner scanner = new Scanner(System.in);
        LocalDate startDate = null;
        System.out.println("Chose start date: ");
        try {
            String stringStartDate = scanner.nextLine();
            startDate = LocalDate.parse(stringStartDate, DateTimeFormatter.ISO_LOCAL_DATE);
            System.out.println("startdate: " + startDate);
        } catch (Exception e) {
            System.out.println("Start date input error");
        }

        return startDate;
    }

    public LocalDate choseEndDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chose end date: ");
        LocalDate endDate = null;
        try {
            String stringEndDate = scanner.nextLine();
            endDate = LocalDate.parse(stringEndDate, DateTimeFormatter.ISO_LOCAL_DATE);
            System.out.println("endDate: " + endDate);
        } catch (Exception e) {
            System.out.println("End date input error");
        }
        return endDate;
    }
}
