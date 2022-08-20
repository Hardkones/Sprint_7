package order;


public class OrderCreate {

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public String getMetroStation() {

        return metroStation;
    }

    public void setMetroStation(String metroStation) {

        this.metroStation = metroStation;
    }

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {

        this.phone = phone;
    }

    public Number getRentTime() {

        return rentTime;
    }

    public void setRentTime(Number rentTime) {

        this.rentTime = rentTime;
    }

    public String getDeliveryDate() {

        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {

        this.deliveryDate = deliveryDate;
    }

    public String getComment() {

        return comment;
    }

    public void setComment(String comment) {

        this.comment = comment;
    }

    public String[] getColor() {

        return color;
    }

    public void setColor(String[] color) {

        this.color = color;
    }

    public OrderCreate(String firstName, String lastName, String address, String metroStation, String phone, Number rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private Number rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;
}
