package io.mawa.spring.core.aopapi.advisor;

public class BankingServiceImpl implements BankingService {

    @Override
    public void deposit(double amount) {
        System.out.println("Depositing: " + amount);
    }

    @Override
    public void withdraw(double amount) {
        System.out.println("Withdrawing: " + amount);
    }
}
