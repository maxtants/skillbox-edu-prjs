public abstract class Client {

    double amount;

    public double getAmount() {
        //TODO: реализуйте метод и удалите todo
        return amount;
    }

    public void put(double amount) {
        //TODO: реализуйте метод и удалите todo
        if (amount > 0) {
            this.amount += amount;
        }
    }

    public void take(double amount) {
        //TODO: реализуйте метод и удалите todo
        if (amount <= this.amount) {
            this.amount -= amount;
        }
    }

    public abstract String getName();

    public void info() {
        System.out.println("Условия пополнения: cумма пополнения должна быть бльше нуля");
        System.out.println("Баланс: " + this.amount);
    }

}
