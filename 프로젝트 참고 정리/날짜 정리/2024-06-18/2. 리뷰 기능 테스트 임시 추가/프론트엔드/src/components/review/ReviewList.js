// src/components/review/ReviewList.js
import React, { useState, useEffect } from 'react';
import { getReviewsByProductId, deleteReview } from '../productreview/productReviewApi';
import Review from './Review';

const ReviewList = ({ productId }) => {
  const [reviews, setReviews] = useState([]);

  useEffect(() => {
    fetchReviews();
  }, []);

  const fetchReviews = async () => {
    const response = await getReviewsByProductId(productId);
    setReviews(response.data);
  };

  const handleDelete = async (id) => {
    await deleteReview(id);
    fetchReviews();
  };

  return (
    <div>
      <h3>Reviews</h3>
      {reviews.map((review) => (
        <Review key={review.id} review={review} onDelete={handleDelete} />
      ))}
    </div>
  );
};

export default ReviewList;
