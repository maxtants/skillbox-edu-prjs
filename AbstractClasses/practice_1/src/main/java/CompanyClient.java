public class CompanyClient extends Client {

    @Override
    public String getName() {
        return "Юридическое лицо";
    }
    @Override
    public void take(double amount) {
        super.take(amount * 1.01);
    }

    public void info() {
        super.info();
        System.out.println("Условие снятия: баланс должен быть больше нуля, взимается комиссия 1%");
    }

}
