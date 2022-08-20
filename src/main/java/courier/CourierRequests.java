package courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
public class CourierRequests extends RestClient{
    @Step("Create courier account")
    public ValidatableResponse create(CreateCourier createCourier) {
        return given()
                .spec(getBaseSpec())
                .body(createCourier)
                .when()
                .post("/api/v1/courier")
                .then();
    }
    @Step("Courier authorization")
    public ValidatableResponse login(CourierCredentials credentials) {
        return given()
                .spec(getBaseSpec())
                .body(credentials)
                .when()
                .post("/api/v1/courier/login")
                .then();
    }
    @Step("Delete courier account")
    public ValidatableResponse delete(CourierDelete courierDelete, int idCourier) {
        return given()
                .spec(getBaseSpec())
                .body(courierDelete)
                .when()
                .delete("/api/v1/courier/{idCourier}", idCourier)
                .then();
    }
    @Step("Courier authorization with invalid request")
    public ValidatableResponse invalidLogin(CourierInvalidCredentials invalidCredentials) {
        return given()
                .spec(getBaseSpec())
                .body(invalidCredentials)
                .when()
                .post("/api/v1/courier/login")
                .then();
    }
}
