import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import StoreManagerDashboard from "./pages/dashboards/StoreManagerDashboard";
import HRManagerDashboard from "./pages/dashboards/HRManagerDashboard";
import FinanceTeamDashboard from "./pages/dashboards/FinanceTeamDashboard.js";
import ProcurementDashboard from "./pages/dashboards/ProcurementDashboard.js";
import WarehouseManagerDashboard from "./pages/dashboards/WarehouseManagerDashboard";
import ExecutiveDashboard from "./pages/dashboards/ExecutiveDashboard";
import ITAdminDashboard from "./pages/dashboards/ITAdminDashboard";

const App = () => {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<LoginPage />} />
                <Route path="/store-manager" element={<StoreManagerDashboard />} />
                <Route path="/hr-manager" element={<HRManagerDashboard />} />
                <Route path="/finance" element={<FinanceTeamDashboard />} />
                <Route path="/procurement" element={<ProcurementDashboard />} />
                <Route path="/warehouse" element={<WarehouseManagerDashboard />} />
                <Route path="/executive" element={<ExecutiveDashboard />} />
                <Route path="/it-admin" element={<ITAdminDashboard />} />
            </Routes>
        </Router>
    );
};

export default App;
