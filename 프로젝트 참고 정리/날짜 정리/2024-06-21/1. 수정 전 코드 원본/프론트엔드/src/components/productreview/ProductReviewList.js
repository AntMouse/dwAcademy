// src/components/productreview/ProductReviewList.js

import React, { useState, useEffect } from 'react';
import { getProducts } from './productReviewApi';
import ProductReview from './ProductReview';
import useAuth from '../../components/productreview/useAuth'; // 올바른 경로로 수정

const ProductReviewList = () => {
  const [products, setProducts] = useState([]);
  const { currentMember } = useAuth();

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
        <ProductReview key={product.id} product={product} currentMember={currentMember} />
      ))}
    </div>
  );
};

export default ProductReviewList;
