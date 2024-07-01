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
    } catch (error) {
      setErrorMessage('장바구니를 가져오는 도중 오류가 발생했습니다.');
      console.error(error);
    }
  };

  const handleQuantityChange = (productId, value, isAdd) => {
    const setFunction = isAdd ? setAddQuantities : setQuantities;
    setFunction(prev => ({
      ...prev,
      [productId]: value,
    }));
  };

  const updateCart = async (productId, quantity, method) => {
    const url = `${apiUrl}/api/carts/${id}/items`;
    const params = { productId, quantity };
    try {
      await axios({ method, url, params });
      fetchCart();
    } catch (error) {
      setErrorMessage('장바구니 업데이트 도중 오류가 발생했습니다.');
      console.error(error);
    }
  };

  const handleRemoveFromCart = (productId) => {
    const quantity = quantities[productId] || 1;
    updateCart(productId, quantity, 'delete');
  };

  const handleAddToCart = (productId) => {
    const quantity = addQuantities[productId] || 1;
    updateCart(productId, quantity, 'post');
  };

  const removeAllFromCart = (productId) => {
    updateCart(productId, 999999, 'delete');
  };

  const getTotalQuantity = () => cart?.items.reduce((total, item) => total + item.quantity, 0) || 0;

  if (errorMessage) return <div className="text-danger">{errorMessage}</div>;
  if (!cart) return <div>로딩 중...</div>;

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
                  onChange={(e) => handleQuantityChange(item.product.id, e.target.value, false)}
                  className="form-control d-inline w-auto mr-2"
                />
                <button onClick={() => handleRemoveFromCart(item.product.id)} className="btn btn-danger btn-sm">상품 제거</button>
              </div>
              <div className="d-flex align-items-center mt-2">
                <input
                  type="number"
                  min="1"
                  value={addQuantities[item.product.id] || 1}
                  onChange={(e) => handleQuantityChange(item.product.id, e.target.value, true)}
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
