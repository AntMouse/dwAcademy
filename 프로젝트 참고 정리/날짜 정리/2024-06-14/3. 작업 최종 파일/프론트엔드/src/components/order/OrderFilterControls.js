import React from 'react';

function OrderFilterControls({ orderStatuses, selectedStatus, onStatusChange }) {
  return (
    <div className="order-filter-controls-container">
      <select value={selectedStatus} onChange={onStatusChange} className="order-filter-controls-select">
        <option value="전체보기">전체보기</option>
        {orderStatuses.map(status => (
          <option key={status} value={status}>{status}</option>
        ))}
      </select>
    </div>
  );
}

export default OrderFilterControls;
