// src/components/page/CartItem.js
import React from 'react';
import { Tooltip, OverlayTrigger } from 'react-bootstrap';

const CartItem = ({
  item,
  toggleCheckbox,
  decreaseQuantity,
  increaseQuantity,
  handleQuantityChange,
  removeAllFromCart
}) => (
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
            overlay={<Tooltip id={`tooltip-${item.id}`}>{item.warningMessage}</Tooltip>}
            show={item.warningVisible} // 경고 메시지가 있을 때만 표시
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
);

export default CartItem;
