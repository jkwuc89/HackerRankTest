import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/**
 * CreditCardValidationTests
 *
 * @author Keith Wedinger <br>
 * Created On: 1/31/18
 */

class CreditCardValidationTest {
    private val input001BannedPrefixes = arrayOf("1034", "5", "993934" , "33", "9")
    private val input001CreditCards = arrayOf(
        "2552086989552589",
        "6724843711060148",
        "9758289300869651",
        "5048166833276726",
        "2864448008247645",
        "6803999652011971",
        "9758289300869650",
        "9083938527182086",
        "4563447869509114",
        "2552086989552588",
        "6724843711060149",
        "6803999652011970",
        "4563447869509115",
        "3227366834142384",
        "3227366834142385",
        "5048166833276727",
        "5153186889177226",
        "2864448008247644",
        "9083938527182087",
        "5153186889177227"
    )
    private val input001ExpectedOutput =
        "{\"card\":2552086989552589,\"isValid\":false,\"isAllowed\":true}\n" +
        "{\"card\":6724843711060148,\"isValid\":true,\"isAllowed\":true}\n" +
        "{\"card\":9758289300869651,\"isValid\":false,\"isAllowed\":false}\n" +
        "{\"card\":5048166833276726,\"isValid\":true,\"isAllowed\":false}\n" +
        "{\"card\":2864448008247645,\"isValid\":false,\"isAllowed\":true}\n" +
        "{\"card\":6803999652011971,\"isValid\":false,\"isAllowed\":true}\n" +
        "{\"card\":9758289300869650,\"isValid\":true,\"isAllowed\":false}\n" +
        "{\"card\":9083938527182086,\"isValid\":true,\"isAllowed\":false}\n" +
        "{\"card\":4563447869509114,\"isValid\":true,\"isAllowed\":true}\n" +
        "{\"card\":2552086989552588,\"isValid\":true,\"isAllowed\":true}\n" +
        "{\"card\":6724843711060149,\"isValid\":false,\"isAllowed\":true}\n" +
        "{\"card\":6803999652011970,\"isValid\":true,\"isAllowed\":true}\n" +
        "{\"card\":4563447869509115,\"isValid\":false,\"isAllowed\":true}\n" +
        "{\"card\":3227366834142384,\"isValid\":true,\"isAllowed\":true}\n" +
        "{\"card\":3227366834142385,\"isValid\":false,\"isAllowed\":true}\n" +
        "{\"card\":5048166833276727,\"isValid\":false,\"isAllowed\":false}\n" +
        "{\"card\":5153186889177226,\"isValid\":true,\"isAllowed\":false}\n" +
        "{\"card\":2864448008247644,\"isValid\":true,\"isAllowed\":true}\n" +
        "{\"card\":9083938527182087,\"isValid\":false,\"isAllowed\":false}\n" +
        "{\"card\":5153186889177227,\"isValid\":false,\"isAllowed\":false}\n"

    private fun convertValidatedCardsToString(validatedCards: List<Map<String, Any>>): String {
        val validatedCardsStringBuilder = StringBuilder()
        validatedCards.forEach {
            validatedCardsStringBuilder.append(
                "{\"card\":${it["card"]}," +
                "\"isValid\":${it["isValid"]}," +
                "\"isAllowed\":${it["isAllowed"]}}\n"
            )
        }
        return validatedCardsStringBuilder.toString()
    }

    @Test
    internal fun `Test with input001`() {
        val validatedCards = CreditCardValidation.validateCards(input001BannedPrefixes, input001CreditCards)
        val input001ActualOutput = convertValidatedCardsToString(validatedCards)
        Assertions.assertEquals(input001ExpectedOutput, input001ActualOutput)
    }

}