// src/components/page/CartSummary.js
import React from 'react';

const CartSummary = ({ totalAmount, handleCheckout }) => (
  <div className="section2 section">
    <h3>상품금액 : {totalAmount.toLocaleString()}원</h3>
    <p className='total'>총 결제금액 : {totalAmount.toLocaleString()}원</p>
    <button className="checkout-button" onClick={handleCheckout}>결제하기</button>
  </div>
);

export default CartSummary;
