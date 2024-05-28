import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import CartControls from './CartControls';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

function CartPage() {
  const { id } = useParams();
  const [cart, setCart] = useState(null);
  const [errorMessage, setErrorMessage] = useState('');
  const [addItemErrorMessage, setAddItemErrorMessage] = useState('');

  useEffect(() => {
    fetchCart();
  }, [id]);

  const fetchCart = async () => {
    try {
      const response = await axios.get(`${apiUrl}/api/carts/${id}`);
      setCart(response.data);
      setErrorMessage('');
    } catch (error) {
      if (error.response && error.response.status === 404) {
        setErrorMessage('장바구니를 찾을 수 없습니다. 먼저 장바구니를 생성하세요.');
      } else {
        setErrorMessage('장바구니를 가져오는 도중 오류가 발생했습니다.');
        console.error('장바구니를 가져오는 도중 오류가 발생했습니다!', error);
      }
    }
  };

  const addItemToCart = async (productName, quantity) => {
    try {
      await axios.post(`${apiUrl}/api/carts/${id}/items`, null, {
        params: {
          productName,
          quantity
        }
      });
      fetchCart();
      setAddItemErrorMessage(''); // 성공적으로 추가되면 오류 메시지 초기화
    } catch (error) {
      console.error('장바구니에 상품을 추가하는 도중 오류가 발생했습니다!', error);
      setAddItemErrorMessage('존재하지 않는 상품입니다. 올바른 상품 이름을 입력하세요.');
    }
  };

  const removeItemFromCart = async (productId, quantity) => {
    try {
      await axios.delete(`${apiUrl}/api/carts/${id}/items`, {
        params: {
          productId,
          quantity
        }
      });
      fetchCart();
      setErrorMessage(''); // 오류 메시지 초기화
    } catch (error) {
      setErrorMessage('장바구니에서 상품을 제거하는 도중 오류가 발생했습니다.');
      console.error('장바구니에서 상품을 제거하는 도중 오류가 발생했습니다!', error);
    }
  };

  const removeAllFromCart = async (productId) => {
    try {
      await axios.delete(`${apiUrl}/api/carts/${id}/items`, {
        params: {
          productId,
          quantity: 999999 // 모든 수량을 제거하기 위해 큰 숫자를 사용합니다.
        }
      });
      fetchCart();
      setErrorMessage(''); // 오류 메시지 초기화
    } catch (error) {
      setErrorMessage('장바구니에서 모든 상품을 제거하는 도중 오류가 발생했습니다.');
      console.error('장바구니에서 모든 상품을 제거하는 도중 오류가 발생했습니다!', error);
    }
  };

  const getTotalQuantity = () => {
    return cart.items.reduce((total, item) => total + item.quantity, 0);
  };

  if (errorMessage) return <div className="text-danger">{errorMessage}</div>;
  if (cart === null) return <div>로딩 중...</div>;
  // if (!cart.items || cart.items.length === 0) return <div>장바구니에 상품이 없습니다.</div>;

  return (
    <div className="container">
      <h1>장바구니</h1>
      <div>총 상품 수량: {getTotalQuantity()}</div>
      <ul className="list-group mb-3">
        {cart.items.map(item => (
          <li key={item.id} className="list-group-item">
            {item.product.productName} - {item.quantity} 개
            <button onClick={() => removeItemFromCart(item.product.id, 1)} className="btn btn-danger btn-sm float-right ml-2">1개 제거</button>
            <button onClick={() => removeAllFromCart(item.product.id)} className="btn btn-danger btn-sm float-right">모두 제거</button>
          </li>
        ))}
      </ul>
      {addItemErrorMessage && <div className="text-danger">{addItemErrorMessage}</div>}
      <CartControls addItemToCart={addItemToCart} />
    </div>
  );
}

export default CartPage;
