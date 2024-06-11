import React, { useState } from 'react';
import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

function CartNavigation({ setCurrentCart, currentCart }) {
  const [cartName, setCartName] = useState('');
  const [viewCartName, setViewCartName] = useState('');
  const [message, setMessage] = useState('');

  const handleSetCart = async () => {
    if (!cartName) {
      setMessage('장바구니 이름을 입력하세요.');
      return;
    }

    try {
      const response = await axios.get(`${apiUrl}/api/carts/name/${cartName}`);
      if (response.status === 200) {
        setCurrentCart(response.data);
        setMessage('장바구니가 설정되었습니다.');
      } else {
        setMessage('장바구니를 찾을 수 없습니다.');
      }
    } catch (error) {
      setMessage('장바구니를 찾을 수 없습니다.');
    }
  };

  return (
    <div className="mb-3">
      <input
        type="text"
        placeholder="장바구니 이름"
        value={cartName}
        onChange={e => setCartName(e.target.value)}
        className="form-control d-inline w-auto mr-2"
      />
      <button onClick={handleSetCart} className="btn btn-secondary">장바구니 설정</button>
      {message && <div className="text-danger mt-2">{message}</div>}
      {currentCart && <div className="mt-2">현재 설정된 장바구니: {currentCart.name}</div>}
    </div>
  );
}

export default CartNavigation;
