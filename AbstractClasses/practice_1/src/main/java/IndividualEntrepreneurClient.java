public class IndividualEntrepreneurClient extends Client {

    @Override
    public String getName() {
        return "Индивидуальный предприниматель";
    }

    @Override
    public void put(double amount) {
        if (amount < 1000) {
            super.put(amount * 0.99);
        } else if (amount >= 1000) {
            super.put(amount * 0.995);
        }

    }

    public void info() {
        super.info();
        System.out.println("Условие пополнения: баланс должен быть больше нуля, комиссия 1%, " +
                "если сумма меньше 1000 рублей, и с комиссией 0,5%, если сумма больше или равна 1000 рублей");
    }
}
