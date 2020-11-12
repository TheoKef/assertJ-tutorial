package org.tutorial.assertj.tests;

import org.assertj.core.api.Assertions;
import org.assertj.core.description.Description;
import org.tutorial.assertj.models.Student;
import org.testng.annotations.Test;

import java.util.function.Consumer;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class BasicAssertionsTests {

    @Test
    public void simpleAssertionsForStrings() {

        String book = "The Lord of the Rings";

        assertThat(book).isNotNull() // for Objects
                        .isNotBlank() // the assertions from here and below are for Strings
                        .startsWith("The")
                        .endsWith("Rings")
                        .contains("Lord");
    }

    @Test
    public void describingAssertions_Success() {
        Student studentA = new Student();
        studentA.setFirstName("John");
        studentA.setLastName("Doe");
        studentA.setId("123456");

        assertThat(studentA.getId())
                .as("The ID of student: %s %s should exist", studentA.getFirstName(), studentA.getLastName())
                .isNotNull();
    }

    @Test
    public void describingAssertions_Failure() {
        Student studentA = new Student();
        studentA.setFirstName("John");
        studentA.setLastName("Doe");
        studentA.setId("123456");

        assertThat(studentA.getId())
                .as("The ID of student: %s %s should be %s", studentA.getFirstName(), studentA.getLastName(), "1234567")
                .isEqualTo("1234567");
    }

    @Test
    public void printingDescription() {

        // initialize the consumer
        final StringBuilder descriptionReportBuilder = new StringBuilder(String.format("Assertions: %n"));
        Consumer<Description> descriptionConsumer = description ->
                descriptionReportBuilder.append(String.format(" - %s%n", description));

        // use the descriptionConsumer for any following assertions
        Assertions.setDescriptionConsumer(descriptionConsumer);

        // assertions
        assertThat(1).as("1 should be equal to 1").isEqualTo(1);
        assertThat("a").as("String should not be blank").isNotBlank();

        String descriptionReport = descriptionReportBuilder.toString();

        System.out.println("DESCRIPTION REPORT: " + descriptionReport);
    }

    @Test
    public void overridingErrorMessage() {
        Student john = new Student("John", "Doe", "123456");
        Student jane = new Student("Jane", "Doe", "789101");

        assertThat(john.getLastName())
                .overridingErrorMessage("Should be %s", john)
                .isEqualTo(jane);
    }

    @Test
    public void assertingException_Success() {
        Student student = null;

        Throwable thrown = catchThrowable(() ->
                student.getId());

        assertThat(thrown).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void assertingException_Failure() {
        Student student = null;

        Throwable thrown = catchThrowable(() ->
                student.getId());

        assertThat(thrown).isInstanceOf(Student.class);
    }

    @Test
    public void assertObjects() {
        Student a = new Student("john", "doe", "123456");
        Student b = new Student("john", "doe", "123456");

        assertThat(a).isEqualTo(b).usingRecursiveComparison();
    }
}
