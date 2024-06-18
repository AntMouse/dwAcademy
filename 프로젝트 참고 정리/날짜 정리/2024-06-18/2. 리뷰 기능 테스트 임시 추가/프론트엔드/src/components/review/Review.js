// src/components/review/Review.js
import React, { useState } from 'react';
import ReviewForm from './ReviewForm';

const Review = ({ review, onDelete }) => {
  const [isEditing, setIsEditing] = useState(false);

  const toggleEdit = () => {
    setIsEditing(!isEditing);
  };

  return (
    <div>
      {isEditing ? (
        <ReviewForm review={review} onEdit={() => setIsEditing(false)} />
      ) : (
        <div>
          <p>{review.content}</p>
          <p>Rating: {review.rating}</p>
          <button onClick={toggleEdit}>Edit</button>
          <button onClick={() => onDelete(review.id)}>Delete</button>
        </div>
      )}
    </div>
  );
};

export default Review;
