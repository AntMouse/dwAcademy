// OrderListPage.js
import React, { useState, useEffect } from 'react';
import './OrderListPage.css';
import { fetchOrders, fetchOrderStatuses, handleStatusChange, handleOrdersPerPageChange, handleDeleteClick, handleStatusUpdateClick } from './orderHandlers';
import { showError, errorMessages } from './messages';
import OrderFilterControls from './OrderFilterControls';
import OrderList from './OrderList';
import OrderPagination from './OrderPagination'; // 페이지네이션 컴포넌트 가져오기
import OrderPaginationControls from './OrderPaginationControls'; // 페이지네이션 컨트롤 컴포넌트 가져오기
import OrderSearchComponent from './OrderSearchComponent'; // 검색 컴포넌트 가져오기
import { useNavigate } from 'react-router-dom';
import { filteredOrders, getCurrentOrders, handleCheckBoxChange, searchedOrders } from './orderUtils'; // 유틸리티 함수 import

function OrderListPage() {
  const [orders, setOrders] = useState([]);
  const [orderStatuses, setOrderStatuses] = useState([]); // 빈 배열로 초기화
  const [selectedStatus, setSelectedStatus] = useState('전체보기');
  const [sortCriteria, setSortCriteria] = useState('id');
  const [sortDirection, setSortDirection] = useState('desc');
  const [currentPage, setCurrentPage] = useState(1);
  const [ordersPerPage, setOrdersPerPage] = useState(20);
  const [searchTerm, setSearchTerm] = useState('');
  const [searchQuery, setSearchQuery] = useState(''); // 검색 버튼을 클릭했을 때의 검색어 상태
  const [checkedOrders, setCheckedOrders] = useState(new Set()); // 체크된 주문 ID를 저장하는 Set
  const navigate = useNavigate();

  // 컴포넌트가 마운트될 때 주문 목록과 주문 상태를 가져옴
  useEffect(() => {
    fetchOrders().then(data => {
      setOrders(data);
      fetchOrderStatuses().then(statuses => {
        setOrderStatuses(statuses);
      }).catch(() => {
        showError(errorMessages.fetchOrderStatuses);
      });
    }).catch(() => {
      showError(errorMessages.fetchOrders);
    });
  }, []);

  // 선택된 주문 상태가 변경될 때 현재 페이지를 1로 리셋
  useEffect(() => {
    setCurrentPage(1);
  }, [selectedStatus]);

  // 상세보기 클릭 핸들러
  const handleDetailClick = (orderId) => {
    navigate(`/order/${orderId}`);
  };

  // 검색어 변경 핸들러
  const handleSearchChange = (event) => {
    setSearchTerm(event.target.value);
  };

  // 검색 버튼 클릭 핸들러
  const handleSearchClick = () => {
    setSearchQuery(searchTerm);
    setCurrentPage(1);
  };

  // 정렬 변경 핸들러
  const handleSortChange = (criteria, direction) => {
    setSortCriteria(criteria);
    setSortDirection(direction);
  };

  // 현재 페이지의 주문 목록을 계산
  const currentOrders = getCurrentOrders(orders, selectedStatus, sortCriteria, sortDirection, currentPage, ordersPerPage, searchQuery);

  // 총 페이지 수를 계산 (검색된 주문과 상태 필터링된 주문 기준)
  const filteredAndSearchedOrders = searchedOrders(filteredOrders(orders, selectedStatus), searchQuery);
  const totalPages = Math.ceil(filteredAndSearchedOrders.length / ordersPerPage);

  // 총 체크된 주문 개수 계산
  const totalCheckedCount = checkedOrders.size;

  // 각 주문에 체크 상태를 추가
  const currentOrdersWithChecked = currentOrders.map(order => ({
    ...order,
    checked: checkedOrders.has(order.id)
  }));

  return (
    <div className="order-list-page-container">
      <h1>주문 목록</h1>
      <div className="order-list-page-controls">
        <div className="order-count">주문목록 (총 {filteredAndSearchedOrders.length}개)</div>
        <OrderSearchComponent
          searchTerm={searchTerm}
          handleSearchChange={handleSearchChange}
          handleSearchClick={handleSearchClick}
          setCurrentPage={setCurrentPage}
        />
        <OrderFilterControls
          orderStatuses={orderStatuses}
          selectedStatus={selectedStatus}
          onStatusChange={(e) => handleStatusChange(e, setSelectedStatus, setCurrentPage)}
        />
        <OrderPaginationControls
          ordersPerPage={ordersPerPage}
          handleOrdersPerPageChange={(e) => handleOrdersPerPageChange(e, setOrdersPerPage, setCurrentPage)}
          totalOrders={filteredAndSearchedOrders.length}
        />
      </div>
      <div className="order-list-page-actions">
        <button onClick={() => handleDeleteClick(checkedOrders, setCheckedOrders, setOrders)} className="delete-button">삭제</button>
        <button onClick={() => handleStatusUpdateClick(checkedOrders, 'COMPLETED', setCheckedOrders, setOrders)} className="status-button">배송완료</button>
        <button onClick={() => handleStatusUpdateClick(checkedOrders, 'PENDING', setCheckedOrders, setOrders)} className="status-button">배송준비</button>
        <span>선택된 주문: {totalCheckedCount}개</span>
      </div>
      <OrderList
        currentOrders={currentOrdersWithChecked}
        handleCheckBoxChange={(orderId, checked, isSelectAll) => handleCheckBoxChange(orderId, checked, isSelectAll, currentOrders, setCheckedOrders)}
        handleDetailClick={handleDetailClick}
        orderStatuses={orderStatuses} // 주문 상태 목록 전달
        handleSortChange={handleSortChange} // 정렬 함수 전달
        sortCriteria={sortCriteria}
        sortDirection={sortDirection}
        totalCheckedCount={totalCheckedCount} // 총 체크된 주문 개수 전달
      />
      <OrderPagination
        currentPage={currentPage}
        totalPages={totalPages}
        setCurrentPage={setCurrentPage}
      />
    </div>
  );
}

export default OrderListPage;
