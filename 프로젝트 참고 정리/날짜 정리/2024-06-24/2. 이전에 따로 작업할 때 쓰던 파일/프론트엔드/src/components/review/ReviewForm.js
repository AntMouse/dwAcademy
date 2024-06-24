// src/components/review/ReviewForm.js

import React, { useState } from 'react';
import { createReview, updateReview } from '../productreview/productReviewApi';

const ReviewForm = ({ productId, review, onEdit, currentMember }) => {
  const [content, setContent] = useState(review ? review.content : '');
  const [rating, setRating] = useState(review ? review.rating : 0);

  const handleSubmit = async (event) => {
    event.preventDefault();
    const token = localStorage.getItem('token');

    if (!currentMember) {
      alert("Please log in to submit a review.");
      return;
    }

    try {
      if (review) {
        await updateReview(review.id, { ...review, content, rating }, token);
        if (onEdit) onEdit();
      } else {
        await createReview({ productId, content, rating, memberId: currentMember.id }, token);
      }
      console.log("Review submitted successfully");
    } catch (error) {
      console.error("Failed to submit review", error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Content</label>
        <textarea value={content} onChange={(e) => setContent(e.target.value)} />
      </div>
      <div>
        <label>Rating</label>
        <input type="number" value={rating} onChange={(e) => setRating(Number(e.target.value))} />
      </div>
      <button type="submit">{review ? 'Update' : 'Create'}</button>
    </form>
  );
};

export default ReviewForm;
