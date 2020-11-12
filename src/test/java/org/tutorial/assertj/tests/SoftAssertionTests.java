package org.tutorial.assertj.tests;

import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.assertj.core.api.BDDSoftAssertions;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class SoftAssertionTests {

    @Test
    public void useSoftAssertions() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat("Spiderman").as("Great superheroes").isEqualTo("Iron Man");
        softAssertions.assertThat(12).isLessThan(1);
        softAssertions.assertThat("Java").isNullOrEmpty();

        softAssertions.assertAll();
    }

    @Test
    public void useSoftAssertionsForBDD() {
        BDDSoftAssertions bddSoftAssertions = new BDDSoftAssertions();
        bddSoftAssertions.then("Spiderman").as("Great superheroes").isEqualTo("Iron Man");
        bddSoftAssertions.then(12).isLessThan(1);
        bddSoftAssertions.then("Java").isNullOrEmpty();

        bddSoftAssertions.assertAll();
    }

    @Test
    public void autocloseableSoftAssertions(){
        try(AutoCloseableSoftAssertions softAssertions = new AutoCloseableSoftAssertions()) {
            softAssertions.assertThat("Spiderman").as("Great superheroes").isEqualTo("Iron Man");
            softAssertions.assertThat(12).isLessThan(1);
            softAssertions.assertThat("Java").isNullOrEmpty();
        }
    }
}
