// src/components/review/Review.js

import React, { useState } from 'react';
import ReviewForm from './ReviewForm';

const Review = ({ review, onDelete, currentMember }) => {
  const [isEditing, setIsEditing] = useState(false);

  const toggleEdit = () => {
    setIsEditing(!isEditing);
  };

  const isOwner = currentMember && currentMember.id === review.memberId;

  return (
    <div>
      {isEditing ? (
        <ReviewForm review={review} onEdit={() => setIsEditing(false)} currentMember={currentMember} />
      ) : (
        <div>
          <p>{review.content}</p>
          <p>Rating: {review.rating}</p>
          {isOwner && (
            <>
              <button onClick={toggleEdit}>Edit</button>
              <button onClick={() => onDelete(review.id)}>Delete</button>
            </>
          )}
        </div>
      )}
    </div>
  );
};

export default Review;
