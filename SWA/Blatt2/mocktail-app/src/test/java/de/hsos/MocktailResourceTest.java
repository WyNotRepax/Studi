package de.hsos;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.builder.RequestSpecBuilder;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import org.junit.jupiter.api.Test;

import de.hsos.mocktail.boundary.rest.MocktailResource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestHTTPEndpoint(MocktailResource.class)
public class MocktailResourceTest {

    @Test
    public void testCreate() {
        String testName = "TestName";
        String testRezept = "TestRezept";
        String testAutor = "TestAutor";
        String testZutaten = "TestZutaten";

        given(new RequestSpecBuilder()
            .addQueryParam("name", testName)
            .addQueryParam("rezept", testRezept)
            .addQueryParam("autor", testAutor)
            .addQueryParam("zutaten", testZutaten)
            .build()
        )
        .when().post().then()
        .statusCode(201)
        .body("name", is(testName))
        .body("rezept", is(testRezept))
        .body("autor",is(testAutor))
        .body("zutaten",is(testZutaten));
    }

    @Test
    public void testCreateFail() {
        String testName = "TestName";
        String testRezept = "TestRezept";
        String testAutor = "TestAutor";

        given(new RequestSpecBuilder()
            .addQueryParam("name", testName)
            .addQueryParam("rezept", testRezept)
            .addQueryParam("autor", testAutor)
            .build()
        )
        .when().post().then()
        .statusCode(400);
    }

    @Test
    public void testUpdateSuccess()
    {
        
        String testName = "TestName";
        String testRezept = "TestRezept";
        String testAutor = "TestAutor";
        String testZutaten = "TestZutaten";

        given(new RequestSpecBuilder()
            .addQueryParam("name", testName)
            .addQueryParam("rezept", testRezept)
            .addQueryParam("autor", testAutor)
            .addQueryParam("zutaten", testZutaten)
            .addPathParam("id", 0)
            .build()
        )
        .when().put("/{id}").then()
        .statusCode(200)
        .body("name", is(testName))
        .body("rezept", is(testRezept))
        .body("autor",is(testAutor))
        .body("zutaten",is(testZutaten))
        .body("id",is(0));
    }

    @Test
    public void testUpdateFail()
    {
        
        String testName = "TestName";
        String testRezept = "TestRezept";
        String testAutor = "TestAutor";
        String testZutaten = "TestZutaten";

        given(new RequestSpecBuilder()
            .addQueryParam("name", testName)
            .addQueryParam("rezept", testRezept)
            .addQueryParam("autor", testAutor)
            .addQueryParam("zutaten", testZutaten)
            .addPathParam("id", -100)
            .build()
        )
        .when().put("/{id}").then()
        .statusCode(400);
    }
}