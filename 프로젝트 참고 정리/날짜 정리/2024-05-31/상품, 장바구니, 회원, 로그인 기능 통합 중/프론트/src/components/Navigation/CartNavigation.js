import React, { useState, useEffect } from 'react';
import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

function CartNavigation({ setCurrentCart, currentCart, currentMember }) {
  const [message, setMessage] = useState('');

  useEffect(() => {
    const setCartByMember = async (memberId) => {
      try {
        const { data, status } = await axios.get(`${apiUrl}/api/carts/member/${memberId}`);
        if (status === 200) {
          setCurrentCart(data);
          setMessage('장바구니가 설정되었습니다.');
        } else {
          setMessage('장바구니를 찾을 수 없습니다.');
        }
      } catch {
        setMessage('장바구니를 찾을 수 없습니다.');
      }
    };

    if (currentMember) {
      setCartByMember(currentMember.id);
    }
  }, [currentMember, setCurrentCart]);

  return (
    <div className="mb-3">
      {currentMember && <div className="mt-2">현재 로그인된 회원: {currentMember.memberName}</div>}
      {message && <div className="text-danger mt-2">{message}</div>}
      {currentCart && <div className="mt-2">현재 설정된 장바구니: {currentCart.id}</div>}
    </div>
  );
}

export default CartNavigation;
