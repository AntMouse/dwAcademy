import React, { useState } from 'react';
import './cartlist.css';
import logo from '../assets/Logo.png';
import itemImage1 from '../assets/1.jpg';
import itemImage2 from '../assets/2.jpg';
import itemImage3 from '../assets/3.jpg';
import itemImage4 from '../assets/4.jpg';

const CartList = () => {
  const [items, setItems] = useState([
    { id: 1, name: '엘론 미니 레터링 후드', price: 10000, image: itemImage1, quantity: 1, checked: false },
    { id: 2, name: '파리스 후드', price: 15000, image: itemImage2, quantity: 1, checked: false },
    { id: 3, name: '캔디 이중지 오버핏 탄탄 후드', price: 20000, image: itemImage3, quantity: 1, checked: false },
    { id: 4, name: '유니버시티 오버핏 후드', price: 25000, image: itemImage4, quantity: 1, checked: false }
  ]);
  const [selectAll, setSelectAll] = useState(false);

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

  const increaseQuantity = (id) => {
    setItems(items.map(item => 
      item.id === id ? { ...item, quantity: item.quantity + 1 } : item
    ));
  };

  const decreaseQuantity = (id) => {
    setItems(items.map(item => 
      item.id === id && item.quantity > 1 ? { ...item, quantity: item.quantity - 1 } : item
    ));
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
                <div className="checkbox-item" key={item.id}>
                  <input 
                    type="checkbox" 
                    id={`item${item.id}`} 
                    name={`item${item.id}`} 
                    checked={item.checked} 
                    onChange={() => toggleCheckbox(item.id)} 
                  />
                  <img src={item.image} alt={item.name} className="section-image" />
                  <label htmlFor={`item${item.id}`}>
                    <p className='name'>{item.name}</p>
                    <p className='price'>{item.price.toLocaleString()}원</p>
                    <div className="quantity-control">
                      <button onClick={() => decreaseQuantity(item.id)}>-</button>
                      <span>{item.quantity}</span>
                      <button onClick={() => increaseQuantity(item.id)}>+</button>
                    </div>
                  </label>
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
            <h3>예상 결제 금액</h3>
            <p>상품금액 : {totalAmount.toLocaleString()}원</p>
            <p className='total'>총 결제금액 : {totalAmount.toLocaleString()}원</p>
            <button className="checkout-button" onClick={handleCheckout}>결제하기</button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CartList;
