import axios from 'axios';
import { showError, errorMessages } from './messages';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

export const fetchOrders = async () => {
  try {
    const response = await axios.get(`${apiUrl}/api/orders`);
    return response.data;  // orders 데이터만 반환
  } catch (error) {
    showError(errorMessages.fetchOrders);
    throw error;
  }
};

export const fetchOrderStatuses = (orders) => {
  const statuses = orders.map(order => order.status);
  return [...new Set(statuses)]; // 중복 제거, 배열 반환
};

export const handleStatusChange = (event, setSelectedStatus, setCurrentPage) => {
  setSelectedStatus(event.target.value);
  setCurrentPage(1);
};

export const handleSortChange = (criteria, setSortCriteria) => {
  setSortCriteria(criteria);
};

export const handleOrdersPerPageChange = (event, setOrdersPerPage, setCurrentPage) => {
  setOrdersPerPage(Number(event.target.value));
  setCurrentPage(1);
};
