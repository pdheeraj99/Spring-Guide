package io.mawa.spring.core.aopapi.advisor;

public interface BankingService {
    void deposit(double amount);
    void withdraw(double amount);
}
