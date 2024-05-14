package fr.epita.bank.launcher;

import fr.epita.bank.datamodel.Customer;
import fr.epita.bank.datamodel.SavingsAccount;

import java.util.Scanner;

public class Main {

    static Scanner reader = new Scanner(System.in);

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

        //comment for commit
        String inputMessage = "input the Initial Balance";
        int initialBalance = readInteger(scanner, inputMessage);
        savingsAccount.setBalance(initialBalance);
        savingsAccount.setCustomer(customer);
        savingsAccount.setBalance(savingsAccount.getBalance() - initialBalance);
        System.out.println(savingsAccount.getBalance());

    }

    private static int readInteger(Scanner scanner, String inputMessage) {
        System.out.println(inputMessage);
        String rawValue = scanner.nextLine();
        int initialBalance = Integer.parseInt(rawValue);
        return initialBalance;
    }

}
