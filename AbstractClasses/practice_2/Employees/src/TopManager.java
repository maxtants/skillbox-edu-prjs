public class TopManager implements Employee {
    public double getMonthSalary() {
        Company company = new Company();
        double fixedSalary = 50_000;
        double bonus = company.getIncome() > 10_000_000 ? fixedSalary * 1.5 : 0;
        return fixedSalary + bonus;
    }
}
