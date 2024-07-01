// src/components/review/ReviewForm.js
import React, { useState } from 'react';
import { createReview, updateReview } from '../productreview/productReviewApi';

const ReviewForm = ({ productId, review, onEdit }) => {
  const [content, setContent] = useState(review ? review.content : '');
  const [rating, setRating] = useState(review ? review.rating : 0);

  const handleSubmit = async (event) => {
    event.preventDefault();
    if (review) {
      await updateReview(review.id, { ...review, content, rating });
      if (onEdit) onEdit();
    } else {
      await createReview({ productId, content, rating, memberId: 1 }); // memberId는 예시로 1을 사용
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
