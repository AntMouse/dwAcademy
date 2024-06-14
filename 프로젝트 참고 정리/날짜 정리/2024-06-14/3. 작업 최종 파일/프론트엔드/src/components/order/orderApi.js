import axios from 'axios';
import { showError, errorMessages } from './messages';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

export const fetchOrders = async () => {
  try {
    const response = await axios.get(`${apiUrl}/api/orders`);
    return response.data;
  } catch (error) {
    showError(errorMessages.fetchOrders);
    throw error;
  }
};

export const fetchOrderById = async (id) => {
  try {
    const response = await axios.get(`${apiUrl}/api/orders/${id}`);
    return response.data;
  } catch (error) {
    showError(errorMessages.fetchOrder);
    throw error;
  }
};

export const updateOrder = async (id, orderData) => {
  try {
    await axios.put(`${apiUrl}/api/orders/${id}`, orderData);
  } catch (error) {
    showError(errorMessages.updateOrder);
    throw error;
  }
};
