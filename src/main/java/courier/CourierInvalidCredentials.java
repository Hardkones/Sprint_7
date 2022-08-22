package courier;

public class CourierInvalidCredentials {
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public CourierInvalidCredentials(String login) {
        this.login = login;
    }

    private String login;

}
