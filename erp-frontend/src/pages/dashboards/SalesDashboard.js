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
            .catch((error) => setErrorMessage("Failed to fetch products."));

        // Fetch sales history
        axios.get("http://localhost:8080/sales")
            .then((response) => setSalesHistory(response.data))
            .catch((error) => setErrorMessage("Failed to fetch sales history."));
    }, []);

    // Handle adding a product to the sale
    const addProductToSale = (product) => {
        if (product.stockLevel <= 0) {
            setErrorMessage(`Product ${product.productName} is out of stock.`);
            return;
        }

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
        setErrorMessage("");
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
            await axios.post("http://localhost:8080/sales", salePayload);
            setSuccessMessage("Sale created successfully!");
            setErrorMessage("");

            // Refresh sales history
            axios.get("http://localhost:8080/sales")
                .then((response) => setSalesHistory(response.data))
                .catch(() => setErrorMessage("Failed to refresh sales history."));

            // Reset form
            setSelectedProducts([]);
            setPaymentMethod("");
            setStoreId("");
            setEmployeeId("");
        } catch (error) {
            setErrorMessage("Failed to create sale. Please try again.");
            setSuccessMessage("");
        }
    };

    return (
        <div className="p-6">
            <h1 className="text-3xl font-semibold mb-4">Sales Dashboard</h1>
            {errorMessage && <p className="text-red-500 mb-4">{errorMessage}</p>}
            {successMessage && <p className="text-green-500 mb-4">{successMessage}</p>}

            <h2 className="text-2xl font-semibold mt-8 mb-4">Available Products</h2>
            <table className="w-full table-auto border-collapse mb-6">
                <thead>
                    <tr className="bg-gray-100">
                        <th className="py-2 px-4 border-b">Product Name</th>
                        <th className="py-2 px-4 border-b">Price</th>
                        <th className="py-2 px-4 border-b">Stock Level</th>
                        <th className="py-2 px-4 border-b">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {products.map((product) => (
                        <tr key={product.productId} className="hover:bg-gray-50">
                            <td className="py-2 px-4 border-b">{product.productName}</td>
                            <td className="py-2 px-4 border-b">${product.price.toFixed(2)}</td>
                            <td className="py-2 px-4 border-b">{product.stockLevel}</td>
                            <td className="py-2 px-4 border-b">
                                <button
                                    className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-700"
                                    onClick={() => addProductToSale(product)}
                                >
                                    Add to Sale
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

            <h2 className="text-2xl font-semibold mt-8 mb-4">Create a Sale</h2>
            <div className="space-y-4">
                <div>
                    <label className="block text-sm font-medium">Store ID:</label>
                    <input
                        type="text"
                        value={storeId}
                        onChange={(e) => setStoreId(e.target.value)}
                        className="mt-1 p-2 border border-gray-300 rounded w-full"
                    />
                </div>
                <div>
                    <label className="block text-sm font-medium">Employee ID:</label>
                    <input
                        type="text"
                        value={employeeId}
                        onChange={(e) => setEmployeeId(e.target.value)}
                        className="mt-1 p-2 border border-gray-300 rounded w-full"
                    />
                </div>
                <div>
                    <label className="block text-sm font-medium">Payment Method:</label>
                    <select
                        value={paymentMethod}
                        onChange={(e) => setPaymentMethod(e.target.value)}
                        className="mt-1 p-2 border border-gray-300 rounded w-full"
                    >
                        <option value="">Select</option>
                        <option value="Credit Card">Credit Card</option>
                        <option value="Debit Card">Debit Card</option>
                        <option value="Cash">Cash</option>
                    </select>
                </div>
            </div>

            <h3 className="text-xl font-semibold mt-8">Products in Sale</h3>
            {selectedProducts.length > 0 ? (
                <ul className="space-y-2">
                    {selectedProducts.map((product) => (
                        <li key={product.productId} className="flex justify-between">
                            <span>{product.productName} - Quantity: {product.quantity}</span>
                            <span>${(product.price * product.quantity).toFixed(2)}</span>
                        </li>
                    ))}
                </ul>
            ) : (
                <p>No products added to the sale yet.</p>
            )}

            <button
                onClick={submitSale}
                className="mt-4 px-6 py-2 bg-green-500 text-white rounded hover:bg-green-700"
            >
                Submit Sale
            </button>

            <h2 className="text-2xl font-semibold mt-8 mb-4">Sales History</h2>
            <table className="w-full table-auto border-collapse">
                <thead>
                    <tr className="bg-gray-100">
                        <th className="py-2 px-4 border-b">Sale ID</th>
                        <th className="py-2 px-4 border-b">Date</th>
                        <th className="py-2 px-4 border-b">Total Amount</th>
                        <th className="py-2 px-4 border-b">Payment Method</th>
                    </tr>
                </thead>
                <tbody>
                    {salesHistory.map((sale) => (
                        <tr key={sale.salesId} className="hover:bg-gray-50">
                            <td className="py-2 px-4 border-b">{sale.salesId}</td>
                            <td className="py-2 px-4 border-b">{sale.dateOfSale}</td>
                            <td className="py-2 px-4 border-b">${sale.totalAmount.toFixed(2)}</td>
                            <td className="py-2 px-4 border-b">{sale.paymentMethod}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default SalesDashboard;
