// src/components/productreview/ProductReviewList.js
import React, { useState, useEffect } from 'react';
import { getProducts } from './productReviewApi';
import ProductReview from './ProductReview';

const ProductReviewList = () => {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    const response = await getProducts();
    setProducts(response.data);
  };

  return (
    <div>
      <h1>Product List</h1>
      {products.map((product) => (
        <ProductReview key={product.id} product={product} />
      ))}
    </div>
  );
};

export default ProductReviewList;
