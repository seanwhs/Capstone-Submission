// BalanceController.java
package capstone.bank_api.controller;
import org.springframework.web.bind.annotation.RestController;
import capstone.bank_api.model.AccountTransactions;
import capstone.bank_api.model.Accounts;
import capstone.bank_api.repository.AccountTransactionsRepository;
import capstone.bank_api.repository.AccountsRepository;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BalanceController {

    private AccountTransactionsRepository accountTransactionsRepository;
    private AccountsRepository accountsRepository;

    public BalanceController(
        AccountTransactionsRepository accountTransactionsRepository,
        AccountsRepository accountsRepository
        ) {
        this.accountTransactionsRepository = accountTransactionsRepository;
        this.accountsRepository=accountsRepository;
    }

    @GetMapping("/myBalance")
    public int getBalanceDetails(@RequestParam int id) {
        // Fetch the account of the customer from the database
        Accounts account = accountsRepository.findByCustomerId(id);
        if (account == null) {
            throw new RuntimeException("Account not found for customer with ID: " + id);
        }
    
        // Get all transactions for the customer's account from the database
        List<AccountTransactions> accountTransactions = accountTransactionsRepository.findByCustomer_IdOrderByTransactionDtDesc(id);
    
        // Initialize balance with the current balance of the account
        int balance = account.getBalance();
    
        // Add transaction amounts to the balance
        for (AccountTransactions transaction : accountTransactions) {
            balance += transaction.getTransactionAmt();
        }
    
        return balance;
    }
}
