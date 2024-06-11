import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

function ProductDetail({ currentCart }) {
  const { id } = useParams();
  const [product, setProduct] = useState(null);
  const [quantity, setQuantity] = useState(1);
  const [addItemErrorMessage, setAddItemErrorMessage] = useState('');

  useEffect(() => {
    fetchProduct();
  }, []);

  const fetchProduct = async () => {
    try {
      const response = await axios.get(`${apiUrl}/api/products/${id}`);
      setProduct(response.data);
    } catch (error) {
      console.error("상품 정보를 가져오는 도중 오류가 발생했습니다!", error);
    }
  };

  const addToCart = async () => {
    if (!currentCart) {
      alert('장바구니를 먼저 설정하세요.');
      return;
    }

    try {
      await axios.post(`${apiUrl}/api/carts/${currentCart.id}/items`, null, {
        params: {
          productId: id,
          quantity
        }
      });
      alert('상품이 장바구니에 추가되었습니다.');
    } catch (error) {
      console.error('장바구니에 상품을 추가하는 도중 오류가 발생했습니다!', error);
      setAddItemErrorMessage('상품을 장바구니에 추가하는 도중 오류가 발생했습니다.');
    }
  };

  if (!product) return <div>로딩 중...</div>;

  return (
    <div className="container">
      <h1>{product.productName}</h1>
      <h3>{product.productType}</h3>
      <p>{product.explanation}</p>
      <p>가격: ${product.price}</p>
      <input
        type="number"
        min="1"
        value={quantity}
        onChange={(e) => setQuantity(Number(e.target.value))}
        className="form-control d-inline w-auto mt-2"
      />
      <button onClick={addToCart} className="btn btn-primary btn-sm mt-2">장바구니 추가</button>
      {addItemErrorMessage && <div className="text-danger mt-2">{addItemErrorMessage}</div>}
    </div>
  );
}

export default ProductDetail;
