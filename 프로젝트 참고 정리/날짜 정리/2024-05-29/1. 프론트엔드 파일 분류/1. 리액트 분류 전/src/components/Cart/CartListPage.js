import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

function CartListPage() {
  const [carts, setCarts] = useState([]);
  const [errorMessage, setErrorMessage] = useState('');

  useEffect(() => {
    fetchCarts();
  }, []);

  const fetchCarts = async () => {
    try {
      const response = await axios.get(`${apiUrl}/api/carts`);
      if (Array.isArray(response.data)) {
        setCarts(response.data);
        setErrorMessage('');
      } else {
        setErrorMessage('장바구니 데이터를 가져오는 데 오류가 발생했습니다.');
      }
    } catch (error) {
      setErrorMessage('장바구니 데이터를 가져오는 도중 오류가 발생했습니다.');
      console.error('장바구니 데이터를 가져오는 도중 오류가 발생했습니다!', error);
    }
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
              <th>ID</th>
              <th>이름</th>
              <th>동작</th>
            </tr>
          </thead>
          <tbody>
            {carts.map(cart => (
              <tr key={cart.id}>
                <td>{cart.id}</td>
                <td>{cart.name}</td>
                <td>
                  <Link to={`/cart/${cart.id}`} className="btn btn-primary btn-sm">장바구니 보기</Link>
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
