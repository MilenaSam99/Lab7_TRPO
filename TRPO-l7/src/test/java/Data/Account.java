package Data;

public class Account {

    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String address;
    public String city;
    public String phone;
    public int country;
    public String state;
    public int postalCode;
    public int day;
    public String month;
    public int year;

    public  Account(String firstName, String lastName, String email, String password, int day, String month, int year, String address, String city, int country, String state, int postalCode, String phone)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.day = day;
        this.month = month;
        this.year = year;
        this.address = address;
        this.city = city;
        this.country = country;
        this.state = state;
        this.postalCode = postalCode;
        this.phone = phone;
    }

}
