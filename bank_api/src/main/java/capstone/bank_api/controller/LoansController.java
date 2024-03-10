// LoansController.java
package capstone.bank_api.controller;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import capstone.bank_api.model.Customer;
import capstone.bank_api.model.Loans;
import capstone.bank_api.repository.CustomerRepository;
import capstone.bank_api.repository.LoanRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoansController {

    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;

    public LoansController(
        LoanRepository loanRepository,
        CustomerRepository customerRepository
        ) {
        this.loanRepository = loanRepository;
        this.customerRepository=customerRepository;
    }

    @GetMapping("/myLoans")
    public Map<String, Object> getLoanDetails(@RequestParam int id) {
        List<Loans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(id);
        
        // Create a map to hold customer ID and loans
        Map<String, Object> response = new HashMap<>();
        response.put("customerId", id);
        response.put("loans", loans);
        
        return response;
    }

     @PostMapping("/issueLoan")
    public Map<String, Object> issueLoan(@RequestParam int customerId, @RequestBody Loans loan) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            // Customer not found
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Customer not found");
            return response;
        }

        // Set customer for the loan
        loan.setCustomer(customer);
        loan.setStartDt(new Date()); // Set current date as start date
        loan.setCreateDt(new Date()); // Set current date as creation date

        // Save the loan
        Loans savedLoan = loanRepository.save(loan);

        // Prepare response
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Loan issued successfully");
        response.put("loan", savedLoan);

        return response;
    }
}
