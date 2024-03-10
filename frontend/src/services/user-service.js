// user-service.js

import { http } from "./http-common";

const registerUser = (userData) => {
  return http.post("/register", userData);
};

export default {
  registerUser,
};
