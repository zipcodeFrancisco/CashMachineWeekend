package rocks.zipcode.atm.bank;

/**
 * @author ZipCodeWilmington
 */
public abstract class Account {

    private AccountData accountData;

    public Account(AccountData accountData) {
        this.accountData = accountData;
    }

    public AccountData getAccountData() {
        return accountData;
    }

    public Integer getAccountDataId() {
        return accountData.getId();
    }

    public String getAccountDataName() {
        return accountData.getName();
    }

    public String getAccountDataEmail() {
        return accountData.getEmail();
    }

    public Double getAccountDataBalance() {
        return accountData.getBalance();
    }



    public void deposit(Double amount) {
        updateBalance( getBalance() + amount);
    }

    public boolean withdraw(int amount) {
        if (canWithdraw(amount)) {
            updateBalance((double) (getBalance() - amount));
            System.out.println( "getBalance(): " + getBalance() + " amount: " + amount );
            return true;
        } else {
            return false;
        }
    }

    protected boolean canWithdraw(int amount) {
        return getBalance() >= amount;
    }

    public Double getBalance() {
        return accountData.getBalance();
    }

    private void updateBalance(Double newBalance) {
        accountData = new AccountData(accountData.getId(), accountData.getName(), accountData.getEmail(),
                newBalance);
    }
}
