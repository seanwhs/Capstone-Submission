// AccountController.java
package capstone.bank_api.controller;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import capstone.bank_api.model.AccountTransactions;
import capstone.bank_api.model.Accounts;
import capstone.bank_api.model.Customer;
import capstone.bank_api.repository.AccountTransactionsRepository;
import capstone.bank_api.repository.AccountsRepository;
import capstone.bank_api.repository.CustomerRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {
    private final AccountsRepository accountsRepository;
    private final AccountTransactionsRepository accountTransactionsRepository;
    private final CustomerRepository customerRepository;

    public AccountController(
            AccountsRepository accountsRepository,
            AccountTransactionsRepository accountTransactionsRepository,
            CustomerRepository customerRepositor) {
        this.accountsRepository = accountsRepository;
        this.accountTransactionsRepository = accountTransactionsRepository;
        this.customerRepository = customerRepositor;
    }

    @GetMapping("/myAccount")
    public Accounts getAccountDetails(@RequestParam int id) {
        return accountsRepository.findByCustomerId(id);
    }

    @PostMapping("/createAccount")
    public Accounts createAccount(@RequestBody Accounts newAccount, @RequestParam int customerId) {
        // Generate a unique account number
        String accountNumber = Accounts.generateAccountNumber();
        newAccount.setAccountNumber(accountNumber);

        // Find the customer by their ID
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Associate the customer with the account
        newAccount.setCustomer(customer);

        // Save the new account to the repository
        Accounts createdAccount = accountsRepository.save(newAccount);
        return createdAccount;
    }

    @PostMapping("/deposit")
    public Accounts depositCash(@RequestParam String accountNumber, @RequestParam int amount) {
        // Retrieve the account by accountNumber
        Optional<Accounts> optionalAccount = accountsRepository.findById(accountNumber);

        // Check if the account exists
        if (optionalAccount.isPresent()) {
            Accounts account = optionalAccount.get();

            // Calculate new balance
            int newBalance = account.getBalance() + amount;
            account.setBalance(newBalance);

            // Save the updated account with the new balance
            Accounts updatedAccount = accountsRepository.save(account);

            // Create and save transaction
            AccountTransactions transaction = new AccountTransactions();
            transaction.setTransactionId(UUID.randomUUID().toString()); // Generate unique transactionId
            transaction.setAccountNumber(accountNumber);
            transaction.setTransactionSummary("Deposit");
            transaction.setTransactionType("Deposit");
            transaction.setTransactionAmt(amount);
            transaction.setClosingBalance(newBalance);
            accountTransactionsRepository.save(transaction);

            return updatedAccount;
        } else {
            // If the account does not exist, handle the error appropriately
            throw new RuntimeException("Account not found");
        }
    }

    @PostMapping("/withdraw")
    public Accounts withdrawCash(@RequestParam String accountNumber, @RequestParam int amount) {
        Accounts account = accountsRepository.findById(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        int currentBalance = account.getBalance();

        if (currentBalance < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        int newBalance = currentBalance - amount;
        account.setBalance(newBalance);
        accountsRepository.save(account);

        // Create and save transaction with a generated transactionId
        AccountTransactions transaction = new AccountTransactions();
        transaction.setTransactionId(UUID.randomUUID().toString()); // Generate unique transactionId
        transaction.setAccountNumber(accountNumber);
        transaction.setTransactionSummary("Withdrawal");
        transaction.setTransactionType("Withdrawal");
        transaction.setTransactionAmt(-amount);
        transaction.setClosingBalance(newBalance);
        accountTransactionsRepository.save(transaction);

        return account;
    }

    @PostMapping("/transfer")
    public void transferCash(@RequestParam String fromAccountNumber, @RequestParam String toAccountNumber,
            @RequestParam int amount) {
        // Retrieve the accounts
        Accounts fromAccount = accountsRepository.findById(fromAccountNumber)
                .orElseThrow(() -> new RuntimeException("From Account not found"));
        Accounts toAccount = accountsRepository.findById(toAccountNumber)
                .orElseThrow(() -> new RuntimeException("To Account not found"));

        // Validate balance in the 'from' account
        int fromCurrentBalance = fromAccount.getBalance();
        if (fromCurrentBalance < amount) {
            throw new RuntimeException("Insufficient balance in the from account");
        }

        // Update balances
        int fromNewBalance = fromCurrentBalance - amount;
        fromAccount.setBalance(fromNewBalance);
        accountsRepository.save(fromAccount);

        int toNewBalance = toAccount.getBalance() + amount;
        toAccount.setBalance(toNewBalance);
        accountsRepository.save(toAccount);

        // Create and save transaction for 'from' account
        AccountTransactions fromTransaction = new AccountTransactions();
        fromTransaction.setTransactionId(UUID.randomUUID().toString()); // Generate unique transactionId
        fromTransaction.setAccountNumber(fromAccountNumber);
        fromTransaction.setTransactionSummary("Transfer to " + toAccountNumber);
        fromTransaction.setTransactionType("Transfer");
        fromTransaction.setTransactionAmt(-amount);
        fromTransaction.setClosingBalance(fromNewBalance);
        accountTransactionsRepository.save(fromTransaction);

        // Create and save transaction for 'to' account
        AccountTransactions toTransaction = new AccountTransactions();
        toTransaction.setTransactionId(UUID.randomUUID().toString()); // Generate unique transactionId
        toTransaction.setAccountNumber(toAccountNumber);
        toTransaction.setTransactionSummary("Transfer from " + fromAccountNumber);
        toTransaction.setTransactionType("Transfer");
        toTransaction.setTransactionAmt(amount);
        toTransaction.setClosingBalance(toNewBalance);
        accountTransactionsRepository.save(toTransaction);
    }
}
