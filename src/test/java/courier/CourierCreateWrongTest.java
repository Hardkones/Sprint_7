package courier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;

public class CourierCreateWrongTest {
    private CreateCourier createCourier;
    private CourierRequests courierRequests;

    @Before
    public void setUp() {
        courierRequests = new CourierRequests();
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
