// Accounts.java
package capstone.bank_api.model;
import java.util.Random;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Accounts {

    @Id
    @Column(name = "account_number")
    private String accountNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "account_type")
    private AccountType accountType; 

    @Column(name = "branch_address")
    private String branchAddress;

    @Column(name = "create_dt")
    private String createDt;

    @Column(name = "balance")
    private int balance;

    public Accounts(Customer customer, AccountType accountType, String branchAddress, String createDt, int balance) {
        this.customer = customer;
        this.accountType = accountType;
        this.branchAddress = branchAddress;
        this.createDt = createDt;
        this.balance = balance;
    }

    // Method to generate unique account number
    // Account Number is generated on format: "ACC-XXNNNN", 
    // where "ACC-" is fixed, XX represents 2 capital alphabets, 
    // and NNNN represents 4 numbers. 
    public static String generateAccountNumber() {
        StringBuilder accountNumberBuilder = new StringBuilder("ACC-");

        // Generate 2 capital alphabets
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            char randomChar = (char) (random.nextInt(26) + 'A');
            accountNumberBuilder.append(randomChar);
        }

        // Generate 4 numbers
        for (int i = 0; i < 4; i++) {
            int randomNumber = random.nextInt(10);
            accountNumberBuilder.append(randomNumber);
        }

        return accountNumberBuilder.toString();
    }

    // Setter method for Customer
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}