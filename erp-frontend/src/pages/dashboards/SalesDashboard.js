import React, { useState, useEffect } from "react";
import axios from "axios";

const SalesDashboard = () => {
    // State variables
    const [products, setProducts] = useState([]);
    const [salesHistory, setSalesHistory] = useState([]);
    const [selectedProducts, setSelectedProducts] = useState([]);
    const [paymentMethod, setPaymentMethod] = useState("");
    const [storeId, setStoreId] = useState("");
    const [employeeId, setEmployeeId] = useState("");
    const [errorMessage, setErrorMessage] = useState("");
    const [successMessage, setSuccessMessage] = useState("");

    // Fetch available products and sales history
    useEffect(() => {
        // Fetch products
        axios.get("http://localhost:8080/products")
            .then((response) => setProducts(response.data))
            .catch((error) => console.error("Error fetching products:", error));

        // Fetch sales history
        axios.get("http://localhost:8080/sales")
            .then((response) => setSalesHistory(response.data))
            .catch((error) => console.error("Error fetching sales history:", error));
    }, []);

    // Handle adding a product to the sale
    const addProductToSale = (product) => {
        const existingProduct = selectedProducts.find((p) => p.productId === product.productId);
        if (existingProduct) {
            setSelectedProducts((prev) =>
                prev.map((p) =>
                    p.productId === product.productId
                        ? { ...p, quantity: p.quantity + 1 }
                        : p
                )
            );
        } else {
            setSelectedProducts((prev) => [...prev, { ...product, quantity: 1 }]);
        }
    };

    // Handle submitting the sale
    const submitSale = async () => {
        if (!storeId || !employeeId || !paymentMethod || selectedProducts.length === 0) {
            setErrorMessage("Please fill out all fields and add at least one product to the sale.");
            return;
        }

        // Prepare the sale payload
        const salePayload = {
            dateOfSale: new Date().toISOString().split("T")[0], // Current date
            totalAmount: selectedProducts.reduce((total, product) => total + product.price * product.quantity, 0),
            paymentMethod,
            storeId: parseInt(storeId),
            employeeId: parseInt(employeeId),
            products: selectedProducts.map((product) => ({
                productId: product.productId,
                quantity: product.quantity,
                pricePerUnit: product.price,
            })),
        };

        // Send the sale payload to the backend
        try {
            const response = await axios.post("http://localhost:8080/sales", salePayload);
            setSuccessMessage("Sale created successfully!");
            setErrorMessage("");
            console.log("Response:", response.data);

            // Refresh sales history
            axios.get("http://localhost:8080/sales")
                .then((response) => setSalesHistory(response.data))
                .catch((error) => console.error("Error fetching sales history:", error));

            // Reset form
            setSelectedProducts([]);
            setPaymentMethod("");
            setStoreId("");
            setEmployeeId("");
        } catch (error) {
            console.error("Error creating sale:", error);
            setErrorMessage("Failed to create sale. Please check your input.");
            setSuccessMessage("");
        }
    };

    return (
        <div style={{ padding: "20px" }}>
            <h1>Sales Dashboard</h1>
            {errorMessage && <p style={{ color: "red" }}>{errorMessage}</p>}
            {successMessage && <p style={{ color: "green" }}>{successMessage}</p>}

            <h2>Available Products</h2>
            <table border="1" style={{ width: "100%", textAlign: "center" }}>
                <thead>
                    <tr>
                        <th>Product Name</th>
                        <th>Price</th>
                        <th>Stock Level</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {products.map((product) => (
                        <tr key={product.productId}>
                            <td>{product.productName}</td>
                            <td>{product.price}</td>
                            <td>{product.stockLevel}</td>
                            <td>
                                <button onClick={() => addProductToSale(product)}>Add to Sale</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

            <h2>Create a Sale</h2>
            <div>
                <label>Store ID:</label>
                <input
                    type="text"
                    value={storeId}
                    onChange={(e) => setStoreId(e.target.value)}
                />
            </div>
            <div>
                <label>Employee ID:</label>
                <input
                    type="text"
                    value={employeeId}
                    onChange={(e) => setEmployeeId(e.target.value)}
                />
            </div>
            <div>
                <label>Payment Method:</label>
                <select
                    value={paymentMethod}
                    onChange={(e) => setPaymentMethod(e.target.value)}
                >
                    <option value="">Select</option>
                    <option value="Credit Card">Credit Card</option>
                    <option value="Debit Card">Debit Card</option>
                    <option value="Cash">Cash</option>
                </select>
            </div>

            <h3>Products in Sale</h3>
            <ul>
                {selectedProducts.map((product) => (
                    <li key={product.productId}>
                        Product ID: {product.productId}, Quantity: {product.quantity}, Price Per Unit: {product.price}
                    </li>
                ))}
            </ul>

            <button onClick={submitSale}>Submit Sale</button>

            <h2>Sales History</h2>
            <table border="1" style={{ width: "100%", textAlign: "center" }}>
                <thead>
                    <tr>
                        <th>Sale ID</th>
                        <th>Date</th>
                        <th>Total Amount</th>
                        <th>Payment Method</th>
                    </tr>
                </thead>
                <tbody>
                    {salesHistory.map((sale) => (
                        <tr key={sale.salesId}>
                            <td>{sale.salesId}</td>
                            <td>{sale.dateOfSale}</td>
                            <td>{sale.totalAmount}</td>
                            <td>{sale.paymentMethod}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default SalesDashboard;
