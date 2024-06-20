import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

function CartListPage() {
  const [carts, setCarts] = useState([]);
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    console.log("CartListPage 컴포넌트 렌더링됨");  // 컴포넌트 렌더링 확인용 콘솔 로그
    const fetchCarts = async () => {
      try {
        const { data } = await axios.get(`${apiUrl}/api/carts`);
        console.log("백엔드에서 받은 데이터:", data); // 백엔드 데이터 확인
        if (Array.isArray(data)) {
          setCarts(data);
        } else {
          setErrorMessage('장바구니 데이터를 가져오는 데 오류가 발생했습니다.');
        }
      } catch (error) {
        setErrorMessage('장바구니 데이터를 가져오는 도중 오류가 발생했습니다.');
        console.error(error);
      }
    };
    fetchCarts();
  }, []);

  const handleViewCart = (cartId, memberId) => {
    navigate(`/cart/${cartId}/${memberId}`);
  };

  return (
    <div className="container">
      <h1>장바구니 목록</h1>
      {errorMessage ? (
        <div className="text-danger">{errorMessage}</div>
      ) : (
        <table className="table table-striped">
          <thead>
            <tr>
              <th>회원 ID</th>
              <th>장바구니 ID</th>
              <th>회원명</th>
              <th>총 수량</th>
              <th>총 가격</th>
              <th>동작</th>
            </tr>
          </thead>
          <tbody>
            {carts.map(cart => (
              <tr key={cart.id}>
                <td>{cart.memberId}</td>
                <td>{cart.id}</td>
                <td>{cart.memberName}</td>
                <td>{cart.totalQuantity}</td>
                <td>{cart.totalPrice}</td>
                <td>
                  <button
                    onClick={() => handleViewCart(cart.id, cart.memberId)}
                    className="btn btn-primary btn-sm"
                  >
                    장바구니 보기
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default CartListPage;
