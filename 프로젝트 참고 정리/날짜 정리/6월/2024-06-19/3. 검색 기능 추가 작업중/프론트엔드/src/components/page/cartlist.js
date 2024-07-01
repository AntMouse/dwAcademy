// src/components/page/CartList.js
import React from 'react';
import CartItem from './CartItem';
import CartSummary from './CartSummary';
import useCart from '../../hooks/useCart';
import './cartlist.css';
import logo from '../../assets/Logo.png';

const CartList = ({ currentMember }) => {
  const {
    items,
    selectAll,
    errorMessage,
    totalAmount,
    toggleCheckbox,
    toggleSelectAll,
    increaseQuantity,
    decreaseQuantity,
    handleQuantityChange,
    removeAllFromCart,
    handleCheckout
  } = useCart(currentMember);

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
                <CartItem
                  key={item.id}
                  item={item}
                  toggleCheckbox={toggleCheckbox}
                  decreaseQuantity={decreaseQuantity}
                  increaseQuantity={increaseQuantity}
                  handleQuantityChange={handleQuantityChange}
                  removeAllFromCart={removeAllFromCart}
                />
              ))}
            </div>
            <div className="select-all-container">
              <button onClick={toggleSelectAll}>
                <strong>{selectAll ? "전체 해제" : "전체 선택"}</strong>
              </button>
            </div>
          </div>
          <CartSummary totalAmount={totalAmount} handleCheckout={handleCheckout} />
        </div>
      </div>
      {errorMessage && <div className="text-danger">{errorMessage}</div>}
    </div>
  );
};

export default CartList;
