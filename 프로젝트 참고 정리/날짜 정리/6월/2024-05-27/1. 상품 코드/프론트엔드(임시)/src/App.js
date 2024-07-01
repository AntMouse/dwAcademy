// import logo from './logo.svg';
// import './App.css';

import React, { useState, useEffect } from 'react';
import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

function App() {
  const [products, setProducts] = useState([]);
  const [form, setForm] = useState({ productType: '', productName: '', price: '', explanation: '' });
  const [editingProduct, setEditingProduct] = useState(null);

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    try {
      const response = await axios.get(`${apiUrl}/api/products`);
      setProducts(response.data);
    } catch (error) {
      console.error("There was an error fetching the products!", error);
    }
  };

  const createProduct = async () => {
    try {
      await axios.post(`${apiUrl}/api/products`, form);
      setForm({ productType: '', productName: '', price: '', explanation: '' });
      fetchProducts();
    } catch (error) {
      console.error("There was an error creating the product!", error);
    }
  };

  const updateProduct = async () => {
    try {
      await axios.put(`${apiUrl}/api/products/${editingProduct.id}`, form);
      setForm({ productType: '', productName: '', price: '', explanation: '' });
      setEditingProduct(null);
      fetchProducts();
    } catch (error) {
      console.error("There was an error updating the product!", error);
    }
  };

  const deleteProduct = async (id) => {
    try {
      await axios.delete(`${apiUrl}/api/products/${id}`);
      fetchProducts();
    } catch (error) {
      console.error("There was an error deleting the product!", error);
    }
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    if (editingProduct) {
      updateProduct();
    } else {
      createProduct();
    }
  };

  const handleEdit = (product) => {
    setEditingProduct(product);
    setForm(product);
  };

  const handleChange = (event) => {
    setForm({ ...form, [event.target.name]: event.target.value });
  };

  return (
    <div className="App">
      <h1>Product Management</h1>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="productType"
          placeholder="Product Type"
          value={form.productType}
          onChange={handleChange}
        />
        <input
          type="text"
          name="productName"
          placeholder="Product Name"
          value={form.productName}
          onChange={handleChange}
        />
        <input
          type="number"
          name="price"
          placeholder="Price"
          value={form.price}
          onChange={handleChange}
        />
        <textarea
          name="explanation"
          placeholder="Explanation"
          value={form.explanation}
          onChange={handleChange}
        />
        <button type="submit">{editingProduct ? 'Update' : 'Create'}</button>
      </form>
      <ul>
        {products.map(product => (
          <li key={product.id}>
            {product.productName} - {product.productType} - {product.price} - {product.explanation}
            <button onClick={() => handleEdit(product)}>Edit</button>
            <button onClick={() => deleteProduct(product.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;