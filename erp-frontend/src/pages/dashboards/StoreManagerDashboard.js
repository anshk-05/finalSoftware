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
        <div className="p-5">
            <h1 className="text-3xl font-bold mb-5">Store Manager Dashboard</h1>
            {error && <p className="text-red-500 mb-4">{error}</p>}

            {/* Inventory Section */}
            <h2 className="text-2xl font-semibold mb-3">Inventory</h2>
            <div className="overflow-x-auto mb-8">
                <table className="table-auto w-full border-collapse border border-gray-300">
                    <thead>
                        <tr className="bg-gray-100">
                            <th className="border border-gray-300 px-4 py-2 text-left">ID</th>
                            <th className="border border-gray-300 px-4 py-2 text-left">Product Name</th>
                            <th className="border border-gray-300 px-4 py-2 text-left">Quantity</th>
                            <th className="border border-gray-300 px-4 py-2 text-left">Price</th>
                            <th className="border border-gray-300 px-4 py-2 text-left">Category</th>
                            <th className="border border-gray-300 px-4 py-2 text-left">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {inventory.map((product) => (
                            <tr key={product.productId} className="hover:bg-gray-50">
                                <td className="border border-gray-300 px-4 py-2">{product.productId}</td>
                                <td className="border border-gray-300 px-4 py-2">{product.productName}</td>
                                <td className="border border-gray-300 px-4 py-2">{product.stockLevel}</td>
                                <td className="border border-gray-300 px-4 py-2">{product.price}</td>
                                <td className="border border-gray-300 px-4 py-2">{product.category}</td>
                                <td className="border border-gray-300 px-4 py-2">
                                    <button className="bg-blue-500 text-white px-3 py-1 rounded mr-2 hover:bg-blue-600">
                                        Edit
                                    </button>
                                    <button className="bg-green-500 text-white px-3 py-1 rounded hover:bg-green-600">
                                        Update Quantity
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>

            {/* Sales Section */}
            <h2 className="text-2xl font-semibold mb-3">Sales Data</h2>
            <div className="overflow-x-auto">
                <table className="table-auto w-full border-collapse border border-gray-300">
                    <thead>
                        <tr className="bg-gray-100">
                            <th className="border border-gray-300 px-4 py-2 text-left">Sales ID</th>
                            <th className="border border-gray-300 px-4 py-2 text-left">Product ID</th>
                            <th className="border border-gray-300 px-4 py-2 text-left">Product Name</th>
                            <th className="border border-gray-300 px-4 py-2 text-left">Quantity</th>
                            <th className="border border-gray-300 px-4 py-2 text-left">Price per Unit</th>
                            <th className="border border-gray-300 px-4 py-2 text-left">Total Amount</th>
                            <th className="border border-gray-300 px-4 py-2 text-left">Date of Sale</th>
                            <th className="border border-gray-300 px-4 py-2 text-left">Payment Method</th>
                            <th className="border border-gray-300 px-4 py-2 text-left">Store Name</th>
                            <th className="border border-gray-300 px-4 py-2 text-left">Employee Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        {sales.map((sale) =>
                            sale.products.map((product) => (
                                <tr key={`${sale.salesId}-${product.productId}`} className="hover:bg-gray-50">
                                    <td className="border border-gray-300 px-4 py-2">{sale.salesId}</td>
                                    <td className="border border-gray-300 px-4 py-2">{product.productId}</td>
                                    <td className="border border-gray-300 px-4 py-2">{product.productName}</td>
                                    <td className="border border-gray-300 px-4 py-2">{product.quantity}</td>
                                    <td className="border border-gray-300 px-4 py-2">{product.pricePerUnit}</td>
                                    <td className="border border-gray-300 px-4 py-2">{sale.totalAmount}</td>
                                    <td className="border border-gray-300 px-4 py-2">{sale.dateOfSale}</td>
                                    <td className="border border-gray-300 px-4 py-2">{sale.paymentMethod}</td>
                                    <td className="border border-gray-300 px-4 py-2">{sale.storeName}</td>
                                    <td className="border border-gray-300 px-4 py-2">{sale.employeeName}</td>
                                </tr>
                            ))
                        )}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default StoreManagerDashboard;
