import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

function CartPage() {
  const { id } = useParams();
  const [cart, setCart] = useState(null);
  const [errorMessage, setErrorMessage] = useState('');
  const [quantities, setQuantities] = useState({});
  const [addQuantities, setAddQuantities] = useState({});

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

  const handleQuantityChange = (productId, value) => {
    setQuantities({
      ...quantities,
      [productId]: value,
    });
  };

  const handleAddQuantityChange = (productId, value) => {
    setAddQuantities({
      ...addQuantities,
      [productId]: value,
    });
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

  const addItemToCart = async (productId, quantity) => {
    try {
      await axios.post(`${apiUrl}/api/carts/${id}/items`, null, {
        params: {
          productId,
          quantity
        }
      });
      fetchCart();
      setErrorMessage(''); // 오류 메시지 초기화
    } catch (error) {
      setErrorMessage('장바구니에 상품을 추가하는 도중 오류가 발생했습니다.');
      console.error('장바구니에 상품을 추가하는 도중 오류가 발생했습니다!', error);
    }
  };

  const handleRemoveFromCart = (productId) => {
    const quantity = quantities[productId] || 1;
    const item = cart.items.find(item => item.product.id === productId);

    if (item) {
      const quantityToRemove = Math.min(quantity, item.quantity);
      removeItemFromCart(productId, quantityToRemove);
    }
  };

  const handleAddToCart = (productId) => {
    const quantity = addQuantities[productId] || 1;
    addItemToCart(productId, quantity);
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

  return (
    <div className="container">
      <h1>장바구니</h1>
      <div>총 상품 수량: {getTotalQuantity()}</div>
      <ul className="list-group mb-3">
        {cart.items.map(item => (
          <li key={item.id} className="list-group-item d-flex justify-content-between align-items-center">
            <div>
              <div>{item.product.productName} - {item.quantity} 개</div>
              <div>상품 유형: {item.product.productType}</div>
              <div>가격: ${item.product.price}</div>
              <div>설명: {item.product.explanation}</div>
              <div className="d-flex align-items-center mt-2">
                <input
                  type="number"
                  min="1"
                  value={quantities[item.product.id] || 1}
                  onChange={(e) => handleQuantityChange(item.product.id, e.target.value)}
                  className="form-control d-inline w-auto mr-2"
                />
                <button onClick={() => handleRemoveFromCart(item.product.id)} className="btn btn-danger btn-sm">상품 제거</button>
              </div>
              <div className="d-flex align-items-center mt-2">
                <input
                  type="number"
                  min="1"
                  value={addQuantities[item.product.id] || 1}
                  onChange={(e) => handleAddQuantityChange(item.product.id, e.target.value)}
                  className="form-control d-inline w-auto mr-2"
                />
                <button onClick={() => handleAddToCart(item.product.id)} className="btn btn-primary btn-sm">상품 추가</button>
              </div>
            </div>
            <button onClick={() => removeAllFromCart(item.product.id)} className="btn btn-danger btn-sm">X</button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default CartPage;
