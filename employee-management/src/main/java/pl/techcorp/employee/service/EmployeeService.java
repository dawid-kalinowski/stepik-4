package pl.techcorp.employee.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.techcorp.employee.domain.Person;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import java.io.InputStreamReader;

public class EmployeeService {


    public List<Person> loadEmployeesFromCsv(String filePath) throws IOException, CsvException {
        List<Person> employees = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(new ClassPathResource(filePath).getInputStream()))) {
            String[] nextLine;
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
   
                if (nextLine.length != 6) {
                    System.err.println("Invalid row: " + String.join(",", nextLine));
                    continue; 
                }

                String firstName = nextLine[0];
                String lastName = nextLine[1];
                String email = nextLine[2];
                double salary = Double.parseDouble(nextLine[3]);
                String currency = nextLine[4];
                String country = nextLine[5];


                employees.add(new Person(firstName, lastName, email, salary, currency, country, null));
            }
        }

        return employees;
    }


    public List<Person> loadEmployeesFromXml(String xmlFilePath) {
        ApplicationContext context = new ClassPathXmlApplicationContext(xmlFilePath);
        
        List<Person> employees = new ArrayList<>();


        Person president = (Person) context.getBean("president");
        Person vicePresident = (Person) context.getBean("vicePresident");
        Person secretary = (Person) context.getBean("secretary");

        employees.add(president);
        employees.add(vicePresident);
        employees.add(secretary);

        return employees;
    }


    public List<Person> getAllEmployees(String csvFilePath, String xmlFilePath) throws IOException, CsvException {
        List<Person> allEmployees = new ArrayList<>();
        

        allEmployees.addAll(loadEmployeesFromCsv(csvFilePath));
        

        allEmployees.addAll(loadEmployeesFromXml(xmlFilePath));
        
        return allEmployees;
    }

    public List<Person> filterEmployeesByCompany(String csvFilePath, String xmlFilePath, String companyName) throws IOException, CsvException {
        return getAllEmployees(csvFilePath, xmlFilePath)
                .stream()
                .filter(employee -> companyName.equals(employee.getCompany()))
                .collect(Collectors.toList());
    }


    public List<Person> filterEmployeesByCountry(String csvFilePath, String xmlFilePath, String country) throws IOException, CsvException {
        return getAllEmployees(csvFilePath, xmlFilePath)
                .stream()
                .filter(employee -> country.equals(employee.getCountry()))
                .collect(Collectors.toList());
    }


    public List<Person> sortEmployeesByLastName(String csvFilePath, String xmlFilePath) throws IOException, CsvException {
        return getAllEmployees(csvFilePath, xmlFilePath)
                .stream()
                .sorted(Comparator.comparing(Person::getLastName))
                .collect(Collectors.toList());
    }
}
