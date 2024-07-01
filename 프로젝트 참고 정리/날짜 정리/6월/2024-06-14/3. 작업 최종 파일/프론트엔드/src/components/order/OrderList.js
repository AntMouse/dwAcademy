import React from 'react';

function OrderList({ currentOrders, handleCheckBoxChange, handleDetailClick }) {
  const getProductName = (orderItems) => {
    if (!orderItems || orderItems.length === 0) return '상품 없음'; // 주문한 상품이 없는 경우
    if (orderItems.length === 1) {
      return orderItems[0].productName || '상품 이름 없음'; // 주문한 상품이 1개인 경우
    }
    return `${orderItems[0].productName || '상품 이름 없음'} 외 ${orderItems.length - 1}개`; // 주문한 상품이 2개 이상인 경우
  };

  return (
    <table className="order-list-table">
      <thead>
        <tr>
          <th>선택</th>
          <th>주문번호</th>
          <th>상품명</th>
          <th>총가격</th>
          <th>주문상태</th>
          <th>상세정보</th>
        </tr>
      </thead>
      <tbody>
        {currentOrders.map(order => (
          <tr key={order.id}>
            <td>
              <input
                type="checkbox"
                onChange={(e) => handleCheckBoxChange(order.id, e.target.checked)}
                className="order-list-checkbox"
              />
            </td>
            <td>{order.id}</td>
            <td>{getProductName(order.orderItems)}</td>
            <td>{order.totalPrice}</td>
            <td>{order.status}</td>
            <td>
              <button onClick={() => handleDetailClick(order.id)} className="order-list-detail-button">상세정보</button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}

export default OrderList;
