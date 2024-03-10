//Navbar.js
import React from "react";
import { Link } from "react-router-dom";
import "../App.css"

export default function Navbar() {
  return (
    <nav className="navbar navbar-expand-lg bg-navbar-footer">
      <div className="container-fluid">
        <Link className="navbar-brand" to="/home"><strong>
          Bank Duit
        </strong>
        </Link>

        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav me-auto mb-2 mb-lg-0">
            <li className="nav-item">
              <Link className="nav-link" to="/notices-list">
                Notices
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/add-notice">
                Add Notice
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/contact">
                Contact Us
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/register"> {/* Add link for registration */}
                Register
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/login">
                Login
              </Link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
}
