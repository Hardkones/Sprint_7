package courier;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CourierLoginTest {
    private CourierCredentials courierCredentials;
    private CreateCourier createCourier;
    private CourierRequests courierRequests;
    private CourierDelete courierDelete;
    @Before
    public void setUp() {

        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
        courierRequests = new CourierRequests();
    }
    @Test
    @DisplayName("Courier authorization is successfully")
    @Description("Send post request to /api/v1/courier/login, expected status code 200 ok")
    public void courierAuthorization() {
        courierCredentials = new CourierCredentials("ninja-shinobi", "1234");
        createCourier();
        ValidatableResponse response = courierRequests.login(courierCredentials);
        int statusCode = response.extract().statusCode();
        assertEquals(SC_OK, statusCode);
        response.assertThat().body("id", notNullValue());
        deleteCourier();
    }
    @Step("Create courier and return login")
    public void createCourier() {
        createCourier = new CreateCourier("ninja-shinobi", "1234", "madara");
        courierRequests.create(createCourier);
    }
    @Step("Delete courier")
    public void deleteCourier() {
        courierCredentials = new CourierCredentials("ninja-shinobi", "1234");
        ValidatableResponse loginResponse = courierRequests.login(courierCredentials);
        int loginId = loginResponse.extract().path("id");
        String idLogin = String.valueOf(loginId);
        courierDelete = new CourierDelete(idLogin);
        courierRequests.delete(courierDelete, loginId);
    }
    @Test
    @DisplayName("Courier authorization return bad request")
    @Description("Send post request to /api/v1/courier/login, expected status code 400 bad request")
    public void courierAuthorizationErrorWithWrongData() {
        courierCredentials = new CourierCredentials("ninja-shinobi", "");
        createCourier();
        ValidatableResponse response = courierRequests.login(courierCredentials);
        int statusCode = response.extract().statusCode();
        assertEquals(SC_BAD_REQUEST, statusCode);
    }
    @Test
    @DisplayName("Courier authorization return not found")
    @Description("Sent post to /api/v1/courier/login, expected status code 404 not found")
    public void courierAccountNotFoundOrNotExist() {
        courierCredentials = new CourierCredentials("ninja-shinobi", "123");
        createCourier();
        ValidatableResponse response = courierRequests.login(courierCredentials);
        int statusCode = response.extract().statusCode();
        assertEquals(SC_NOT_FOUND, statusCode);
    }
}
