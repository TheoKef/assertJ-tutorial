package org.tutorial.assertj.tests;

import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

public class AssumptionsTests {

    @Test
    public void useAssumptionsTests() {
        // if this assumption fails then all the other code is not performed
        // useful when it does not make sense to continue execution of given test method
        assumeThat("the string").isEqualTo("a string");

        String a = "a";
        String b = "b";
        assertThat(a + b).as("String concatenation works successfully").isEqualTo("ab");
    }
}
