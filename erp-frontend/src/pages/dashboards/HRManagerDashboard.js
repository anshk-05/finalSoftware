import React, { useState, useEffect } from "react";
import axios from "axios";

const HRManagerDashboard = () => {
    const [employees, setEmployees] = useState([]);
    const [newEmployee, setNewEmployee] = useState({
        name: "",
        salary: "",
        hireDate: "",
        password: "",
        departmentId: "",
        storeId: "",
        roleId: "",
    });
    const [editEmployee, setEditEmployee] = useState(null); // For editing an employee
    const [error, setError] = useState("");

    // Fetch all employees on component mount
    useEffect(() => {
        fetchEmployees();
    }, []);

    const fetchEmployees = async () => {
        try {
            const response = await axios.get("http://localhost:8080/employees");
            const sortedEmployees = response.data.sort((a, b) => a.employeeId - b.employeeId);
            setEmployees(response.data);
        } catch (err) {
            setError("Failed to fetch employees.");
        }
    };

    const handleAddEmployee = async (e) => {
        e.preventDefault();
        try {
            await axios.post("http://localhost:8080/employees", {
                name: newEmployee.name,
                salary: newEmployee.salary,
                hireDate: newEmployee.hireDate,
                password: newEmployee.password,
                department: { departmentId: newEmployee.departmentId },
                store: { storeId: newEmployee.storeId },
                role: { roleId: newEmployee.roleId },
            });
            setNewEmployee({
                name: "",
                salary: "",
                hireDate: "",
                password: "",
                departmentId: "",
                storeId: "",
                roleId: "",
            });
            fetchEmployees(); // Refresh the list
        } catch (err) {
            setError("Failed to add employee.");
        }
    };

    const handleEditEmployee = async (e) => {
        e.preventDefault();
        try {
            await axios.put(`http://localhost:8080/employees/${editEmployee.employeeId}`, {
                name: editEmployee.name,
                salary: editEmployee.salary,
                hireDate: editEmployee.hireDate,
                password: editEmployee.password || "unchangedpassword", // Use existing password if unchanged
                department: { departmentId: editEmployee.departmentId },
                store: { storeId: editEmployee.storeId },
                role: { roleId: editEmployee.roleId },
            });
            setEditEmployee(null); // Close the edit form
            fetchEmployees(); // Refresh the employee list
        } catch (err) {
            setError("Failed to update employee.");
        }
    };
    

    const handleDeleteEmployee = async (id) => {
        if (window.confirm("Are you sure you want to delete this employee?")) {
            try {
                await axios.delete(`http://localhost:8080/employees/${id}`);
                fetchEmployees(); // Refresh the list
            } catch (err) {
                setError("Failed to delete employee.");
            }
        }
    };

    return (
        <div style={{ padding: "20px" }}>
            <h1>HR Manager Dashboard</h1>
            {error && <p style={{ color: "red" }}>{error}</p>}

            {/* Employee List */}
            <h2>Employee List</h2>
            <table border="1" style={{ width: "100%", marginBottom: "20px" }}>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Salary</th>
                        <th>Hire Date</th>
                        <th>Department</th>
                        <th>Store</th>
                        <th>Role</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {employees.map((employee) => (
                        <tr key={employee.employeeId}>
                            <td>{employee.employeeId}</td>
                            <td>{employee.name}</td>
                            <td>{employee.salary}</td>
                            <td>{employee.hireDate}</td>
                            <td>{employee.departmentName}</td>
                            <td>{employee.storeName}</td>
                            <td>{employee.roleName}</td>
                            <td>
                                <button onClick={() => setEditEmployee(employee)}>Edit</button>
                                <button onClick={() => handleDeleteEmployee(employee.employeeId)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

            {/* Add Employee Form */}
            <h2>Add New Employee</h2>
            <form onSubmit={handleAddEmployee}>
                <input
                    type="text"
                    placeholder="Name"
                    value={newEmployee.name}
                    onChange={(e) => setNewEmployee({ ...newEmployee, name: e.target.value })}
                    required
                />
                <input
                    type="number"
                    placeholder="Salary"
                    value={newEmployee.salary}
                    onChange={(e) => setNewEmployee({ ...newEmployee, salary: e.target.value })}
                    required
                />
                <input
                    type="date"
                    placeholder="Hire Date"
                    value={newEmployee.hireDate}
                    onChange={(e) => setNewEmployee({ ...newEmployee, hireDate: e.target.value })}
                    required
                />
                <input
                    type="text"
                    placeholder="Password"
                    value={newEmployee.password}
                    onChange={(e) => setNewEmployee({ ...newEmployee, password: e.target.value })}
                    required
                />
                <input
                    type="number"
                    placeholder="Department ID"
                    value={newEmployee.departmentId}
                    onChange={(e) => setNewEmployee({ ...newEmployee, departmentId: e.target.value })}
                    required
                />
                <input
                    type="number"
                    placeholder="Store ID"
                    value={newEmployee.storeId}
                    onChange={(e) => setNewEmployee({ ...newEmployee, storeId: e.target.value })}
                    required
                />
                <input
                    type="number"
                    placeholder="Role ID"
                    value={newEmployee.roleId}
                    onChange={(e) => setNewEmployee({ ...newEmployee, roleId: e.target.value })}
                    required
                />
                <button type="submit">Add Employee</button>
            </form>

            {/* Edit Employee Form */}
            {editEmployee && (
                <div>
                    <h2>Edit Employee</h2>
                    <form onSubmit={handleEditEmployee}>
                        <input
                            type="text"
                            placeholder="Name"
                            value={editEmployee.name || ""}
                            onChange={(e) => setEditEmployee({ ...editEmployee, name: e.target.value })}
                            required
                        />
                        <input
                            type="number"
                            placeholder="Salary"
                            value={editEmployee.salary || ""}
                            onChange={(e) => setEditEmployee({ ...editEmployee, salary: e.target.value })}
                            required
                        />
                        <input
                            type="date"
                            placeholder="Hire Date"
                            value={editEmployee.hireDate || ""}
                            onChange={(e) => setEditEmployee({ ...editEmployee, hireDate: e.target.value })}
                            required
                        />
                        <input
                            type="text"
                            placeholder="Password"
                            value={editEmployee.password || ""}
                            onChange={(e) => setEditEmployee({ ...editEmployee, password: e.target.value })}
                        />
                        <input
                            type="number"
                            placeholder="Department ID"
                            value={editEmployee.departmentId || ""}
                            onChange={(e) => setEditEmployee({ ...editEmployee, departmentId: e.target.value })}
                            required
                        />
                        <input
                            type="number"
                            placeholder="Store ID"
                            value={editEmployee.storeId || ""}
                            onChange={(e) => setEditEmployee({ ...editEmployee, storeId: e.target.value })}
                            required
                        />
                        <input
                            type="number"
                            placeholder="Role ID"
                            value={editEmployee.roleId || ""}
                            onChange={(e) => setEditEmployee({ ...editEmployee, roleId: e.target.value })}
                            required
                        />
                        <button type="submit">Update Employee</button>
                        <button type="button" onClick={() => setEditEmployee(null)}>Cancel</button>
                    </form>

                </div>
            )}
        </div>
    );
};

export default HRManagerDashboard;
