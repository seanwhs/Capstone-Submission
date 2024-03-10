// CardsController.java
package capstone.bank_api.controller;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import capstone.bank_api.model.Cards;
import capstone.bank_api.model.Customer;
import capstone.bank_api.repository.CardsRepository;
import capstone.bank_api.repository.CustomerRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CardsController {

    private CardsRepository cardsRepository;
    private CustomerRepository customerRepository;

    public CardsController(CardsRepository cardsRepository, CustomerRepository customerRepository) {
        this.cardsRepository = cardsRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/myCards")
    public ResponseEntity<List<Cards>> getCardDetails(@RequestParam int customerId) {
        // Retrieve Customer by ID from the repository
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            List<Cards> cards = cardsRepository.findByCustomer(customer);
            return ResponseEntity.ok(cards);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/issueCard")
    public String issueCard(@RequestParam int customerId) {
        // Retrieve Customer by ID from the repository
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            // Generate a random card number (for demonstration purposes)
            String cardNumber = generateRandomCardNumber();

            // Other card details
            String cardType = "Debit"; // or "Credit" based on your requirements
            int totalLimit = 10000; // Initial total limit
            int amountUsed = 0;
            int availableAmount = totalLimit;

            // Current date
            Date createDate = new Date(System.currentTimeMillis());

            // Create a new card object
            Cards card = new Cards(customer, cardNumber, cardType, totalLimit, amountUsed, availableAmount, createDate);

            // Save the card to the database
            cardsRepository.save(card);

            return "Card issued successfully";
        } else {
            return "Customer not found";
        }
    }

    private String generateRandomCardNumber() {
        // Generate a random 16-digit card number
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int digit = random.nextInt(10);
            cardNumber.append(digit);
        }
        return cardNumber.toString();
    }
}
