//ResetPasswordRequest.java
package capstone.bank_api.model;
import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String email;
    private String newPassword;
}