public class PhysicalPersonClient extends Client {

    @Override
    public String getName() {
        return "Физическое лицо";
    }

    @Override
    public void take(double amount) {
        super.take(amount);
    }

    @Override
    public void info() {
        super.info();
        System.out.println("Условие снятия: баланс должен быть больше нуля");
    }

}
