import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Tooltip, OverlayTrigger } from 'react-bootstrap';
import './cartlist.css';
import logo from '../../assets/Logo.png';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

const CartList = ({ currentMember }) => {
  const [items, setItems] = useState([]);
  const [selectAll, setSelectAll] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');
  const [warningMessage, setWarningMessage] = useState('');

  useEffect(() => {
    if (currentMember) {
      console.log('Current Member ID:', currentMember.id); // 디버깅용 로그 추가
      fetchCartByMemberId(currentMember.id);
    }
  }, [currentMember]);

  const fetchCartByMemberId = async (memberId) => {
    try {
      const response = await axios.get(`${apiUrl}/api/carts/member/${memberId}`);
      console.log('Cart Data:', response.data); // 디버깅용 로그 추가
      setItems(response.data.items.map(item => ({
        id: item.product.id,
        name: item.product.productName,
        price: item.product.price,
        image: item.product.imageUrl, // Assuming the image URL is provided by the API
        quantity: item.quantity,
        checked: false,
      })));
    } catch (error) {
      setErrorMessage('장바구니를 가져오는 도중 오류가 발생했습니다.');
      console.error(error);
    }
  };

  const toggleCheckbox = (id) => {
    setItems(items.map(item =>
      item.id === id ? { ...item, checked: !item.checked } : item
    ));
  };

  const toggleSelectAll = () => {
    const newSelectAll = !selectAll;
    setSelectAll(newSelectAll);
    setItems(items.map(item => ({ ...item, checked: newSelectAll })));
  };

  const increaseQuantity = async (id) => {
    const item = items.find(item => item.id === id);
    if (item.quantity < 99) { // 최대 수량 99로 제한
      const newQuantity = item.quantity + 1;
      try {
        await updateCart(id, newQuantity, 'put');
        setItems(items.map(item =>
          item.id === id ? { ...item, quantity: newQuantity } : item // checked 상태를 변경하지 않음
        ));
      } catch (error) {
        setErrorMessage('수량을 변경하는 도중 오류가 발생했습니다.');
        console.error(error);
      }
    } else {
      setWarningMessage('최대 수량은 99입니다.');
      setTimeout(() => setWarningMessage(''), 3000); // 3초 후 경고 메시지 제거
    }
  };

  const decreaseQuantity = async (id) => {
    const item = items.find(item => item.id === id);
    if (item.quantity > 1) {
      const newQuantity = item.quantity - 1;
      try {
        await updateCart(id, newQuantity, 'put');
        setItems(items.map(item =>
          item.id === id ? { ...item, quantity: newQuantity } : item // checked 상태를 변경하지 않음
        ));
      } catch (error) {
        setErrorMessage('수량을 변경하는 도중 오류가 발생했습니다.');
        console.error(error);
      }
    }
  };

  const handleQuantityChange = async (id, value) => {
    const item = items.find(item => item.id === id);
    const newQuantity = parseInt(value, 10);

    if (isNaN(newQuantity) || newQuantity <= 0) { // 값이 NaN이거나 0 이하이면 1로 설정
      setItems(items.map(item =>
        item.id === id ? { ...item, quantity: 1 } : item // checked 상태를 변경하지 않음
      ));
      try {
        await updateCart(id, 1, 'put');
      } catch (error) {
        setErrorMessage('수량을 변경하는 도중 오류가 발생했습니다.');
        console.error(error);
      }
    } else if (newQuantity <= 99) { // 1 이상 99 이하인 경우
      try {
        await updateCart(id, newQuantity, 'put');
        setItems(items.map(item =>
          item.id === id ? { ...item, quantity: newQuantity } : item // checked 상태를 변경하지 않음
        ));
      } catch (error) {
        setErrorMessage('수량을 변경하는 도중 오류가 발생했습니다.');
        console.error(error);
      }
    } else {
      setWarningMessage('최대 수량은 99입니다.');
      setTimeout(() => setWarningMessage(''), 3000); // 3초 후 경고 메시지 제거
    }
  };

  const updateCart = async (productId, quantity, method) => {
    const url = `${apiUrl}/api/carts/${currentMember.id}/items/${productId}`;
    const params = { quantity };
    try {
      await axios({ method, url, params });
      // fetchCartByMemberId(currentMember.id); // 불필요한 호출 제거
    } catch (error) {
      setErrorMessage('장바구니 업데이트 도중 오류가 발생했습니다.');
      console.error(error);
    }
  };

  const removeAllFromCart = async (productId) => {
    try {
      await axios.delete(`${apiUrl}/api/carts/${currentMember.id}/items/${productId}`);
      fetchCartByMemberId(currentMember.id);
    } catch (error) {
      setErrorMessage('상품 삭제 중 오류가 발생했습니다.');
      console.error(error);
    }
  };

  const totalAmount = items.reduce((sum, item) => 
    item.checked ? sum + (item.price * item.quantity) : sum, 0
  );

  const handleCheckout = () => {
    // 여기에 결제 처리 로직을 추가하세요
    console.log('결제하기 버튼이 클릭되었습니다.');
  };

  return (
    <div className="cart">
      <img src={logo} alt="Logo" className="logo" />
      <div className="cart-section">
        <h2>◁ 장바구니</h2>
        <p className="purchase-text">일반구매</p>
        <div className="inner-section">
          <div className="section1 section">
            <div className="checkbox-group">
              {items.map(item => (
                <div className="checkbox-item d-flex justify-content-between align-items-center" key={item.id}>
                  <div className="d-flex align-items-center">
                    <input 
                      type="checkbox" 
                      id={`item${item.id}`} 
                      name={`item${item.id}`} 
                      checked={item.checked} 
                      onChange={() => toggleCheckbox(item.id)} 
                    />
                    <img src={item.image} alt={item.name} className="section-image" />
                    <label htmlFor={`item${item.id}`} className="ml-2">
                      <p className='name'>{item.name}</p>
                      <p className='price'>{item.price.toLocaleString()}원</p>
                      <div className="quantity-control">
                        <button onClick={() => decreaseQuantity(item.id)}>-</button>
                        <OverlayTrigger
                          placement="top"
                          overlay={<Tooltip id={`tooltip-${item.id}`}>{warningMessage}</Tooltip>}
                          show={warningMessage && item.id === items.find(i => i.id === item.id)?.id}
                        >
                          <input 
                            type="number" 
                            value={item.quantity} 
                            onChange={(e) => handleQuantityChange(item.id, e.target.value)}
                            className="quantity-input"
                          />
                        </OverlayTrigger>
                        <button onClick={() => increaseQuantity(item.id)}>+</button>
                      </div>
                    </label>
                  </div>
                  <button onClick={() => removeAllFromCart(item.id)} className="btn btn-danger btn-sm ml-3">X</button>
                </div>
              ))}
            </div>
            <div className="select-all-container">
              <button onClick={toggleSelectAll}>
                <strong>{selectAll ? "전체 해제" : "전체 선택"}</strong>
              </button>
            </div>
          </div>
          <div className="section2 section">
            <h3>상품금액 : {totalAmount.toLocaleString()}원</h3>
            <p className='total'>총 결제금액 : {totalAmount.toLocaleString()}원</p>
            <button className="checkout-button" onClick={handleCheckout}>결제하기</button>
          </div>
        </div>
      </div>
      {errorMessage && <div className="text-danger">{errorMessage}</div>}
    </div>
  );
};

export default CartList;
