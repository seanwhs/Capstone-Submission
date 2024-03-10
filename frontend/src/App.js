import React from "react";
import { Navigate } from "react-router";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import Home from "./components/Home";
import AddNotice from "./components/AddNotice";
import Notice from "./components/Notice";
import NoticesList from "./components/NoticesList";
import ContactForm from "./components/ContactForm";
import Footer from "./components/Footer";
import Login from "./components/Login";
import LoginDashboard from "./components/LoginDashboard";
import AuthenticationService from "./services/AuthenticationService";
import RegisterUser from "./components/RegisterUser";
import "./App.css";

export default function App() {
  return (
    <BrowserRouter>
      <div
        style={{ display: "flex", flexDirection: "column", minHeight: "100vh" }}
        className="font-family"
      >
        <div>
          <Navbar />
        </div>

        <div className="container mt-6 py-3 border shadow flex-grow-1 bg-container">
          <Routes>
            <Route path="/home" element={<Home />} />
            {/* Routes for Notices */}
            <Route path="/add-notice" element={<AddNotice />} />
            <Route path="/notices-list" element={<NoticesList />} />
            <Route path="/notices/:id" element={<Notice />} />
            {/* Route for Contact Us form */}
            <Route path="/contact" element={<ContactForm />} />

            {/* Route for Login Test */}
            <Route  path='/' element={ AuthenticationService.isUserLoggedin() ? <LoginDashboard/> : <Navigate replace to={"/login"}/> } />
			      <Route  path='/login' element={ <Login/> } />
          
            {/* Route for Registering User */}
            <Route path="/register" element={<RegisterUser />} />
          </Routes>
        </div>

        <div>
          <Footer />
        </div>
      </div>
    </BrowserRouter>
  );
}
