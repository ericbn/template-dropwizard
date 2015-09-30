package com.avenuecode.template;

import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TemplateApplicationTest {
    @ClassRule
    public static final DropwizardAppRule<TemplateConfiguration> RULE =
            new DropwizardAppRule<TemplateConfiguration>(TemplateApplication.class, "template.yml");

    @Test
    public void testTemplate() {
        when().
                get("/").
        then().
                assertThat().statusCode(200).
                assertThat().body(is("Hello World"));
    }
}
