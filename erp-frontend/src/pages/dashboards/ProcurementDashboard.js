import React, { useState, useEffect } from "react";
import axios from "axios";

const ProcurementDashboard = () => {
  const [purchaseOrders, setPurchaseOrders] = useState([]);
  const [error, setError] = useState("");
  const [newOrder, setNewOrder] = useState({
    orderDate: "",
    deliveryDate: "",
    totalAmount: "",
    orderStatus: "",
    supplierName: "",
    products: [],
  });
  const [editOrder, setEditOrder] = useState(null);

  // Fetch purchase orders data
  useEffect(() => {
    fetchPurchaseOrders();
  }, []);

  const fetchPurchaseOrders = async () => {
    try {
      const response = await axios.get("http://localhost:8080/purchase-orders");
      setPurchaseOrders(response.data);
    } catch (err) {
      console.error("Error fetching purchase orders:", err);
      setError("Failed to fetch purchase orders data.");
    }
  };

  const handleAddOrder = async (e) => {
    e.preventDefault();
    try {
      await axios.post("http://localhost:8080/purchase-orders", newOrder);
      setNewOrder({
        orderDate: "",
        deliveryDate: "",
        totalAmount: "",
        orderStatus: "",
        supplierName: "",
        products: [],
      });
      fetchPurchaseOrders();
    } catch (err) {
      setError("Failed to add purchase order.");
    }
  };

  const handleEditOrder = async (e) => {
    e.preventDefault();
    try {
      await axios.put(
        `http://localhost:8080/purchase-orders/${editOrder.purchaseOrderId}`,
        editOrder
      );
      setEditOrder(null);
      fetchPurchaseOrders();
    } catch (err) {
      setError("Failed to update purchase order.");
    }
  };

  const handleDeleteOrder = async (id) => {
    if (window.confirm("Are you sure you want to delete this order?")) {
      try {
        await axios.delete(`http://localhost:8080/purchase-orders/${id}`);
        fetchPurchaseOrders();
      } catch (err) {
        setError("Failed to delete purchase order.");
      }
    }
  };

  return (
    <div className="p-6 bg-gray-100 min-h-screen">
      <h1 className="text-3xl font-bold mb-6">Procurement Team Dashboard</h1>
      {error && <p className="text-red-500 mb-4">{error}</p>}

      {/* Purchase Orders Section */}
      <h2 className="text-2xl font-semibold mb-4">Purchase Orders</h2>
      <table className="w-full bg-white shadow-md rounded-lg overflow-hidden">
        <thead className="bg-gray-200">
          <tr>
            <th className="px-4 py-2">Order ID</th>
            <th className="px-4 py-2">Order Date</th>
            <th className="px-4 py-2">Delivery Date</th>
            <th className="px-4 py-2">Total Amount</th>
            <th className="px-4 py-2">Status</th>
            <th className="px-4 py-2">Supplier</th>
            <th className="px-4 py-2">Products</th>
            <th className="px-4 py-2">Actions</th>
          </tr>
        </thead>
        <tbody>
          {purchaseOrders.map((order) => (
            <tr key={order.purchaseOrderId} className="border-b">
              <td className="px-4 py-2">{order.purchaseOrderId}</td>
              <td className="px-4 py-2">{order.orderDate}</td>
              <td className="px-4 py-2">{order.deliveryDate}</td>
              <td className="px-4 py-2">${order.totalAmount.toFixed(2)}</td>
              <td className="px-4 py-2">{order.orderStatus}</td>
              <td className="px-4 py-2">{order.supplierName}</td>
              <td className="px-4 py-2">
                <table className="w-full bg-gray-100">
                  <thead>
                    <tr>
                      <th className="px-2 py-1">Product ID</th>
                      <th className="px-2 py-1">Name</th>
                      <th className="px-2 py-1">Quantity</th>
                      <th className="px-2 py-1">Price</th>
                    </tr>
                  </thead>
                  <tbody>
                    {order.products.map((product) => (
                      <tr key={product.productId}>
                        <td className="px-2 py-1">{product.productId}</td>
                        <td className="px-2 py-1">{product.productName}</td>
                        <td className="px-2 py-1">{product.quantity}</td>
                        <td className="px-2 py-1">${product.pricePerUnit.toFixed(2)}</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </td>
              <td className="px-4 py-2">
                <button
                  className="bg-blue-500 text-white px-3 py-1 rounded-md mr-2"
                  onClick={() => setEditOrder(order)}
                >
                  Edit
                </button>
                <button
                  className="bg-red-500 text-white px-3 py-1 rounded-md"
                  onClick={() => handleDeleteOrder(order.purchaseOrderId)}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* Add Purchase Order */}
      <h2 className="text-2xl font-semibold mt-8 mb-4">Add Purchase Order</h2>
      <form className="bg-white p-6 shadow-md rounded-lg" onSubmit={handleAddOrder}>
        <div className="grid grid-cols-2 gap-4 mb-4">
          <input
            type="date"
            className="border px-4 py-2 rounded-md"
            placeholder="Order Date"
            value={newOrder.orderDate}
            onChange={(e) => setNewOrder({ ...newOrder, orderDate: e.target.value })}
            required
          />
          <input
            type="date"
            className="border px-4 py-2 rounded-md"
            placeholder="Delivery Date"
            value={newOrder.deliveryDate}
            onChange={(e) => setNewOrder({ ...newOrder, deliveryDate: e.target.value })}
            required
          />
          <input
            type="number"
            className="border px-4 py-2 rounded-md"
            placeholder="Total Amount"
            value={newOrder.totalAmount}
            onChange={(e) => setNewOrder({ ...newOrder, totalAmount: parseFloat(e.target.value) })}
            required
          />
          <input
            type="text"
            className="border px-4 py-2 rounded-md"
            placeholder="Order Status"
            value={newOrder.orderStatus}
            onChange={(e) => setNewOrder({ ...newOrder, orderStatus: e.target.value })}
            required
          />
          <input
            type="text"
            className="border px-4 py-2 rounded-md"
            placeholder="Supplier Name"
            value={newOrder.supplierName}
            onChange={(e) => setNewOrder({ ...newOrder, supplierName: e.target.value })}
            required
          />
        </div>
        <button className="bg-green-500 text-white px-4 py-2 rounded-md">Add Order</button>
      </form>

      {/* Edit Purchase Order */}
      {editOrder && (
        <div className="mt-8">
          <h2 className="text-2xl font-semibold mb-4">Edit Purchase Order</h2>
          <form className="bg-white p-6 shadow-md rounded-lg" onSubmit={handleEditOrder}>
            <div className="grid grid-cols-2 gap-4 mb-4">
              <input
                type="date"
                className="border px-4 py-2 rounded-md"
                placeholder="Order Date"
                value={editOrder.orderDate}
                onChange={(e) => setEditOrder({ ...editOrder, orderDate: e.target.value })}
                required
              />
              <input
                type="date"
                className="border px-4 py-2 rounded-md"
                placeholder="Delivery Date"
                value={editOrder.deliveryDate}
                onChange={(e) => setEditOrder({ ...editOrder, deliveryDate: e.target.value })}
                required
              />
              <input
                type="number"
                className="border px-4 py-2 rounded-md"
                placeholder="Total Amount"
                value={editOrder.totalAmount}
                onChange={(e) => setEditOrder({ ...editOrder, totalAmount: parseFloat(e.target.value) })}
                required
              />
              <input
                type="text"
                className="border px-4 py-2 rounded-md"
                placeholder="Order Status"
                value={editOrder.orderStatus}
                onChange={(e) => setEditOrder({ ...editOrder, orderStatus: e.target.value })}
                required
              />
              <input
                type="text"
                className="border px-4 py-2 rounded-md"
                placeholder="Supplier Name"
                value={editOrder.supplierName}
                onChange={(e) => setEditOrder({ ...editOrder, supplierName: e.target.value })}
                required
              />
            </div>
            <div className="flex gap-4">
              <button className="bg-blue-500 text-white px-4 py-2 rounded-md">Update Order</button>
              <button
                type="button"
                className="bg-gray-400 text-white px-4 py-2 rounded-md"
                onClick={() => setEditOrder(null)}
              >
                Cancel
              </button>
            </div>
          </form>
        </div>
      )}
    </div>
  );
};

export default ProcurementDashboard;
