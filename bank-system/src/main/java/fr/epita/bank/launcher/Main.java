package fr.epita.bank.launcher;

import fr.epita.bank.datamodel.Customer;
import fr.epita.bank.datamodel.SavingsAccount;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("hello!");


        System.out.println("enter a customer name");
        Scanner scanner = new Scanner(System.in);
        String customerName = scanner.nextLine();
        Customer customer = new Customer();
        customer.setName(customerName);


        SavingsAccount savingsAccount = new SavingsAccount();
        System.out.println("enter an interest rate");
        String rawInterestRate = scanner.nextLine();
        savingsAccount.setInterestRate(Double.parseDouble(rawInterestRate));

        String next = scanner.nextLine();

        //comment for commit

        String nextNext = scanner.nextLine();
        //blublublue
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
