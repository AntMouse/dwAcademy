import React, { useState } from 'react';
import { Link, Route, Routes, useNavigate } from 'react-router-dom';
import ProductDetail from './components/Product/ProductDetail';
import CartPage from './components/Cart/CartPage';
import CartListPage from './components/Cart/CartListPage';
import ProductManagement from './components/Product/ProductManagement';
import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

function App() {
  const [cartName, setCartName] = useState('');
  const [viewCartName, setViewCartName] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();

  const handleCartCreate = async () => {
    try {
      const response = await axios.post(`${apiUrl}/api/carts`, null, {
        params: {
          name: cartName
        }
      });
      if (response.status === 200) {
        setErrorMessage('');
        navigate(`/cart/${response.data.id}`);
      } else {
        setErrorMessage('Failed to create cart.');
      }
    } catch (error) {
      setErrorMessage('There was an error creating the cart.');
    }
  };

  const handleViewCartByName = async () => {
    try {
      const response = await axios.get(`${apiUrl}/api/carts/name/${viewCartName}`);
      if (response.status === 200) {
        setErrorMessage('');
        navigate(`/cart/${response.data.id}`);
      } else {
        setErrorMessage('Cart not found.');
      }
    } catch (error) {
      setErrorMessage('Cart not found.');
    }
  };

  return (
    <div className="container">
      <h1>Product Management</h1>
      <nav className="mb-4">
        <Link to="/" className="btn btn-primary mr-2">Home</Link>
        <Link to="/cart-list" className="btn btn-secondary">Cart List</Link>
        <input
          type="text"
          placeholder="Cart Name"
          value={cartName}
          onChange={e => setCartName(e.target.value)}
          className="form-control d-inline w-auto ml-2"
        />
        <button onClick={handleCartCreate} className="btn btn-secondary ml-2">Create Cart</button>
        <input
          type="text"
          placeholder="View Cart Name"
          value={viewCartName}
          onChange={e => setViewCartName(e.target.value)}
          className="form-control d-inline w-auto ml-2"
        />
        <button onClick={handleViewCartByName} className="btn btn-secondary ml-2">View Cart</button>
      </nav>
      {errorMessage && <div className="text-danger">{errorMessage}</div>}
      <Routes>
        <Route path="/" element={<ProductManagement />} />
        <Route path="/products/:id" element={<ProductDetail />} />
        <Route path="/cart/:id" element={<CartPage />} />
        <Route path="/cart-list" element={<CartListPage />} />
      </Routes>
    </div>
  );
}

export default App;
