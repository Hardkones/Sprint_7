package order;

import courier.CourierCredentials;
import courier.CourierDelete;
import courier.CourierRequests;
import courier.CreateCourier;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class OrderListTest {
    private CourierRequests courierRequests;
    private CreateCourier createCourier;
    private CourierCredentials courierCredentials;
    private CourierDelete courierDelete;
    private OrderListRequest orderListRequest;
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
        courierRequests = new CourierRequests();
        orderListRequest = new OrderListRequest();
        createCourier = new CreateCourier("ninja-shinobi", "1234", "madara");
        courierRequests.create(createCourier);
    }
    @Test
    @DisplayName("Return order list")
    @Description("Send get request to /api/v1/orders and courierId")
    public void orderListReturns() {
        courierCredentials = new CourierCredentials("ninja-shinobi", "1234");
        ValidatableResponse response = courierRequests.login(courierCredentials);
        int id = response.extract().path("id");
        ValidatableResponse responseOrder = orderListRequest.getOrderListWithCourierId(id);
        ArrayList<String> orderBody = responseOrder.extract().path("orders");
        assertNotNull(orderBody);
    }
    @Test
    @DisplayName("Return order list with nearest station")
    @Description("Send get request to /api/v1/orders and courierId and nearestStation")
    public void orderListReturnsWithMetroStation() {
        courierCredentials = new CourierCredentials("ninja-shinobi", "1234");
        ValidatableResponse response = courierRequests.login(courierCredentials);
        int id = response.extract().path("id");
        ValidatableResponse responseOrder = orderListRequest.getOrderListOnNearestStation(id, "5");
        ArrayList<String> orderBody = responseOrder.extract().path("orders");
        assertNotNull(orderBody);
    }
    @Test
    @DisplayName("Return order list with limit and page")
    @Description("Send get request to /api/v1/orders and limit and page")
    public void orderListReturnsWithLimitAndPage() {
        ValidatableResponse responseOrder = orderListRequest.getOrderListLimitAndPage(7, 0);
        ArrayList<String> orderBody = responseOrder.extract().path("orders");
        assertNotNull(orderBody);
    }
    @Test
    @DisplayName("Return order list with limit, page and nearestStation")
    @Description("Send get request to /api/v1/orders limit, page and nearestStation")
    public void orderListReturnsWithLimitPageAndMetroStation() {
        ValidatableResponse responseOrder = orderListRequest.getOrderLimitPageAndStation(2, 0, "4");
        ArrayList<String> orderBody = responseOrder.extract().path("orders");
        assertNotNull(orderBody);
    }
    @After
    public void tearDown() {
        courierCredentials = new CourierCredentials("ninja-shinobi", "1234");
        ValidatableResponse response = courierRequests.login(courierCredentials);
        int id = response.extract().path("id");
        String idLogin = String.valueOf(id);
        courierDelete = new CourierDelete(idLogin);
        courierRequests.delete(courierDelete, id);
    }
}
