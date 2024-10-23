package pl.techcorp.employee;

import com.opencsv.exceptions.CsvException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import pl.techcorp.employee.domain.Person;
import pl.techcorp.employee.service.EmployeeService;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
@ImportResource("classpath:beans.xml")
public class EmployeeApplication {
    public static void main(String[] args) throws IOException, CsvException {
        EmployeeService employeeService = new EmployeeService();

        String csvFilePath = "MOCK_DATA.csv";
        String xmlFilePath = "beans.xml";


        // List<Person> allEmployees = employeeService.getAllEmployees(csvFilePath, xmlFilePath);
        // System.out.println("Wszyscy pracownicy:");
        // allEmployees.forEach(System.out::println);


        String companyToFilter = "Mymm";
        List<Person> filteredByCompany = employeeService.filterEmployeesByCompany(csvFilePath, xmlFilePath, companyToFilter);
        System.out.println("\nPracownicy z firmy " + companyToFilter + ":");
        filteredByCompany.forEach(System.out::println);

 
        String countryToFilter = "Poland";
        List<Person> filteredByCountry = employeeService.filterEmployeesByCountry(csvFilePath, xmlFilePath, countryToFilter);
        System.out.println("\nPracownicy z kraju  " + countryToFilter + ":");
        filteredByCountry.forEach(System.out::println);

        // List<Person> sortedByLastName = employeeService.sortEmployeesByLastName(csvFilePath, xmlFilePath);
        // System.out.println("\nPracownicy posortowani nazwiskiem:");
        // sortedByLastName.forEach(System.out::println);
    }
}
