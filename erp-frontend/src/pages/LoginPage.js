import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const LoginPage = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const navigate = useNavigate();

    const handleLogin = (e) => {
        e.preventDefault();
    
        // Mock role assignment based on username
        if (username === "store_manager") {
            navigate("/store-manager-dashboard");
        } else if (username === "admin") {
            navigate("/admin-dashboard");
        } else if (username === "manager") {
            navigate("/manager-dashboard");
        } else if (username === "employee") {
            navigate("/employee-dashboard");
        } else {
            setError("Invalid username. Try 'store_manager', 'admin', etc.");
        }
    };
    
    

    return (
        <div style={{ textAlign: "center", marginTop: "50px" }}>
            <h1>Login</h1>
            <form onSubmit={handleLogin}>
                <div>
                    <input
                        type="text"
                        placeholder="Username"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <input
                        type="password"
                        placeholder="Password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>
                <button type="submit">Login</button>
            </form>

            {error && <p style={{ color: "red" }}>{error}</p>}
        </div>
    );
};

export default LoginPage;
