package order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import static org.hamcrest.CoreMatchers.notNullValue;
@RunWith(Parameterized.class)
public class OrderCreateTest {
    private final String[] color;
    private OrderCreate orderCreate;
    private OrderCreateRequest orderCreateRequest;

    public OrderCreateTest(String[] color) {

        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getColors() {
        return new Object[][]{
                {new String[]{"GRAY", "BLACK"}},
                {new String[]{"GRAY"}},
                {new String[]{"BLACK"}},
                {new String[]{}}
        };
    }
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
        orderCreateRequest = new OrderCreateRequest();
    }
    @Test
    @DisplayName("Create order")
    @Description("Send post request to /api/v1/orders")
    public void createOrderWithDifferentColors() {
        orderCreate = new OrderCreate(
                "Kimimaro",
                "Kagya",
                "Land of Sound, 1",
                "1",
                "+79998887766",
                5,
                "2022-09-22",
                "One day of rent for each of the Five Sounds",
                color);
        ValidatableResponse response = orderCreateRequest.create(orderCreate);
        response.assertThat().body("track", notNullValue());

    }
}
