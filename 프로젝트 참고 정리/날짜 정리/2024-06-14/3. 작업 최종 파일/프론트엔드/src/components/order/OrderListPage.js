import React, { useState, useEffect } from 'react';
import './OrderListPage.css';
import { fetchOrders, fetchOrderStatuses } from './orderHandlers';
import { showError, errorMessages } from './messages';
import OrderFilterControls from './OrderFilterControls';
import OrderSortControls from './OrderSortControls';
import OrderPagination from './OrderPagination';
import OrderList from './OrderList';
import { useNavigate } from 'react-router-dom';
import { filteredOrders, getCurrentOrders } from './orderUtils'; // 유틸리티 함수 import
import { handleStatusChange, handleSortChange, handleOrdersPerPageChange } from './orderHandlers';

function OrderListPage() {
  const [orders, setOrders] = useState([]);
  const [orderStatuses, setOrderStatuses] = useState([]); // 빈 배열로 초기화
  const [selectedStatus, setSelectedStatus] = useState('전체보기');
  const [sortCriteria, setSortCriteria] = useState('default');
  const [sortDirection, setSortDirection] = useState('asc');
  const [currentPage, setCurrentPage] = useState(1);
  const [ordersPerPage, setOrdersPerPage] = useState(20);
  const navigate = useNavigate();

  useEffect(() => {
    fetchOrders().then(data => {
      setOrders(data);
      const statuses = fetchOrderStatuses(data); // 상태 목록 추출
      setOrderStatuses(statuses); // 상태 배열로 설정
    }).catch(() => {
      showError(errorMessages.fetchOrders);
    });
  }, []);

  useEffect(() => {
    setCurrentPage(1);
  }, [selectedStatus]);

  const handleDetailClick = (orderId) => {
    navigate(`/order/${orderId}`);
  };

  const currentOrders = getCurrentOrders(orders, selectedStatus, sortCriteria, sortDirection, currentPage, ordersPerPage);

  const totalPages = Math.ceil(filteredOrders(orders, selectedStatus).length / ordersPerPage);

  return (
    <div className="order-list-page-container">
      <h1>주문 목록</h1>
      <OrderFilterControls
        orderStatuses={orderStatuses}
        selectedStatus={selectedStatus}
        onStatusChange={(e) => handleStatusChange(e, setSelectedStatus, setCurrentPage)}
      />
      <OrderSortControls
        handleSortChange={(criteria) => handleSortChange(criteria, setSortCriteria)}
        sortDirection={sortDirection}
        setSortDirection={setSortDirection}
      />
      <OrderPagination
        currentPage={currentPage}
        totalPages={totalPages}
        setCurrentPage={setCurrentPage}
        ordersPerPage={ordersPerPage}
        handleOrdersPerPageChange={(e) => handleOrdersPerPageChange(e, setOrdersPerPage, setCurrentPage)}
      />
      <OrderList
        currentOrders={currentOrders}
        handleCheckBoxChange={() => {}} // 빈 함수 전달
        handleDetailClick={handleDetailClick}
      />
    </div>
  );
}

export default OrderListPage;
