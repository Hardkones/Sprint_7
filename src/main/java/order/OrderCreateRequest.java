package order;

import courier.RestClient;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderCreateRequest extends RestClient {
    public ValidatableResponse create(OrderCreate orderCreate) {
        return given()
                .spec(getBaseSpec())
                .body(orderCreate)
                .when()
                .post("/api/v1/orders")
                .then();
    }
}
