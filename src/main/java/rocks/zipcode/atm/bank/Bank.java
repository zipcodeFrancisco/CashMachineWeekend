package rocks.zipcode.atm.bank;

import rocks.zipcode.atm.ActionResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZipCodeWilmington
 */
public class Bank {

    private Map<Integer, Account> accounts = new HashMap<>();

    public Bank() {
        accounts.put(1000, new BasicAccount(new AccountData(
                1000, "Example 1", "example1@gmail.com", (double) 100
        )));

        accounts.put(2000, new PremiumAccount(new AccountData(
                2000, "Example 2", "example2@gmail.com", (double) 200
        )));
        accounts.put(3000, new PremiumAccount(new AccountData(
                3000, "Example 3", "example3@gmail.com", (double) 300
        )));
        accounts.put(4000, new PremiumAccount(new AccountData(
                4000, "Example 4", "example4@gmail.com", (double) 400
        )));
        accounts.put(4000, new PremiumAccount(new AccountData(
                4000, "Example 5", "example5@gmail.com", (double) 500
        )));
    }

    public ActionResult<AccountData> getAccountById(int id) {
        Account account = accounts.get(id);
        //System.out.println( "getAccountById: " + account.getAccountData() );
        System.out.println( "getAccountById: " + account.getAccountDataId() );
        System.out.println( "getAccountById: " + account.getAccountDataName() );
        System.out.println( "getAccountById: " + account.getAccountDataEmail() );
        //return  ActionResult.success( account.getAccountDataId() );
        if (account != null) {
            return ActionResult.success( account.getAccountData() );
        } else {
            return ActionResult.fail("No account with id: " + id + "\nTry account 1000 or 2000");
        }
    }

    public Integer getID(int id){
        Account account = accounts.get(id);
        return account.getAccountDataId();
    }

    public String getEmail(int id){
        //Account account = accounts.get(getEmail(id));
        Account account = accounts.get(id);
        return account.getAccountDataEmail();
    }

    public String getName(int id){
        //Account account = accounts.get(getEmail(id));
        Account account = accounts.get(id);
        return account.getAccountDataName();
    }

    public Double getBalance2(int id){
        Account account = accounts.get(id);
        return account.getAccountDataBalance();
    }



    public ActionResult<AccountData> getBalance( AccountData accountData ) {

        Account account = accounts.get( accountData.getId() );

        System.out.println( "Balance: " +  account.getAccountData().getBalance() );

        return ActionResult.success( account.getAccountData() );
//
//        if (account != null) {
//            //return ActionResult.success(account.getAccountData());
//            //return ActionResult.fail("No account with id: " + balance + "\nTry account 1000 or 2000");
//            return  ActionResult.success( account.getAccountData() );
//        } else {
//            return ActionResult.fail("No account with id: " + id + "\nTry account 1000 or 2000");
//        }
    }

    public ActionResult<AccountData> deposit(AccountData accountData, Double amount) {
        Account account = accounts.get(accountData.getId());
        account.deposit(amount);

        return ActionResult.success(account.getAccountData());
    }

    public ActionResult<AccountData> withdraw(AccountData accountData, int amount) {
        Account account = accounts.get(accountData.getId());
        boolean ok = account.withdraw(amount);

        if (ok) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("Withdraw failed: " + amount + ". Account has: " + account.getBalance());
        }
    }
}
