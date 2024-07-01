// src/components/productreview/productReviewApi.js

import axios from 'axios';

// axios 인스턴스 생성
const api = axios.create({
  baseURL: 'http://localhost:8080/api',
});

// API 함수들
export const getProducts = () => api.get('/products');
export const getReviewsByProductId = (productId) => api.get(`/reviews/product/${productId}`);
export const createReview = (review, token) => api.post('/reviews', review, {
  headers: { Authorization: `Bearer ${token}` }
});
export const updateReview = (id, review, token) => api.put(`/reviews/${id}`, review, {
  headers: { Authorization: `Bearer ${token}` }
});
export const deleteReview = (id, token) => api.delete(`/reviews/${id}`, {
  headers: { Authorization: `Bearer ${token}` }
});
