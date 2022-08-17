package courier;

public class CourierCredentials {
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String login;
    private String password;

    public CourierCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }


//    public static CourierCredentials from(CreateCourier createCourier) {
//        return new CourierCredentials(createCourier.getLogin(), createCourier.getPassword());
//    }
}