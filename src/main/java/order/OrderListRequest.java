package order;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderListRequest {
    public ValidatableResponse getOrderListWithCourierId(int idCourier) {
        return given()
                .header("Content-type", "application/json")
                .when()
                .get("/api/v1/orders?courierId={idCourier}", idCourier)
                .then();
    }
    public ValidatableResponse getOrderListOnNearestStation(int idCourier, String nearestStation) {
        return given()
                .header("Content-type", "application/json")
                .when()
                .get("/api/v1/orders?courierId={idCourier}&nearestStation={nearestStation}", idCourier, nearestStation)
                .then();
    }
    public ValidatableResponse getOrderListLimitAndPage(Number limit, Number page) {
        return given()
                .header("Content-type", "application/json")
                .when()
                .get("/api/v1/orders?limit={limit}&page={page}", limit, page)
                .then();
    }
    public ValidatableResponse getOrderLimitPageAndStation(Number limit, Number page, String nearestStation) {
        return given()
                .header("Content-type", "application/json")
                .when()
                .get("/api/v1/orders?limit={limit}&page={page}&nearestStation={nearestStation}", limit, page, nearestStation)
                .then();
    }

}
