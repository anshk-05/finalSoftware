import React, { useEffect, useState } from "react";
import axios from "axios";
import { Bar } from "react-chartjs-2";
import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend,
} from "chart.js";

// Register the ChartJS modules
ChartJS.register(CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend);

const FinanceTeamDashboard = () => {
    const [salesTrends, setSalesTrends] = useState([]);
    const [revenue, setRevenue] = useState(0);
    const [expenses, setExpenses] = useState(0);
    const [profit, setProfit] = useState(0);
    const [error, setError] = useState("");

    useEffect(() => {
        fetchFinanceData();
    }, []);

    const fetchFinanceData = async () => {
        try {
            // Fetch Sales Trends
            const salesTrendsResponse = await axios.get(
                "http://localhost:8080/finance/sales-trends"
            );
            const salesTrendsObject = salesTrendsResponse.data;

            // Transform sales trends object into an array
            const transformedSalesTrends = Object.entries(salesTrendsObject).map(
                ([month, sales]) => ({
                    month,
                    sales,
                })
            );
            setSalesTrends(transformedSalesTrends);

            // Fetch Total Revenue
            const revenueResponse = await axios.get(
                "http://localhost:8080/finance/revenue"
            );
            setRevenue(revenueResponse.data);

            // Fetch Total Expenses
            const expensesResponse = await axios.get(
                "http://localhost:8080/finance/expenses"
            );
            setExpenses(expensesResponse.data);

            // Fetch Profit
            const profitResponse = await axios.get(
                "http://localhost:8080/finance/profit"
            );
            setProfit(profitResponse.data);
        } catch (err) {
            console.error("Failed to fetch financial data:", err);
            setError("Failed to fetch financial data.");
        }
    };

    return (
        <div className="p-5">
            <h1 className="text-3xl font-bold mb-5">Finance Team Dashboard</h1>
            {error && <p className="text-red-500 mb-4">{error}</p>}

            {/* Revenue Overview */}
            <div className="mb-6 p-4 bg-white shadow rounded-lg">
                <h2 className="text-2xl font-semibold mb-2">Revenue Overview</h2>
                <p className="text-lg">Total Revenue: ${revenue.toFixed(2)}</p>
            </div>

            {/* Expenses Overview */}
            <div className="mb-6 p-4 bg-white shadow rounded-lg">
                <h2 className="text-2xl font-semibold mb-2">Expenses Overview</h2>
                <p className="text-lg">Total Expenses: ${expenses.toFixed(2)}</p>
            </div>

            {/* Profit Overview */}
            <div className="mb-6 p-4 bg-white shadow rounded-lg">
                <h2 className="text-2xl font-semibold mb-2">Profit</h2>
                <p className="text-lg">Current Profit: ${profit.toFixed(2)}</p>
            </div>

            {/* Sales Trends */}
            <div className="mb-6 p-4 bg-white shadow rounded-lg">
                <h2 className="text-2xl font-semibold mb-4">Sales Trends</h2>
                {salesTrends.length > 0 ? (
                    <Bar
                        data={{
                            labels: salesTrends.map((trend) => trend.month), // Extract month names
                            datasets: [
                                {
                                    label: "Sales Trends",
                                    data: salesTrends.map((trend) => trend.sales), // Extract sales values
                                    backgroundColor: "rgba(75,192,192,0.4)",
                                    borderColor: "rgba(75,192,192,1)",
                                    borderWidth: 1,
                                },
                            ],
                        }}
                        options={{
                            responsive: true,
                            plugins: {
                                legend: {
                                    position: "top",
                                },
                                title: {
                                    display: true,
                                    text: "Sales Trends Over Time",
                                },
                            },
                            scales: {
                                y: {
                                    beginAtZero: true,
                                    ticks: {
                                        stepSize: 1000,
                                    },
                                },
                            },
                        }}
                    />
                ) : (
                    <p className="text-gray-500">No sales trends data available.</p>
                )}
            </div>
        </div>
    );
};

export default FinanceTeamDashboard;
