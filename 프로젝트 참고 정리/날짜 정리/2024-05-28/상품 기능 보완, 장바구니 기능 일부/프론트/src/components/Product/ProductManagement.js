import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ProductForm from './ProductForm';
import ProductList from './ProductList';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

const requiredFields = ['productType', 'productName', 'price'];

function ProductManagement() {
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
      const response = await axios.post(`${apiUrl}/api/products`, form);
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
    <div>
      <ProductForm 
        form={form} 
        handleChange={handleChange} 
        handleSubmit={handleSubmit} 
        errors={errors} 
        editingProduct={editingProduct} 
      />
      <ProductList 
        products={products} 
        handleEdit={handleEdit} 
        deleteProduct={deleteProduct} 
      />
    </div>
  );
}

export default ProductManagement;
