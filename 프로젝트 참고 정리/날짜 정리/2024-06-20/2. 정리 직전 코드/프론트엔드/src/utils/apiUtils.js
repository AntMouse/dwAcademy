// apiUtils.js

import axios from 'axios';
import { showError } from './messagesUtils';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

export const getData = async (endpoint, errorMessage) => {
  try {
    const response = await axios.get(`${apiUrl}${endpoint}`);
    return response.data;
  } catch (error) {
    showError(errorMessage);
    throw error;
  }
};

export const postData = async (endpoint, data, errorMessage) => {
  try {
    const response = await axios.post(`${apiUrl}${endpoint}`, data);
    return response.data;
  } catch (error) {
    showError(errorMessage);
    throw error;
  }
};

export const putData = async (endpoint, data, errorMessage) => {
  try {
    const response = await axios.put(`${apiUrl}${endpoint}`, data);
    return response.data;
  } catch (error) {
    showError(errorMessage);
    throw error;
  }
};

export const deleteData = async (endpoint, data, errorMessage) => {
  try {
    const response = await axios.delete(`${apiUrl}${endpoint}`, { data });
    return response.data;
  } catch (error) {
    showError(errorMessage);
    throw error;
  }
};
