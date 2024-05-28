import React, { useState } from 'react';
import { Link, Route, Routes, useNavigate } from 'react-router-dom';
import ProductDetail from './ProductDetail';
import CartPage from './CartPage';
import ProductManagement from './components/ProductManagement';
import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

function App() {
  const [cartId, setCartId] = useState('');
  const navigate = useNavigate();

  const handleCartIdChange = (event) => {
    setCartId(event.target.value);
  };

  const handleViewCart = async () => {
    try {
      await axios.get(`${apiUrl}/api/carts/ensure/${cartId}`);
      navigate(`/cart/${cartId}`);
    } catch (error) {
      console.error("There was an error ensuring the cart exists!", error);
    }
  };

  return (
    <div className="container">
      <h1>Product Management</h1>
      <nav className="mb-4">
        <Link to="/" className="btn btn-primary mr-2">Home</Link>
        <div className="input-group">
          <input 
            type="number" 
            className="form-control" 
            placeholder="Cart ID" 
            value={cartId} 
            onChange={handleCartIdChange}
          />
          <button onClick={handleViewCart} className="btn btn-secondary">View Cart</button>
        </div>
      </nav>
      <Routes>
        <Route path="/" element={<ProductManagement />} />
        <Route path="/products/:id" element={<ProductDetail />} />
        <Route path="/cart/:id" element={<CartPage />} />
      </Routes>
    </div>
  );
}

export default App;