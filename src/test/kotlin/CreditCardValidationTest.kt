import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*
import java.util.stream.IntStream



/**
 * CreditCardValidationTests
 *
 * @author Keith Wedinger <br>
 * Created On: 1/31/18
 */

class CreditCardValidationTest {
    private fun convertValidatedCardsToString(validatedCards: List<Map<String, Any>>): String {
        val validatedCardsStringBuilder = StringBuilder()
        validatedCards.forEach {
            validatedCardsStringBuilder.append(
                "{\"card\":\"${it["card"]}\"," +
                "\"isValid\":${it["isValid"]}," +
                "\"isAllowed\":${it["isAllowed"]}}\n"
            )
        }
        return validatedCardsStringBuilder.toString().trim('\n')
    }

    private fun getTestInput(testResourcePath: String): Pair<Array<String>, Array<String?>> {
        val input = Scanner(this.javaClass.getResourceAsStream(testResourcePath))
        val bannedPrefixes = input.nextLine().split(",").toTypedArray()
        val lines = Integer.parseInt(input.nextLine())
        val cardsToValidate = arrayOfNulls<String>(lines)
        IntStream.range(0, lines).forEach { i -> cardsToValidate[i] = input.nextLine() }
        return Pair(bannedPrefixes, cardsToValidate)
    }

    private fun getExpectedOutputFromTestResource(testResourcePath: String): String =
        this.javaClass.getResourceAsStream(testResourcePath).bufferedReader().use { it.readText() }

    @Test
    internal fun `Test with input001`() {
        val (bannedPrefixes, cardsToValidate) = getTestInput("/input001.txt")
        val expectedOutput = getExpectedOutputFromTestResource("/output001.txt")
        val actualOutput = convertValidatedCardsToString(CreditCardValidation.validateCards(bannedPrefixes, cardsToValidate))
        Assertions.assertEquals(expectedOutput, actualOutput)
    }

    @Test
    internal fun `Test with input002`() {
        val (bannedPrefixes, cardsToValidate) = getTestInput("/input002.txt")
        val expectedOutput = getExpectedOutputFromTestResource("/output002.txt")
        val actualOutput = convertValidatedCardsToString(CreditCardValidation.validateCards(bannedPrefixes, cardsToValidate))
        Assertions.assertEquals(expectedOutput, actualOutput)
    }
}