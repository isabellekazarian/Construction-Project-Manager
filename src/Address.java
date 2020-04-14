public class Address {

    // Attributes
    int street_num;
    String street_name;
    String city;
    String state;
    int zipcode;

    // Constructor:
    public Address (int street_num, String street_name, String city, String state, int zipcode){

        this.street_num  = street_num;
        this.street_name = street_name;
        this.city        = city;
        this.state       = state;
        this.zipcode     = zipcode;
    }

    // Methods
    public String toString() {
        return street_num + " " + street_name + ", " + city + " " + state + " " + String.format("%05d", zipcode);
    }

    public String getZip() {
        return String.format("%05d",zipcode);
    }
}
