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
import static org.junit.Assert.assertTrue;

public class CourierCreateTest {
    private CreateCourier createCourier;
    private CourierRequests courierRequests;
    private CourierCredentials courierCredentials;
    private CourierDelete courierDelete;

    @Before
    public void setUp() {

        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
        courierRequests = new CourierRequests();
    }

    @Test
    @DisplayName("Courier Account Can Be Created Successfully")
    @Description("Send post to /api/v1/courier, expected status code 201 created")
    public void courierAccountCanBeCreatedSuccessfully() {
        createCourier = new CreateCourier("ninja-shinobi", "1234", "madara");
        ValidatableResponse response = courierRequests.create(createCourier);
        int statusCode = response.extract().statusCode();
        assertEquals(SC_CREATED, statusCode);
        boolean isCourierCreated = response.extract().path("ok");
        assertTrue(isCourierCreated);
        deleteCourier();
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
        @DisplayName("Not enough data for create account")
        @Description("Send post request to /api/v1/courier, expected status code 400 bad request")
        public void notEnoughDataForCreateCourierAccount() {
            createCourier = new CreateCourier("", "1234", "madara");
            ValidatableResponse response = courierRequests.create(createCourier);
            int statusCode = response.extract().statusCode();
            assertEquals(SC_BAD_REQUEST, statusCode);
        }
        @Test
        @DisplayName("Data already in use")
        @Description("Send post to /api/v1/courier, expected status code 409 conflict")
        public void loginForCreateCourierAccountAlreadyInUse() {
            createCourier = new CreateCourier("ninja", "1234", "saske");
            ValidatableResponse response = courierRequests.create(createCourier);
            int statusCode = response.extract().statusCode();
            assertEquals(SC_CONFLICT, statusCode);
        }
    }
