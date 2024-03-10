// RegisterUser.js

import React, { useState } from "react";
import userService from "../services/user-service";

export default function RegisterUser() {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    mobileNumber: "",
    pwd: "",
    role: "ROLE_USER", // Assuming all registered users are assigned the role ROLE_USER
  });
  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    userService
      .registerUser(formData)
      .then((response) => {
        setMessage(response.data);
      })
      .catch((error) => {
        setMessage(error.response.data);
      });
  };

  return (
    <div >
      <h2>User Registration</h2>
      <form onSubmit={handleSubmit}>
      <div className="form-floating mb-3">
									
          <input
            type="text"
            name="name"
            value={formData.name}
            onChange={handleChange}
            className="form-control"
            placeholder="Name"
            required 
            
          />
          <label>Name</label> 
        </div>
        <div className="form-floating mb-3">
          
          <input
            type="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            className="form-control"
            placeholder="Email"
            required
          />
          <label>Email</label>
        </div>
        <div className="form-floating mb-3">
         
          <input
            type="text"
            name="mobileNumber"
            value={formData.mobileNumber}
            onChange={handleChange}
            className="form-control"
            placeholder="Mobile Number"
            required
          />
           <label>Mobile Number</label>
        </div>
        <div className="form-floating mb-3">
         
          <input
            type="password"
            name="pwd"
            value={formData.pwd}
            onChange={handleChange}
            className="form-control"
            placeholder="Password"
            required
          />
           <label>Password</label>
        </div>
        <button type="submit" className="btn bg-btn">Register</button>
      </form>
      {message && <div>{message}</div>}
    </div>
  );
}
