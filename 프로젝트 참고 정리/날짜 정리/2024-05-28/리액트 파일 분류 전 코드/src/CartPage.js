import React, { useState, useEffect } from 'react';
import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

function CartPage() {
  const [cart, setCart] = useState(null);
  const [cartId, setCartId] = useState(1); // Assume cart ID is 1 for now
  const [productId, setProductId] = useState('');
  const [quantity, setQuantity] = useState(1);

  useEffect(() => {
    fetchCart();
  }, []);

  const fetchCart = async () => {
    try {
      const response = await axios.get(`${apiUrl}/api/carts/${cartId}`);
      setCart(response.data);
    } catch (error) {
      if (error.response && error.response.status === 404) {
        console.error("Cart not found. Please create a cart first.");
      } else {
        console.error("There was an error fetching the cart!", error);
      }
    }
  };

  const addItemToCart = async () => {
    try {
      await axios.post(`${apiUrl}/api/carts/${cartId}/items`, null, {
        params: {
          productId,
          quantity
        }
      });
      fetchCart();
    } catch (error) {
      console.error("There was an error adding the item to the cart!", error);
    }
  };

  const removeItemFromCart = async (productId, quantity) => {
    try {
      await axios.delete(`${apiUrl}/api/carts/${cartId}/items`, {
        params: {
          productId,
          quantity
        }
      });
      fetchCart();
    } catch (error) {
      console.error("There was an error removing the item from the cart!", error);
    }
  };

  if (cart === null) return <div>Loading...</div>;
  if (!cart.items || cart.items.length === 0) return <div>No items in the cart.</div>;

  return (
    <div className="container">
      <h1>Cart</h1>
      <ul className="list-group mb-3">
        {cart.items.map(item => (
          <li key={item.id} className="list-group-item">
            {item.product.productName} - {item.quantity} pcs
            <button onClick={() => removeItemFromCart(item.product.id, 1)} className="btn btn-danger btn-sm float-right">Remove 1</button>
          </li>
        ))}
      </ul>
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
          onChange={e => setQuantity(e.target.value)}
          className="form-control mt-2"
        />
        <button onClick={addItemToCart} className="btn btn-primary mt-2">Add to Cart</button>
      </div>
    </div>
  );
}

export default CartPage;