import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bank {

    public Map<String, Account> accounts = new HashMap<>();
    public Map<String, Account> blockedAccounts = new HashMap<>();
    private final Random random = new Random();

    boolean fraud;
    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException {
            Thread.sleep(1000);
            return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) {
        if (!accounts.containsKey(fromAccountNum) || !accounts.containsKey(toAccountNum)) {
            return;
        }

        if (fromAccountNum.compareTo(toAccountNum) < 0) {
            synchronized (fromAccountNum){
                synchronized (toAccountNum){
                    makeTransfer(fromAccountNum, toAccountNum, amount);
                }
            }
        } else {
            synchronized (toAccountNum){
                synchronized (fromAccountNum){
                    makeTransfer(fromAccountNum, toAccountNum, amount);
                }
            }
        }

        if(amount > 50000) {
            try{
                fraud = isFraud(fromAccountNum, toAccountNum,  amount);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if(fraud) {
                blockedAccounts.put(fromAccountNum, accounts.get(fromAccountNum));
                blockedAccounts.put(toAccountNum, accounts.get(toAccountNum));
                accounts.remove(fromAccountNum);
                accounts.remove(toAccountNum);
            }
        }
    }

    public void makeTransfer(String fromAccountNum, String toAccountNum, long amount){
        if (accounts.get(fromAccountNum).getMoney() >= amount) {
            accounts.get(fromAccountNum).setMoney(accounts.get(fromAccountNum).getMoney() - amount);
            accounts.get(toAccountNum).setMoney(accounts.get(toAccountNum).getMoney() + amount);
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        long balance = 0;
        if (accounts.containsKey(accountNum)) {
            balance = accounts.get(accountNum).getMoney();
        } else {
            balance = blockedAccounts.get(accountNum).getMoney();
        }
        return balance;
    }

    public long getSumAllAccounts() {
        long sum = 0;
        for (String key : accounts.keySet()) {
            sum += getBalance(key);
        }
        for (String key : blockedAccounts.keySet()) {
            sum += getBalance(key);
        }
        return sum;
    }
}
