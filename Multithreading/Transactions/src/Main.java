public class Main {

    public static void main(String[] args) {
        Bank bank = new Bank();
        for (int i = 0; i <100; i++) {
            openAccount(i, 1000000, bank);
        }

        System.out.println("Сумма на счетах до транзакций: " + bank.getSumAllAccounts());

        for (int i = 0; i <100; i++) {
            String randomAccNum = String.valueOf((int) (99 * Math.random()));
            if (i < 5) {
                bank.transfer(String.valueOf(i), randomAccNum, 60000);
            } else {
                bank.transfer(String.valueOf(i), randomAccNum, 20000);
            }
        }

        System.out.println("");
        System.out.println("Сумма на счетах после транзакций: " + bank.getSumAllAccounts());

    }

    public static void openAccount(int i, int sum, Bank bank) {
        long x = (long) (sum * (1 + Math.random()));
        Account account = new Account(x, String.valueOf(i));
        bank.accounts.put(String.valueOf(i), account);
    }
}
