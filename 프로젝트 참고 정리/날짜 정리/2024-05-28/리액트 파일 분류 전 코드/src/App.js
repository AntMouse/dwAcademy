import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link, Route, Routes } from 'react-router-dom';
import ProductDetail from './ProductDetail';
import CartPage from './CartPage';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

// 필수 입력 항목을 설정
const requiredFields = ['productType', 'productName', 'price'];

function App() {
  const [products, setProducts] = useState([]);
  const [form, setForm] = useState({ productType: '', productName: '', price: '', explanation: '' });
  const [editingProduct, setEditingProduct] = useState(null);
  const [errors, setErrors] = useState({});

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

  const validateForm = () => {
    const newErrors = {};
    requiredFields.forEach(field => {
      if (!form[field]) {
        newErrors[field] = `${field} is required`;
      }
    });
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const createProduct = async () => {
    if (!validateForm()) return;
    try {
      console.log("Sending create request with data:", form);
      const response = await axios.post(`${apiUrl}/api/products`, form);
      console.log("Product created successfully:", response.data);
      setForm({ productType: '', productName: '', price: '', explanation: '' });
      fetchProducts();
    } catch (error) {
      console.error("There was an error creating the product!", error.response || error.message);
    }
  };

  const updateProduct = async () => {
    if (!validateForm()) return;
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
    <div className="container">
      <h1>Product Management</h1>
      <nav className="mb-4">
        <Link to="/" className="btn btn-primary mr-2">Home</Link>
        <Link to="/cart" className="btn btn-secondary">View Cart</Link>
      </nav>
      <form onSubmit={handleSubmit} className="mb-4">
        <div className="mb-3">
          <input
            type="text"
            name="productType"
            className="form-control"
            placeholder="Product Type"
            value={form.productType}
            onChange={handleChange}
          />
          {errors.productType && <div className="text-danger">{errors.productType}</div>}
        </div>
        <div className="mb-3">
          <input
            type="text"
            name="productName"
            className="form-control"
            placeholder="Product Name"
            value={form.productName}
            onChange={handleChange}
          />
          {errors.productName && <div className="text-danger">{errors.productName}</div>}
        </div>
        <div className="mb-3">
          <input
            type="number"
            name="price"
            className="form-control"
            placeholder="Price"
            value={form.price}
            onChange={handleChange}
          />
          {errors.price && <div className="text-danger">{errors.price}</div>}
        </div>
        <div className="mb-3">
          <textarea
            name="explanation"
            className="form-control"
            placeholder="Explanation"
            value={form.explanation}
            onChange={handleChange}
          />
        </div>
        <button type="submit" className="btn btn-primary">
          {editingProduct ? 'Update' : 'Create'}
        </button>
      </form>
      <div className="row">
        {products.map(product => (
          <div className="col-md-4" key={product.id}>
            <div className="card mb-4">
              <div className="card-body">
                <h5 className="card-title">{product.productName}</h5>
                <h6 className="card-subtitle mb-2 text-muted">{product.productType}</h6>
                <p className="card-text">{product.explanation}</p>
                <p className="card-text">${product.price}</p>
                <Link to={`/products/${product.id}`} className="card-link">View Details</Link>
                <button onClick={() => handleEdit(product)} className="btn btn-secondary btn-sm">Edit</button>
                <button onClick={() => deleteProduct(product.id)} className="btn btn-danger btn-sm">Delete</button>
              </div>
            </div>
          </div>
        ))}
      </div>
      <Routes>
        <Route path="/products/:id" element={<ProductDetail />} />
        <Route path="/cart" element={<CartPage />} />
      </Routes>
    </div>
  );
}

export default App;