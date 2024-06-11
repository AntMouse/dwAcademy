import React, { useState } from 'react';

function CartControls({ addItemToCart }) {
  const [productName, setProductName] = useState('');
  const [quantity, setQuantity] = useState(1);

  const handleAddToCart = () => {
    if (productName && quantity > 0) {
      addItemToCart(productName, quantity);
      setProductName('');
      setQuantity(1);
    } else {
      alert('상품 이름과 수량을 올바르게 입력하세요.');
    }
  };

  return (
    <div className="mb-3">
      <input
        type="text"
        placeholder="상품 이름"
        value={productName}
        onChange={e => setProductName(e.target.value)}
        className="form-control"
      />
      <input
        type="number"
        placeholder="수량"
        value={quantity}
        onChange={e => setQuantity(Number(e.target.value))}
        className="form-control mt-2"
        min="1"
      />
      <button onClick={handleAddToCart} className="btn btn-primary mt-2">장바구니에 추가</button>
    </div>
  );
}

export default CartControls;
