public class Person {

    // Attributes
    String first_name;
    String last_name;
    String title;
    long phone_num;
    String email;
    Address address;


    // Constructor
    public Person(String first_name, String last_name, String title, long phone_num, String email, Address address) {

        this.first_name = first_name;
        this.last_name  = last_name;
        this.title      = title;
        this.phone_num  = phone_num;
        this.email      = email;
        this.address    = address;
    }


    // Methods
    public String toString() {
        return getFullName() + "\n" + getPhoneNum() + "\n" + email + "\n" + getAddress();
    }

    public String getFullName() { return (first_name + " " + last_name); }

    public String getAddress() {
        return address.toString();
    }

    public String getPhoneNum() {
        String inputNum = Long.toString(phone_num);
        return ("(" + inputNum.substring(0, 3) + ") " + inputNum.substring(3, 6) + "-" + inputNum.substring(6, 10));
    }

    public String getTitle(){return title;}

    public String getEmail(){ return email; }

    public void updateName(String first, String last){ first_name = first; last_name  = last; }

    public void updatePhone(Long newPhone){ phone_num = newPhone; }

    public void updateEmail(String newEmail){ email = newEmail; }

    public void updateAddress(Address newAddress) {address = newAddress;}

}

