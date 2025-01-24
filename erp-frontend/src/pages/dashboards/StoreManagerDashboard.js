import React, { useState, useEffect } from "react";
import axios from "axios";

const StoreManagerDashboard = () => {
    const [inventory, setInventory] = useState([]);
    const [sales, setSales] = useState([]);
    const [error, setError] = useState("");

    // Fetch inventory and sales data on component mount
    useEffect(() => {
        fetchInventory();
        fetchSales();
    }, []);

    const fetchInventory = async () => {
        try {
            const response = await axios.get("http://localhost:8080/products");
            setInventory(response.data);
        } catch (error) {
            console.error("Error fetching inventory:", error);
            setError("Failed to fetch inventory data.");
        }
    };

    const fetchSales = async () => {
        try {
            const response = await axios.get("http://localhost:8080/sales");
            const sortedSales = response.data.sort((a, b) => a.salesId - b.salesId);
            setSales(sortedSales);
        } catch (error) {
            console.error("Error fetching sales:", error);
            setError("Failed to fetch sales data.");
        }
    };

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
                    {inventory.map((product) => (
                        <tr key={product.productId}>
                            <td>{product.productId}</td>
                            <td>{product.productName}</td>
                            <td>{product.stockLevel}</td>
                            <td>{product.price}</td>
                            <td>{product.category}</td>
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
                        <th>Sales ID</th>
                        <th>Product ID</th>
                        <th>Product Name</th>
                        <th>Quantity</th>
                        <th>Price per Unit</th>
                        <th>Total Amount</th>
                        <th>Date of Sale</th>
                        <th>Payment Method</th>
                        <th>Store Name</th>
                        <th>Employee Name</th>
                    </tr>
                </thead>
                <tbody>
                    {sales.map((sale) =>
                        sale.products.map((product) => (
                            <tr key={`${sale.salesId}-${product.productId}`}>
                                <td>{sale.salesId}</td>
                                <td>{product.productId}</td>
                                <td>{product.productName}</td>
                                <td>{product.quantity}</td>
                                <td>{product.pricePerUnit}</td>
                                <td>{sale.totalAmount}</td>
                                <td>{sale.dateOfSale}</td>
                                <td>{sale.paymentMethod}</td>
                                <td>{sale.storeName}</td>
                                <td>{sale.employeeName}</td>
                            </tr>
                        ))
                    )}
                </tbody>
            </table>
        </div>
    );
};

export default StoreManagerDashboard;
