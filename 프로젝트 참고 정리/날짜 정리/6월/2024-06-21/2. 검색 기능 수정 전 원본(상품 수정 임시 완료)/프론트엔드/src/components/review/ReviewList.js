// src/components/review/ReviewList.js

import React, { useState, useEffect, useCallback } from 'react';
import { getReviewsByProductId, deleteReview } from '../productreview/productReviewApi';
import Review from './Review';

const ReviewList = ({ productId, currentMember }) => {
  const [reviews, setReviews] = useState([]);

  const fetchReviews = useCallback(async () => {
    console.log("Fetching reviews for product ID:", productId); // 디버깅용 로그 추가
    try {
      const response = await getReviewsByProductId(productId);
      console.log("Server response:", response.data); // 디버깅용 로그 추가
      if (response.data && Array.isArray(response.data)) {
        setReviews(response.data);
      } else {
        console.error("Unexpected response data", response.data);
      }
    } catch (error) {
      console.error("Failed to fetch reviews", error);
    }
  }, [productId]);

  useEffect(() => {
    fetchReviews();
  }, [fetchReviews]);

  const handleDelete = async (id) => {
    const token = localStorage.getItem('token'); // JWT 토큰 가져오기
    await deleteReview(id, token);
    fetchReviews();
  };

  return (
    <div>
      <h3>Reviews</h3>
      {reviews.map((review) => (
        <Review key={review.id} review={review} onDelete={handleDelete} currentMember={currentMember} />
      ))}
    </div>
  );
};

export default ReviewList;
