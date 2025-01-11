import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import StoreManagerDashboard from "./pages/dashboards/StoreManagerDashboard";

const App = () => {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<LoginPage />} />
                <Route path="/admin-dashboard" element={<h1>Admin Dashboard</h1>} />
                <Route path="/manager-dashboard" element={<h1>Manager Dashboard</h1>} />
                <Route path="/employee-dashboard" element={<h1>Employee Dashboard</h1>} />
                <Route path="/store-manager-dashboard" element={<StoreManagerDashboard />} />
            </Routes>
        </Router>
    );
};

export default App;
