// contact-form-service.js
import { http } from "./http-common";

class ContactDataService {


  create(data) {
    return http.post("/contact", data);
  }
  
}

const contactDataService = new ContactDataService();
export default contactDataService;