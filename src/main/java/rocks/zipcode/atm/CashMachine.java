package rocks.zipcode.atm;

import rocks.zipcode.atm.bank.AccountData;
import rocks.zipcode.atm.bank.Bank;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author ZipCodeWilmington
 */
public class CashMachine {

    private final Bank bank;
    private AccountData accountData = null;

    public CashMachine(Bank bank) {
        this.bank = bank;
    }

    private Consumer<AccountData> update = data -> {
        accountData = data;
    };

    public void login(int id) {
        tryCall(
                () -> bank.getAccountById(id),
                update
        );
        //System.out.println("LOGIN: ");

    }

    public String loginId(int id) {
        return bank.getID( id ).toString();
    }


    public String loginEmail(int id) {
        return bank.getEmail( id );
    }

    public String loginName(int id) {
        return bank.getName( id );
    }

    public String loginBalance(int id) {
        return bank.getBalance2( id ).toString();
    }


    public void getBalance(int id) {
        if (accountData != null) {
            tryCall(
                    () -> bank.getBalance(accountData),
                    update
            );
        }
    }


    public void deposit(Double amount) {
        System.out.println("CashMachine.java 1." + accountData);
        if (accountData != null) {
            System.out.println("CashMachine.java 2.");
            tryCall(
                    () -> bank.deposit(accountData, amount),
                    update
            );
        }
    }

    public void withdraw(int amount) {
        if (accountData != null) {
            tryCall(
                    () -> bank.withdraw(accountData, amount),
                    update
            );
        }
    }

    public void exit() {
        if (accountData != null) {
            accountData = null;
        }
    }

    @Override
    public String toString() {
        //
        return accountData != null ? accountData.toString() : "Try account 1000 or 2000 and click submit.";
    }

    private <T> void tryCall(Supplier<ActionResult<T> > action, Consumer<T> postAction) {
        try {
            ActionResult<T> result = action.get();
            if (result.isSuccess()) {
                T data = result.getData();
                postAction.accept(data);
            } else {
                String errorMessage = result.getErrorMessage();
                throw new RuntimeException(errorMessage);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


}
