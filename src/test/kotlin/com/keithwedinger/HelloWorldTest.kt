package com.keithwedinger

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/**
 * Simple JUnit5 test starter class
 *
 * @author Keith Wedinger <br>
 * Created On: 1/22/18
 */
class HelloWorldTest {
    private val helloWorldJava = HelloWorldJava()
    private val helloWorldKotlin = HelloWorldKotlin()

    @Test
    internal fun `Simple test for Java class`() {
        Assertions.assertEquals("Hello World", helloWorldJava.greeting)
    }

    @Test
    internal fun `Simple test for Kotlin class`() {
        Assertions.assertEquals("Hello World", helloWorldKotlin.getGreeting())
    }
}