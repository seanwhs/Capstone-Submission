//LoginDashboard.js
import React, { useState, useEffect } from 'react';
import AuthenticationService from '../services/AuthenticationService';

export default function LoginDashboard() {
    const [state, setState] = useState({ loggedinUser: '', greetId: '', greetMsg: '' });

    useEffect(() => {
        fetch(`http://localhost:8080/resource`, AuthenticationService.getAxiosConfig())
            .then(response => response.json())
            .then(result => {
                setState(prevState => ({
                    ...prevState,
                    loggedinUser: AuthenticationService.getLoggedinUser(),
                    greetId: result.id,
                    greetMsg: result.content
                }));
            });
    }, []);

    const logout = () => {
        if (window.confirm("Are you sure want to Logout?")) {
            AuthenticationService.logout();
            window.location.href = '/';
        }
    };

    return (
        <div className="container">
            <h2 className="mt-4">Welcome back, {state.loggedinUser}!</h2>
            <div className="card mt-4 bg-card">
                <div className="card-body">
                    {/* <p className="card-text">The ID is {state.greetId}</p> */}
                    <p className="card-text">Welcome {state.greetMsg}</p>
                    <button className="btn bg-btn" onClick={logout}>Logout</button>
                </div>
            </div>
        </div>
    );
};

