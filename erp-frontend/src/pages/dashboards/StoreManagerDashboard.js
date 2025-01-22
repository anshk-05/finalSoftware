import React, { useState, useEffect } from "react";
import axios from "axios";

const StoreManagerDashboard = () => {
    const [inventory, setInventory] = useState([]);
    const [sales, setSales] = useState([]);
    const [error, setError] = useState("");

    // Fetch inventory data
    const fetchInventory = async () => {
        try {
            const response = await axios.get("http://localhost:8080/inventory");
            console.log(response.data); // Log the API response
            setInventory(response.data);
        } catch (error) {
            console.error("Error fetching inventory:", error);
            setError("Failed to fetch inventory data.");
        }
    };

    // Fetch sales data
    const fetchSales = async () => {
        try {
            const response = await axios.get("http://localhost:8080/sales");
            setSales(response.data);
        } catch (err) {
            console.error("Error fetching sales data:", err);
            setError("Failed to fetch sales data.");
        }
    };

    // Fetch inventory and sales data on component mount
    useEffect(() => {
        fetchInventory();
        fetchSales();
    }, []);

    return (
        <div style={{ padding: "20px" }}>
            <h1>Store Manager Dashboard</h1>
            {error && <p style={{ color: "red" }}>{error}</p>}

            {/* Inventory Section */}
            <h2>Inventory</h2>
            <table border="1" style={{ width: "100%", marginBottom: "20px" }}>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Product Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Category</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {inventory.map((item) => (
                        <tr key={item.inventoryId}>
                            <td>{item.inventoryId}</td>
                            <td>{item.productName}</td>
                            <td>{item.stockLevel}</td>
                            <td>{item.price}</td>
                            <td>{item.category}</td>
                            <td>
                                <button>Edit</button>
                                <button>Update Quantity</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

            {/* Sales Section */}
            <h2>Sales Data</h2>
            <table border="1" style={{ width: "100%", marginBottom: "20px" }}>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Product Name</th>
                        <th>Quantity Sold</th>
                        <th>Total Amount</th>
                        <th>Sale Date</th>
                    </tr>
                </thead>
                <tbody>
                    {sales.map((sale) => (
                        <tr key={sale.saleId}>
                            <td>{sale.saleId}</td>
                            <td>{sale.productName}</td>
                            <td>{sale.quantity}</td>
                            <td>{sale.totalAmount}</td>
                            <td>{sale.saleDate}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default StoreManagerDashboard;
