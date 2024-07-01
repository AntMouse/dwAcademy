// orderApi.js

import { getData, postData, putData, deleteData } from '../../utils/apiUtils';
import { orderErrorMessages } from '../../utils/messagesUtils';

// 주문 목록을 가져오는 함수
export const getOrders = async () => {
  return await getData('/api/orders', orderErrorMessages.getOrders);
};

// 주문 ID로 주문을 가져오는 함수
export const getOrderById = async (id) => {
  return await getData(`/api/orders/${id}`, orderErrorMessages.getOrder);
};

// 주문 상태(배송준비/완료) 목록을 가져오는 함수
export const getOrderStatuses = async () => {
  return await getData('/api/order-statuses', orderErrorMessages.getOrderStatuses);
};

// 주문을 업데이트하는 함수
export const putOrder = async (id, orderData) => {
  return await putData(`/api/orders/${id}`, orderData, orderErrorMessages.putOrder);
};

// 주문 상태(배송준비/완료) 업데이트 함수
export const putOrderStatus = async (orderIds, status) => {
  return await putData('/api/orders/status', { orderIds, status }, orderErrorMessages.putOrderStatus);
};

// 주문 삭제 함수
export const deleteOrders = async (orderIds) => {
  return await deleteData('/api/orders', orderIds, orderErrorMessages.deleteOrder);
};
