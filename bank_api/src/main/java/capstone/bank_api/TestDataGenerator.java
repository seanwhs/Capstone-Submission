//TestDataGenerator.java
package capstone.bank_api;
import java.sql.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import capstone.bank_api.model.AccountTransactions;
import capstone.bank_api.model.AccountType;
import capstone.bank_api.model.Accounts;
import capstone.bank_api.model.Cards;
import capstone.bank_api.model.Customer;
import capstone.bank_api.model.Loans;
import capstone.bank_api.model.Notice;
import capstone.bank_api.repository.AccountTransactionsRepository;
import capstone.bank_api.repository.AccountsRepository;
import capstone.bank_api.repository.CardsRepository;
import capstone.bank_api.repository.CustomerRepository;
import capstone.bank_api.repository.LoanRepository;
import capstone.bank_api.repository.NoticeRepository;

@Component
public class TestDataGenerator implements CommandLineRunner {

        @Autowired
        private AccountTransactionsRepository transactionRepository;
        @Autowired
        private CustomerRepository customerRepository;
        @Autowired
        private AccountsRepository accountsRepository;
        @Autowired
        private LoanRepository loanRepository;
        @Autowired
        private CardsRepository cardsRepository;
        @Autowired
        private NoticeRepository noticeRep;

        @Override
        public void run(String... args) throws Exception {
                // Generate test data for account transactions
                generateCustomerData();
                generateAccountData();
                generateAccountTransactions();
                generateLoanData();
                generateCardData();
                generateNoticeData();
        }

        private void generateCustomerData() {
                Customer customer = new Customer();
                customer.setName("Happy");
                customer.setEmail("happy@example.com");
                customer.setMobileNumber("9876548337");
                customer.setPwd("12345");
                customer.setRole("admin");
                customer.setCreateDt(new Date(System.currentTimeMillis()));

                customerRepository.save(customer);
        }

        private void generateAccountData() {
                // Retrieve or create a Customer object
                Customer customer = customerRepository.findById(1).orElseThrow(); // Assuming customer ID 1 exists in
                                                                                  // the database

                // Generate a unique account number
                String accountNumber = Accounts.generateAccountNumber();
                AccountType accountType = AccountType.SAVINGS;

                String branchAddress = "123 Main Street, New York";
                String createDt = new Date(System.currentTimeMillis()).toString(); // Convert Date to String
                int balance = 0; // Assuming initial balance is 0

                // Create an Accounts object using the retrieved or created Customer object
                Accounts account = new Accounts();
                account.setCustomer(customer);
                account.setAccountType(accountType); // Set account type with enum value
                account.setBranchAddress(branchAddress);
                account.setCreateDt(createDt);
                account.setBalance(balance);
                account.setAccountNumber(accountNumber);

                accountsRepository.save(account);
        }

        private void generateAccountTransactions() {
                // Retrieve or generate a Customer object
                Customer customer = customerRepository.findById(1).orElseThrow();
                
                // Retrieve the generated account number from the database
                String accountNumber = accountsRepository.findAll().iterator().next().getAccountNumber();
                
                // Example data for transactions
                AccountTransactions transaction1 = new AccountTransactions();
                transaction1.setTransactionId(UUID.randomUUID().toString());
                transaction1.setAccountNumber(accountNumber); // Set account number from the generated one
                transaction1.setCustomer(customer);
                transaction1.setTransactionDt(new Date(System.currentTimeMillis()));
                transaction1.setTransactionSummary("Coffee Shop");
                transaction1.setTransactionType("Withdrawal");
                transaction1.setTransactionAmt(30);
                transaction1.setClosingBalance(34500);
                transaction1.setCreateDt(new Date(System.currentTimeMillis()));
                
                // Add more transactions as needed...
                
                // Save transactions to the repository
                transactionRepository.save(transaction1);
            }
            
            

        private void generateLoanData() {
                // Retrieve or create a Customer object
                Customer customer = customerRepository.findById(1).orElseThrow();
            
                Loans loan1 = new Loans(customer, Date.valueOf("2020-10-13"), "Home", 200000, 50000, 150000, null);
                Loans loan2 = new Loans(customer, Date.valueOf("2020-06-06"), "Vehicle", 40000, 10000, 30000, null);
                Loans loan3 = new Loans(customer, Date.valueOf("2018-02-14"), "Home", 50000, 10000, 40000, null);
                Loans loan4 = new Loans(customer, Date.valueOf("2018-02-14"), "Personal", 10000, 3500, 6500, null);
            
                loanRepository.saveAll(List.of(loan1, loan2, loan3, loan4));
        }
            

        private void generateCardData() {
                // Retrieve or create a Customer object
                Customer customer = customerRepository.findById(1).orElseThrow(); // Assuming customer ID 1 exists in
                                                                                  // the database

                // Create Date object for the current timestamp
                Date currentDate = new Date(System.currentTimeMillis());

                // Create Cards objects using the constructor with seven parameters
                Cards card1 = new Cards(0, customer, "4565XXXX4656", "Credit", 10000, 500, 9500, currentDate);
                Cards card2 = new Cards(0, customer, "3455XXXX8673", "Credit", 7500, 600, 6900, currentDate);
                Cards card3 = new Cards(0, customer, "2359XXXX9346", "Credit", 20000, 4000, 16000, currentDate);

                // Save Cards objects to the repository
                cardsRepository.saveAll(List.of(card1, card2, card3));
        }

        private void generateNoticeData() {
                Notice notice1 = new Notice("Home Loan Interest rates reduced",
                                "Home loan interest rates are reduced as per the government guidelines. The updated rates will be effective immediately",
                                Date.valueOf("2024-01-25"), Date.valueOf("2024-03-25"),
                                new Date(System.currentTimeMillis()), null);
                Notice notice2 = new Notice("Net Banking Offers",
                                "Customers who will opt for Internet banking while opening a saving account will get a $50 amazon voucher",
                                Date.valueOf("2024-01-25"), Date.valueOf("2024-03-25"),
                                new Date(System.currentTimeMillis()), null);
                Notice notice3 = new Notice("Mobile App Downtime",
                                "The mobile application of the EazyBank will be down from 2AM-5AM on 12/05/2020 due to maintenance activities",
                                Date.valueOf("2024-01-25"), Date.valueOf("2024-03-25"),
                                new Date(System.currentTimeMillis()), null);
                Notice notice4 = new Notice("E Auction notice",
                                "There will be an e-auction on 12/08/2020 on the Bank website for all the stubborn arrears. Interested parties can participate in the e-auction",
                                Date.valueOf("2024-01-25"), Date.valueOf("2024-03-25"),
                                new Date(System.currentTimeMillis()), null);
                Notice notice5 = new Notice("Launch of Pioneer Generation Cards",
                                "Pioneer Generation Credit Cards are launched for the premium customers of Bank Duit. With these cards, you will get a 5% cashback for each purchase",
                                Date.valueOf("2024-01-25"), Date.valueOf("2024-03-25"),
                                new Date(System.currentTimeMillis()), null);
                Notice notice6 = new Notice("COVID-19 Insurance",
                                "Bank Duit launched an insurance policy which will cover COVID-19 expenses. Please reach out to the branch for more details",
                                Date.valueOf("2024-01-25"), Date.valueOf("2024-03-25"),
                                new Date(System.currentTimeMillis()), null);

                noticeRep.saveAll(List.of(notice1, notice2, notice3, notice4, notice5, notice6));
        }

}
