// src/components/productreview/ProductReview.js
import React, { useState } from 'react';
import ReviewList from '../review/ReviewList';
import ReviewForm from '../review/ReviewForm';

const ProductReview = ({ product }) => {
  const [showReviews, setShowReviews] = useState(false);

  const toggleReviews = () => {
    setShowReviews(!showReviews);
  };

  return (
    <div>
      <h2>{product.productName}</h2>
      <p>{product.explanation}</p>
      <button onClick={toggleReviews}>
        {showReviews ? 'Hide Reviews' : 'Show Reviews'}
      </button>
      {showReviews && (
        <div>
          <ReviewList productId={product.id} />
          <ReviewForm productId={product.id} />
        </div>
      )}
    </div>
  );
};

export default ProductReview;
