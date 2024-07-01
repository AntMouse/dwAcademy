// src/components/productreview/productReviewApi.js
import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api',
});

export const getProducts = () => api.get('/products');
export const getReviewsByProductId = (productId) => api.get(`/reviews/product/${productId}`);
export const createReview = (review) => api.post('/reviews', review);
export const updateReview = (id, review) => api.put(`/reviews/${id}`, review);
export const deleteReview = (id) => api.delete(`/reviews/${id}`);
