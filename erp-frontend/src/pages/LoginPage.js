import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const LoginPage = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        console.log("Attempting login with:", { username, password }); // Log input data
        try {
            const response = await axios.post("http://localhost:8080/auth/login", {
                username,
                password,
            });
            console.log("Response from backend:", response.data); // Log backend response
            const { token, role } = response.data;
    
            sessionStorage.setItem("token", token);
            sessionStorage.setItem("role", role);
    
            switch (role) {
                case "Store Manager":
                    navigate("/store-manager");
                    break;
                case "HR Manager":
                    navigate("/hr-manager");
                    break;
                // Add other roles here
                default:
                    setError("No dashboard available for this role.");
            }
        } catch (err) {
            console.error("Error:", err); // Log error
            setError("Invalid username or password.");
        }
    };
    

    return (
        <div style={{ textAlign: "center", marginTop: "50px" }}>
            <h2>Login to ERP System</h2>
            {error && <p style={{ color: "red" }}>{error}</p>}
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
                        required
                    />
                </div>
                <button type="submit">Login</button>
            </form>
        </div>
    );
};

export default LoginPage;
