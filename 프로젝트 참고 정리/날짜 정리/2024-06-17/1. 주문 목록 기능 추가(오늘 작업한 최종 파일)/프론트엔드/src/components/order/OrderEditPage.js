import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';

function OrderEditPage() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [order, setOrder] = useState(null);
  const [orderStatuses, setOrderStatuses] = useState([]);
  const [editOrderData, setEditOrderData] = useState({
    status: '',
  });

  useEffect(() => {
    // 주문 정보를 가져옴
    axios.get(`http://localhost:8080/api/orders/${id}`)
      .then(response => {
        const orderData = response.data;
        setOrder(orderData);
        setEditOrderData({
          status: orderData.status,
        });
      })
      .catch(error => {
        console.error('주문 정보를 불러오는 중 오류가 발생했습니다:', error);
      });

    // 주문 상태를 가져옴
    axios.get('http://localhost:8080/api/order-statuses')
      .then(response => {
        setOrderStatuses(response.data);
      })
      .catch(error => {
        console.error('주문 상태 정보를 불러오는 중 오류가 발생했습니다:', error);
      });
  }, [id]);

  const handleInputChange = (event, key) => {
    const { value } = event.target;
    setEditOrderData(prevState => ({
      ...prevState,
      [key]: value
    }));
  };

  const handleSaveClick = () => {
    axios.put(`http://localhost:8080/api/orders/${id}/status`, { status: editOrderData.status })
      .then(() => {
        alert('주문 정보가 성공적으로 업데이트되었습니다.');
        navigate('/orderlistpage');
      })
      .catch(error => {
        console.error('주문 정보를 업데이트하는 중 오류가 발생했습니다:', error);
        alert('주문 정보를 업데이트하는 중 오류가 발생했습니다.');
      });
  };

  const handleCancelClick = () => {
    navigate('/orderlistpage');
  };

  if (!order) {
    return <div>로딩 중...</div>;
  }

  return (
    <div className="order-edit-page-container">
      <h1>주문 정보</h1>
      <div className="order-edit-page-form">
        <label>수취인 이름</label>
        <input
          type="text"
          className="order-edit-page-input"
          value={order.recipientName}
          disabled
        />
        <label>연락처</label>
        <input
          type="text"
          className="order-edit-page-input"
          value={order.contactNumber}
          disabled
        />
        <label>배송지</label>
        <input
          type="text"
          className="order-edit-page-input"
          value={order.deliveryLocation}
          disabled
        />
        <label>요청사항</label>
        <input
          type="text"
          className="order-edit-page-input"
          value={order.request}
          disabled
        />
        <label>상태</label>
        <select
          className="order-edit-page-input"
          value={editOrderData.status}
          onChange={(e) => handleInputChange(e, 'status')}
        >
          {orderStatuses.map((status, index) => (
            <option key={index} value={status.status}>{status.displayName}</option>
          ))}
        </select>
        <div className="order-edit-page-buttons">
          <button onClick={handleSaveClick}>저장</button>
          <button onClick={handleCancelClick}>취소</button>
        </div>
      </div>
    </div>
  );
}

export default OrderEditPage;
