public class Manager implements Employee {
    public double getMonthSalary() {
        double fixedSalary = 30_000;
        double bonus = (115000 + (140_000 - 115_000) * Math.random()) * 0.05;
        return fixedSalary + bonus;
    }
}
