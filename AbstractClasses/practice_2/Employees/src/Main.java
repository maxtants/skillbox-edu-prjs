import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Company company = new Company();
        for (int i = 0; i < 180; i++) {
            Operator operator = new Operator();
            company.hire(operator);
        }
        for (int i = 0; i < 80; i++) {
            Manager manager = new Manager();
            company.hire(manager);
        }
        for (int i = 0; i < 10; i++) {
            TopManager topManager = new TopManager();
            company.hire(topManager);
        }
        company.getTopSalaryStaff(10);
        company.getLowestSalaryStaff(30);
        for (int i = 0; i < company.employees.size() * 0.5; i++) {
            company.fire(company.employees.get(i));
        }
        company.getTopSalaryStaff(10);
        company.getLowestSalaryStaff(30);

    }
}
