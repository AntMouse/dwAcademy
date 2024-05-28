import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import CartControls from './components/CartControls';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

function CartPage() {
  const { id: cartId } = useParams();
  const [cart, setCart] = useState(null);

  useEffect(() => {
    fetchCart();
  }, [cartId]);

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

  const addItemToCart = async (productId, quantity) => {
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

  const removeAllFromCart = async (productId) => {
    try {
      await axios.delete(`${apiUrl}/api/carts/${cartId}/items`, {
        params: {
          productId,
          quantity: 999999 // Assuming a large number to remove all items
        }
      });
      fetchCart();
    } catch (error) {
      console.error("There was an error removing all items from the cart!", error);
    }
  };

  const getTotalQuantity = () => {
    return cart.items.reduce((total, item) => total + item.quantity, 0);
  };

  if (cart === null) return <div>Loading...</div>;
  if (!cart.items || cart.items.length === 0) return <div>No items in the cart.</div>;

  return (
    <div className="container">
      <h1>Cart</h1>
      <div>Total Items: {getTotalQuantity()}</div>
      <ul className="list-group mb-3">
        {cart.items.map(item => (
          <li key={item.id} className="list-group-item">
            {item.product.productName} - {item.quantity} pcs
            <button onClick={() => removeItemFromCart(item.product.id, 1)} className="btn btn-danger btn-sm float-right ml-2">Remove 1</button>
            <button onClick={() => removeAllFromCart(item.product.id)} className="btn btn-danger btn-sm float-right">Remove All</button>
          </li>
        ))}
      </ul>
      <CartControls addItemToCart={addItemToCart} />
    </div>
  );
}

export default CartPage;