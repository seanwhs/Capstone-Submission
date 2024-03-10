package capstone.bank_api.controller;
import java.util.Date;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import capstone.bank_api.model.Customer;
import capstone.bank_api.model.ResetPasswordRequest;
import capstone.bank_api.repository.CustomerRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    private CustomerRepository customerRepository;

    public LoginController(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
        Customer savedCustomer = null;
        ResponseEntity<String> response = null;
        try {
            // Set createDt to a Date object
            customer.setCreateDt(new Date(System.currentTimeMillis()));
            savedCustomer = customerRepository.save(customer);
            if (savedCustomer.getId() > 0) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Given user details are successfully registered");
            }
        } catch (Exception ex) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occurred due to " + ex.getMessage());
        }
        return response;
    }

    // Invoked by frontend application
    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        List<Customer> customers = customerRepository.findByEmail(authentication.getName());
        return customers.isEmpty() ? null : customers.get(0);
    }
    
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        Customer customer = customerRepository.findByEmail(resetPasswordRequest.getEmail()).stream().findFirst().orElse(null);
        if (customer != null) {
            if (resetPasswordRequest.getNewPassword() != null && !resetPasswordRequest.getNewPassword().isEmpty()) {
                // Set the new password directly without encoding
                customer.setPwd(resetPasswordRequest.getNewPassword());
                customerRepository.save(customer);
                return ResponseEntity.ok("Password reset successfully");
            } else {
                return ResponseEntity.badRequest().body("New password cannot be empty");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}
