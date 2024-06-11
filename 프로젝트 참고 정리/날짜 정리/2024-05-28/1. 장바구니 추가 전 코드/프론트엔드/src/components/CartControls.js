import React, { useState } from 'react';

function CartControls({ addItemToCart }) {
  const [productId, setProductId] = useState('');
  const [quantity, setQuantity] = useState(1);

  const handleAddToCart = () => {
    addItemToCart(productId, quantity);
    setProductId('');
    setQuantity(1);
  };

  return (
    <div className="mb-3">
      <input
        type="text"
        placeholder="Product ID"
        value={productId}
        onChange={e => setProductId(e.target.value)}
        className="form-control"
      />
      <input
        type="number"
        placeholder="Quantity"
        value={quantity}
        onChange={e => setQuantity(Number(e.target.value))}
        className="form-control mt-2"
        min="1"
      />
      <button onClick={handleAddToCart} className="btn btn-primary mt-2">Add to Cart</button>
    </div>
  );
}

export default CartControls;