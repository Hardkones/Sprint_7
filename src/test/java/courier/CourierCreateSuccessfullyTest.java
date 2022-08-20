package courier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CourierCreateSuccessfullyTest {
    private CourierRequests courierRequests;
    private CreateCourier createCourier;
    private CourierCredentials courierCredentials;
    private CourierDelete courierDelete;

    @Before
    public void setUp() {
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
    }
    @After
    public void tearDown() {
        courierCredentials = new CourierCredentials("ninja-shinobi", "1234");
        ValidatableResponse loginResponse = courierRequests.login(courierCredentials);
        int loginId = loginResponse.extract().path("id");
        String idLogin = String.valueOf(loginId);
        courierDelete = new CourierDelete(idLogin);
        courierRequests.delete(courierDelete, loginId);
    }
}
