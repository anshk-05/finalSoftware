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
            const sortedEmployees = response.data.sort(
                (a, b) => a.employeeId - b.employeeId
            );
            setEmployees(sortedEmployees);
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
                password: editEmployee.password || "unchangedpassword",
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
        <div className="p-6 bg-gray-100 min-h-screen">
            <h1 className="text-3xl font-bold mb-6">HR Manager Dashboard</h1>
            {error && <p className="text-red-500 mb-4">{error}</p>}

            {/* Employee List */}
            <h2 className="text-2xl font-semibold mb-4">Employee List</h2>
            <table className="w-full bg-white shadow-md rounded-lg overflow-hidden">
                <thead className="bg-gray-200">
                    <tr>
                        <th className="px-4 py-2 text-left">ID</th>
                        <th className="px-4 py-2 text-left">Name</th>
                        <th className="px-4 py-2 text-left">Salary</th>
                        <th className="px-4 py-2 text-left">Hire Date</th>
                        <th className="px-4 py-2 text-left">Department</th>
                        <th className="px-4 py-2 text-left">Store</th>
                        <th className="px-4 py-2 text-left">Role</th>
                        <th className="px-4 py-2 text-left">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {employees.map((employee) => (
                        <tr key={employee.employeeId} className="border-b">
                            <td className="px-4 py-2">{employee.employeeId}</td>
                            <td className="px-4 py-2">{employee.name}</td>
                            <td className="px-4 py-2">{employee.salary}</td>
                            <td className="px-4 py-2">{employee.hireDate}</td>
                            <td className="px-4 py-2">{employee.departmentName}</td>
                            <td className="px-4 py-2">{employee.storeName}</td>
                            <td className="px-4 py-2">{employee.roleName}</td>
                            <td className="px-4 py-2 flex gap-2">
                                <button
                                    className="bg-blue-500 text-white px-3 py-1 rounded-md"
                                    onClick={() => setEditEmployee(employee)}
                                >
                                    Edit
                                </button>
                                <button
                                    className="bg-red-500 text-white px-3 py-1 rounded-md"
                                    onClick={() => handleDeleteEmployee(employee.employeeId)}
                                >
                                    Delete
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

            {/* Add Employee Form */}
            <h2 className="text-2xl font-semibold mt-8 mb-4">Add New Employee</h2>
            <form className="bg-white p-6 shadow-md rounded-lg" onSubmit={handleAddEmployee}>
                <div className="grid grid-cols-2 gap-4 mb-4">
                    <input
                        className="border rounded-lg px-4 py-2"
                        type="text"
                        placeholder="Name"
                        value={newEmployee.name}
                        onChange={(e) =>
                            setNewEmployee({ ...newEmployee, name: e.target.value })
                        }
                        required
                    />
                    <input
                        className="border rounded-lg px-4 py-2"
                        type="number"
                        placeholder="Salary"
                        value={newEmployee.salary}
                        onChange={(e) =>
                            setNewEmployee({ ...newEmployee, salary: e.target.value })
                        }
                        required
                    />
                    <input
                        className="border rounded-lg px-4 py-2"
                        type="date"
                        value={newEmployee.hireDate}
                        onChange={(e) =>
                            setNewEmployee({ ...newEmployee, hireDate: e.target.value })
                        }
                        required
                    />
                    <input
                        className="border rounded-lg px-4 py-2"
                        type="text"
                        placeholder="Password"
                        value={newEmployee.password}
                        onChange={(e) =>
                            setNewEmployee({ ...newEmployee, password: e.target.value })
                        }
                        required
                    />
                    <input
                        className="border rounded-lg px-4 py-2"
                        type="number"
                        placeholder="Department ID"
                        value={newEmployee.departmentId}
                        onChange={(e) =>
                            setNewEmployee({ ...newEmployee, departmentId: e.target.value })
                        }
                        required
                    />
                    <input
                        className="border rounded-lg px-4 py-2"
                        type="number"
                        placeholder="Store ID"
                        value={newEmployee.storeId}
                        onChange={(e) =>
                            setNewEmployee({ ...newEmployee, storeId: e.target.value })
                        }
                        required
                    />
                    <input
                        className="border rounded-lg px-4 py-2"
                        type="number"
                        placeholder="Role ID"
                        value={newEmployee.roleId}
                        onChange={(e) =>
                            setNewEmployee({ ...newEmployee, roleId: e.target.value })
                        }
                        required
                    />
                </div>
                <button
                    type="submit"
                    className="bg-green-500 text-white px-4 py-2 rounded-md"
                >
                    Add Employee
                </button>
            </form>

            {/* Edit Employee Form */}
            {editEmployee && (
                <div className="mt-8">
                    <h2 className="text-2xl font-semibold mb-4">Edit Employee</h2>
                    <form
                        className="bg-white p-6 shadow-md rounded-lg"
                        onSubmit={handleEditEmployee}
                    >
                        <div className="grid grid-cols-2 gap-4 mb-4">
                            <input
                                className="border rounded-lg px-4 py-2"
                                type="text"
                                placeholder="Name"
                                value={editEmployee.name || ""}
                                onChange={(e) =>
                                    setEditEmployee({ ...editEmployee, name: e.target.value })
                                }
                                required
                            />
                            <input
                                className="border rounded-lg px-4 py-2"
                                type="number"
                                placeholder="Salary"
                                value={editEmployee.salary || ""}
                                onChange={(e) =>
                                    setEditEmployee({ ...editEmployee, salary: e.target.value })
                                }
                                required
                            />
                            <input
                                className="border rounded-lg px-4 py-2"
                                type="date"
                                value={editEmployee.hireDate || ""}
                                onChange={(e) =>
                                    setEditEmployee({
                                        ...editEmployee,
                                        hireDate: e.target.value,
                                    })
                                }
                                required
                            />
                            <input
                                className="border rounded-lg px-4 py-2"
                                type="text"
                                placeholder="Password"
                                value={editEmployee.password || ""}
                                onChange={(e) =>
                                    setEditEmployee({
                                        ...editEmployee,
                                        password: e.target.value,
                                    })
                                }
                            />
                            <input
                                className="border rounded-lg px-4 py-2"
                                type="number"
                                placeholder="Department ID"
                                value={editEmployee.departmentId || ""}
                                onChange={(e) =>
                                    setEditEmployee({
                                        ...editEmployee,
                                        departmentId: e.target.value,
                                    })
                                }
                                required
                            />
                            <input
                                className="border rounded-lg px-4 py-2"
                                type="number"
                                placeholder="Store ID"
                                value={editEmployee.storeId || ""}
                                onChange={(e) =>
                                    setEditEmployee({
                                        ...editEmployee,
                                        storeId: e.target.value,
                                    })
                                }
                                required
                            />
                            <input
                                className="border rounded-lg px-4 py-2"
                                type="number"
                                placeholder="Role ID"
                                value={editEmployee.roleId || ""}
                                onChange={(e) =>
                                    setEditEmployee({
                                        ...editEmployee,
                                        roleId: e.target.value,
                                    })
                                }
                                required
                            />
                        </div>
                        <div className="flex gap-2">
                            <button
                                type="submit"
                                className="bg-blue-500 text-white px-4 py-2 rounded-md"
                            >
                                Update Employee
                            </button>
                            <button
                                type="button"
                                className="bg-gray-400 text-white px-4 py-2 rounded-md"
                                onClick={() => setEditEmployee(null)}
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

export default HRManagerDashboard;
