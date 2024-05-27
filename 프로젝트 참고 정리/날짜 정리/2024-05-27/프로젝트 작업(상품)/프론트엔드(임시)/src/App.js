// import logo from './logo.svg';
// import './App.css';

import React, { useState, useEffect } from 'react';
import axios from 'axios';

function App() {
  const [products, setProducts] = useState([]);
  const [form, setForm] = useState({ productType: '', productName: '', price: '', explanation: '' });
  const [editingProduct, setEditingProduct] = useState(null);

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    const response = await axios.get('http://localhost:8080/api/products');
    setProducts(response.data);
  };

  const createProduct = async () => {
    await axios.post('http://localhost:8080/api/products', form);
    setForm({ productType: '', productName: '', price: '', explanation: '' });
    fetchProducts();
  };

  const updateProduct = async () => {
    await axios.put(`http://localhost:8080/api/products/${editingProduct.id}`, form);
    setForm({ productType: '', productName: '', price: '', explanation: '' });
    setEditingProduct(null);
    fetchProducts();
  };

  const deleteProduct = async (id) => {
    await axios.delete(`http://localhost:8080/api/products/${id}`);
    fetchProducts();
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
