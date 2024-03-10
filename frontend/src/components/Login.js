//Login.js
import React, { useState } from 'react';
import AuthenticationService from '../services/AuthenticationService';

export default function Login() {
    const [formData, setFormData] = useState({ username: '', password: '' });
    const [isLoginFailed, setIsLoginFailed] = useState(false);

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log("Attempting authentication...");
        AuthenticationService.authenticate(formData.username, formData.password)
            .then(() => {
                AuthenticationService.registerUserInSession(formData.username, formData.password);
                console.log("Authentication successful");
                window.location.href = '/';
            })
            .catch(() => {
                console.log("Authentication failed");
                setIsLoginFailed(true);
            });
    };

    return (
        <div className="container">
            <div className="row justify-content-center">
                <div className="col-md-6">
                    <div className="card mt-4 bg-card">
                        <div className="card-body">
                            <h2 className="card-title">Login Here</h2>
                            {isLoginFailed && <div className="alert alert-danger">Invalid Credentials</div>}
                            <form onSubmit={handleSubmit}>
                                <div className="form-group">
                                    <label>Username:</label>
                                    <input type="text" name="username" value={formData.username} onChange={handleChange} className="form-control" placeholder="Username" required />
                                </div>
                                <div className="form-group">
                                    <label>Password:</label>
                                    <input type="password" name="password" value={formData.password} onChange={handleChange} className="form-control" placeholder="Password" required />
                                </div>
                                <button type="submit" className="btn bg-btn">Submit</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

