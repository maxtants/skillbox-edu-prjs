import java.util.*;

public class Company {
    ArrayList<Employee> employees = new ArrayList<>();

    ArrayList<Employee> getTopSalaryStaff(int count) {
        Comparator salaryComparator = new MonthSalaryComparator();
        Collections.sort(employees, salaryComparator);

        for (int i = employees.size() - 1; i >= employees.size() - count; i--) {
            System.out.println(employees.get(i).getMonthSalary());
        }
        return employees;
    }
    ArrayList<Employee> getLowestSalaryStaff(int count) {
        Comparator salaryComparator = new MonthSalaryComparator();
        Collections.sort(employees, salaryComparator);

        for (int i = 0; i < count; i++) {
            System.out.println(employees.get(i).getMonthSalary());
        }
        return employees;
    }
    public void hire(Employee applicants) {
        employees.add(applicants);
    }

    public void hireAll(ArrayList<Employee> applicants) {
        for (Employee ap : applicants) {
            employees.add(ap);
        }
    }
    public void fire(Employee employee) {
        employees.remove(employees.indexOf(employee));
    }

    public double getIncome () {
        return 9_000_000 * (2_000_000 * Math.random());
    }

}

