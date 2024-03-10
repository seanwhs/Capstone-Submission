//Footer.js
import React from "react";
import "../App.css";

export default function Footer() {
  return (
    <footer className="footer mt-auto py-0 pt-2 bg-navbar-footer">
      <div className="container">
        <div className="row">
          <div className="col-md-4">
            <h5 style={{ fontSize: "14px" }}><strong>Contact Us</strong></h5>
            <p style={{ fontSize: "12px" }}>
              Address: 123 Duit Lane, Metropolis, Singapore <br />
              Phone: +65-6011-7890 <br />
              Email: info@bank-duit.com
            </p>
          </div>
          <div className="col-md-4">
            <h5 style={{ fontSize: "14px" }}><strong>Quick Links</strong></h5>
            <ul className="list-unstyled" style={{ fontSize: "12px" }}>
              <li>
                <a href="#" style={{ fontSize: "12px" }}>
                  Home
                </a>
              </li>
              <li>
                <a href="#" style={{ fontSize: "12px" }}>
                  Accounts
                </a>
              </li>
              <li>
                <a href="#" style={{ fontSize: "12px" }}>
                  Transactions
                </a>
              </li>
              <li>
                <a href="#" style={{ fontSize: "12px" }}>
                  About Us
                </a>
              </li>
            </ul>
          </div>
          <div className="col-md-4">
            <h5 style={{ fontSize: "14px" }}><strong>Follow Us</strong></h5>
            <ul className="list-unstyled" style={{ fontSize: "12px" }}>
              <li>
                <a href="#" style={{ fontSize: "12px", color: "#3b5998" }}>
                  <i className="fab fa-facebook"></i>
                </a>
              </li>{" "}
              {/* Adjusted icon color for Facebook */}
              <li>
                <a href="#" style={{ fontSize: "12px", color: "#1da1f2" }}>
                  <i className="fab fa-twitter"></i>
                </a>
              </li>{" "}
              {/* Adjusted icon color for Twitter */}
              <li>
                <a href="#" style={{ fontSize: "12px", color: "#0077b5" }}>
                  <i className="fab fa-linkedin"></i>
                </a>
              </li>{" "}
              {/* Adjusted icon color for LinkedIn */}
              <li>
                <a href="#" style={{ fontSize: "12px", color: "#c32aa3" }}>
                  <i className="fab fa-instagram"></i>
                </a>
              </li>{" "}
              {/* Adjusted icon color for Instagram */}
            </ul>
          </div>
        </div>
        <div className="row">
          <div className="col-md-12 text-center">
            <strong>
              <p className="mb-0" style={{ fontSize: "12px" }}>
                © {new Date().getFullYear()} Bank Duit Portal. All rights
                reserved.
              </p>
            </strong>
          </div>
        </div>
      </div>
    </footer>
  );
}
