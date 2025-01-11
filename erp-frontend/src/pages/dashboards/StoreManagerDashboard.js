import React, { useEffect, useState } from "react";
import axios from "axios";

const StoreManagerDashboard = () => {
    const [sales, setSales] = useState([]);
    const [inventory, setInventory] = useState([]);
    const [error, setError] = useState("");

    // Fetch sales and inventory data
    useEffect(() => {
        // Fetch sales
        axios.get("http://localhost:8080/sales")
            .then((response) => setSales(response.data))
            .catch(() => setError("Failed to load sales data."));

        // Fetch inventory
        axios.get("http://localhost:8080/inventory")
            .then((response) => setInventory(response.data))
            .catch(() => setError("Failed to load inventory data."));
    }, []);

    return (
        <div style={{ textAlign: "center", marginTop: "20px" }}>
            <h1>Store Manager Dashboard</h1>
            {error && <p style={{ color: "red" }}>{error}</p>}

            <section>
                <h2>Sales Analysis</h2>
                <table border="1" style={{ margin: "0 auto" }}>
                    <thead>
                        <tr>
                            <th>Sales ID</th>
                            <th>Date</th>
                            <th>Employee ID</th>
                            <th>Total Amount</th>
                            <th>Payment Method</th>
                        </tr>
                    </thead>
                    <tbody>
                        {sales.map((sale) => (
                            <tr key={sale.salesId}>
                                <td>{sale.salesId}</td>
                                <td>{sale.dateOfSale}</td>
                                <td>{sale.employeeId}</td>
                                <td>{sale.totalAmount}</td>
                                <td>{sale.paymentMethod}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </section>

            <section>
                <h2>Inventory Tracking</h2>
                <table border="1" style={{ margin: "20px auto" }}>
                    <thead>
                        <tr>
                            <th>Product ID</th>
                            <th>Product Name</th>
                            <th>Stock Level</th>
                            <th>Reorder Threshold</th>
                        </tr>
                    </thead>
                    <tbody>
                        {inventory.map((item) => (
                            <tr key={item.inventoryId}>
                                <td>{item.product.productId}</td>
                                <td>{item.product.productName}</td>
                                <td>{item.stockLevel}</td>
                                <td>{item.reorderThreshold}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </section>
        </div>
    );
};

export default StoreManagerDashboard;
