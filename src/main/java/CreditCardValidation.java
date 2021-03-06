import java.util.*;
import java.util.stream.IntStream;

/**
 * Credit Card Validation
 *
 * @author Keith Wedinger <br>
 * Created On: 1/30/18
 */

public class CreditCardValidation {
    static List<Map<String, Object>> validateCards(String[] bannedPrefixes, String[] cardsToValidate) {
        List<Map<String, Object>> validatedCreditCards = new ArrayList<>();

        // Regex for checking cards against banned prefixes
        StringBuilder bannedPrefixRegex = new StringBuilder();
        bannedPrefixRegex.append("^(");
        bannedPrefixRegex.append(String.join("|", bannedPrefixes));
        bannedPrefixRegex.append(").*");

        for (String cardToValidate : cardsToValidate) {
            // Compute the checksum using all but the last digit
            int cardLength = cardToValidate.length();
            int summedDoubledDigits = cardToValidate.chars()
                    .limit(cardLength - 1)
                    .reduce(0, (a, b) -> a + (b - '0') * 2);
            int lastDigit = (cardToValidate.charAt(cardLength - 1) - '0');

            // Validate checksum against last digit
            boolean isValid = (summedDoubledDigits % 10 == lastDigit);

            // Does card start with banned prefix?
            boolean isAllowed = !cardToValidate.matches(bannedPrefixRegex.toString());

            // Add validation results to the returned list
            Map<String, Object> creditCard = new HashMap<>();
            creditCard.put("card", cardToValidate);
            creditCard.put("isValid", isValid);
            creditCard.put("isAllowed", isAllowed);
            validatedCreditCards.add(creditCard);
        }

        return validatedCreditCards;
    }

    public static void main(String args[]) throws Exception {
        Scanner input = new Scanner(System.in);
        String[] bannedPrefixes = input.nextLine().split(",");
        int lines = Integer.parseInt(input.nextLine());
        String[] cardsToValidate = new String[lines];
        IntStream.range(0, lines).forEach(i -> cardsToValidate[i] = input.nextLine());
        List<Map<String, Object>> processedCards = validateCards(bannedPrefixes, cardsToValidate);
        processedCards.stream().forEach(CreditCardValidation::printCardResult);
    }

    private static void printCardResult(Map<String, Object> cardResult) {
        System.out.print("{\"card\":\"" +  cardResult.get("card") + "\",");
        System.out.print("\"isValid\":" +  cardResult.get("isValid") + ",");
        System.out.println("\"isAllowed\":" +  cardResult.get("isAllowed") + "}");
    }
}
