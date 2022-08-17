package order;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderCreateRequest {
    public ValidatableResponse create(OrderCreate orderCreate) {
        return given()
                .header("Content-type", "application/json")
                .body(orderCreate)
                .when()
                .post("/api/v1/orders")
                .then();
    }
}
