package courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
public class CourierRequests {
    @Step("Create courier account")
    public ValidatableResponse create(CreateCourier createCourier) {
        return given()
                .header("Content-type", "application/json")
                .body(createCourier)
                .when()
                .post("/api/v1/courier")
                .then();
    }
    @Step("Courier authorization")
    public ValidatableResponse login(CourierCredentials credentials) {
        return given()
                .header("Content-type", "application/json")
                .body(credentials)
                .when()
                .post("/api/v1/courier/login")
                .then();
    }
    @Step("Delete courier account")
    public ValidatableResponse delete(CourierDelete courierDelete, int idCourier) {
        return given()
                .header("Content-type", "application/json")
                .body(courierDelete)
                .when()
                .delete("/api/v1/courier/{idCourier}", idCourier)
                .then();
    }
}
