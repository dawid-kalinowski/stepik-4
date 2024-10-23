package pl.techcorp.employee.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Person {
    private String firstName;
    private String lastName;
    private String email;
    private double salary;
    private String currency;
    private String country; 
    private String company;


    public Person(
        @Value("${person.firstName}") String firstName,
        @Value("${person.lastName}") String lastName,
        @Value("${person.email}") String email,
        @Value("${person.salary}") double salary,
        @Value("${person.currency}") String currency,
        @Value("${person.country}") String country,
        @Value("${person.company}") String company
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
        this.currency = currency;
        this.country = country;
        this.company = company;
    }

     public Person() {
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public double getSalary() {
        return salary;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCountry() {
        return country;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", currency='" + currency + '\'' +
                ", country='" + country + '\'' +
                ", company='" + company + '\'' + 
                '}';
    }
}
