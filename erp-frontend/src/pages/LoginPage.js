import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const LoginPage = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    console.log("Attempting login with:", { username, password });
    try {
      const response = await axios.post("http://localhost:8080/auth/login", {
        username,
        password,
      });
      console.log("Response from backend:", response.data);
      const { token, role } = response.data;

      sessionStorage.setItem("token", token);
      sessionStorage.setItem("role", role);

      switch (role) {
        case "Store Manager":
          navigate("/store-manager");
          break;
        case "HR Manager":
          navigate("/hr-manager");
          break;
        case "Finance Manager":
          navigate("/finance");
          break;
        case "Procurement Team":
          navigate("/procurement");
          break;
        case "Warehouse Manager":
          navigate("/warehouse");
          break;
        case "Executive":
          navigate("/executive");
          break;
        case "Admin":
          navigate("/admin");
          break;
        default:
          setError("No dashboard available for this role.");
      }
    } catch (err) {
      console.error("Error:", err);
      setError("Invalid username or password.");
    }
  };

  return (
    <div className="flex justify-center h-screen bg-white items-center flex-col">
      {/* Poké Ball Container */}
      <div className="">
          <img className="" src='https://th.bing.com/th/id/OIP.1fKMsrzLauGls9Xs6cfoVgHaEK?rs=1&pid=ImgDetMain'></img>
          <h1 className="text-5xl font-bold text-gray-800 font-serif mb-5">ERP</h1>
          <p className="text-2xl mt-3 text-center text-gray-600">Login to Your Account</p>
      </div>
        
      <div className="flex justify-center items-center relative w-[550px] h-[550px] bg-white rounded-full shadow-xl bg-[url('https://www.freevector.com/uploads/vector/preview/15943/FreeVector-Poke-Ball.jpg')] bg-cover bg-center">
        {/* Red Top Half */}
        {/* <div className="absolute top-0 left-0 w-full h-1/2 bg-red-600 rounded-t-full"></div> */}
        {/* Black Stripe */}
        {/* <div className="absolute top-1/2 left-0 w-full h-8 bg-black transform -translate-y-1/2"></div> */}
        {/* Center Button */}
        {/* <div className="absolute top-1/2 left-1/2 w-20 h-20 bg-white border-4 border-black rounded-full transform -translate-x-1/2 -translate-y-1/2 flex items-center justify-center"> */}
          {/* <div className="w-12 h-12 bg-gray-200 border-4 border-black rounded-full"></div> */}
        {/* </div> */}

        {/* Login Form */}
        <div className="absolute w-2/3 text-center flex flex-col">
          
          {error && (
            <p className="text-sm text-red-500 bg-red-100 p-2 rounded mb-4">
              {error}
            </p>
          )}
          <form onSubmit={handleLogin} className="space-y-4">
            <input
              type="text"
              placeholder="Username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
              className="w-full p-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
            <input
              type="password"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
              className="w-full p-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
            <button
              type="submit"
              className="w-full p-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600 transition duration-300"
            >
              Login
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default LoginPage;
