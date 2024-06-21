// src/components/productreview/ProductReview.js

import React, { useState } from 'react';
import ReviewList from '../review/ReviewList';
import ReviewForm from '../review/ReviewForm';

const ProductReview = ({ product, currentMember }) => {
  const [showReviews, setShowReviews] = useState(false);

  // 리뷰 표시 토글
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
          <ReviewList productId={product.id} currentMember={currentMember} />
          {currentMember && <ReviewForm productId={product.id} currentMember={currentMember} />}
        </div>
      )}
    </div>
  );
};

export default ProductReview;
