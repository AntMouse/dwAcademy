import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ProductForm from './ProductForm';
import ProductList from './ProductList';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

const requiredFields = ['productType', 'productName', 'price'];

function ProductManagement({ currentCart }) {
  const [products, setProducts] = useState([]);
  const [form, setForm] = useState({ productType: '', productName: '', price: '', explanation: '' });
  const [editingProduct, setEditingProduct] = useState(null);
  const [errors, setErrors] = useState({});

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const { data } = await axios.get(`${apiUrl}/api/products`);
        setProducts(data);
      } catch (error) {
        console.error("There was an error fetching the products!", error);
      }
    };

    fetchProducts();
  }, []);

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

  const handleSubmit = async (event) => {
    event.preventDefault();
    if (!validateForm()) return;

    try {
      if (editingProduct) {
        await axios.put(`${apiUrl}/api/products/${editingProduct.id}`, form);
        setEditingProduct(null);
      } else {
        await axios.post(`${apiUrl}/api/products`, form);
      }
      setForm({ productType: '', productName: '', price: '', explanation: '' });
      fetchProducts();
    } catch (error) {
      console.error("There was an error submitting the product!", error.response || error.message);
    }
  };

  const handleEdit = (product) => {
    setEditingProduct(product);
    setForm(product);
  };

  const handleChange = (event) => {
    setForm({ ...form, [event.target.name]: event.target.value });
  };

  const fetchProducts = async () => {
    try {
      const { data } = await axios.get(`${apiUrl}/api/products`);
      setProducts(data);
    } catch (error) {
      console.error("There was an error fetching the products!", error);
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
        currentCart={currentCart}
      />
    </div>
  );
}

export default ProductManagement;
