package fr.epita.bank.launcher;

import fr.epita.bank.datamodel.Customer;
import fr.epita.bank.datamodel.SavingsAccount;

public class Main {

    public static void main(String[] args) {

        System.out.println("hello!");

        Customer customer = new Customer();
        customer.setName("toto");

        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setInterestRate(0.02);
        int initialBalance = 300;
        savingsAccount.setBalance(initialBalance);
        savingsAccount.setCustomer(customer);
        savingsAccount.setBalance(savingsAccount.getBalance() - initialBalance);
        System.out.println(savingsAccount.getBalance());

        //create a scenario like this:
        // create a customer
        // create a savings account, initial value, as you want,
        // assign the customer to its savings account
        // do withdrawal of the total balance
        // display the final balance (should be 0)

    }

}