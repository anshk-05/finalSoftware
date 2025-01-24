import React, { useState, useEffect } from "react";
import axios from "axios";

const ProcurementDashboard = () => {
  const [purchaseOrders, setPurchaseOrders] = useState([]);
  const [error, setError] = useState("");

  // Fetch purchase orders data
  useEffect(() => {
    const fetchPurchaseOrders = async () => {
      try {
        const response = await axios.get("http://localhost:8080/purchase-orders");
        setPurchaseOrders(response.data);
      } catch (err) {
        console.error("Error fetching purchase orders:", err);
        setError("Failed to fetch purchase orders data.");
      }
    };
    fetchPurchaseOrders();
  }, []);

  return (
    <div style={{ padding: "20px" }}>
      <h1>Procurement Team Dashboard</h1>
      {error && <p style={{ color: "red" }}>{error}</p>}

      {/* Purchase Orders Section */}
      <h2>Purchase Orders</h2>
      <table border="1" style={{ width: "100%", marginBottom: "20px" }}>
        <thead>
          <tr>
            <th>Order ID</th>
            <th>Order Date</th>
            <th>Delivery Date</th>
            <th>Total Amount</th>
            <th>Status</th>
            <th>Supplier</th>
            <th>Products</th>
          </tr>
        </thead>
        <tbody>
          {purchaseOrders.map((order) => (
            <tr key={order.purchaseOrderId}>
              <td>{order.purchaseOrderId}</td>
              <td>{order.orderDate}</td>
              <td>{order.deliveryDate}</td>
              <td>${order.totalAmount.toFixed(2)}</td>
              <td>{order.orderStatus}</td>
              <td>{order.supplierName}</td>
              <td>
                <table border="1" style={{ width: "100%" }}>
                  <thead>
                    <tr>
                      <th>Product ID</th>
                      <th>Name</th>
                      <th>Quantity</th>
                      <th>Price Per Unit</th>
                    </tr>
                  </thead>
                  <tbody>
                    {order.products.map((product) => (
                      <tr key={product.productId}>
                        <td>{product.productId}</td>
                        <td>{product.productName}</td>
                        <td>{product.quantity}</td>
                        <td>${product.pricePerUnit.toFixed(2)}</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ProcurementDashboard;
