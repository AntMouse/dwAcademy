import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

function ProductList({ products, handleEdit, deleteProduct, currentCart }) {
  const [quantities, setQuantities] = useState({});

  const handleQuantityChange = (productId, value) => {
    setQuantities(prevQuantities => ({
      ...prevQuantities,
      [productId]: value,
    }));
  };

  const addToCart = async (product) => {
    if (!currentCart) {
      alert('장바구니를 먼저 설정하세요.');
      return;
    }

    const quantity = quantities[product.id] || 1;

    try {
      await axios.post(`${apiUrl}/api/carts/${currentCart.id}/items`, null, {
        params: { productId: product.id, quantity }
      });
      alert('상품이 장바구니에 추가되었습니다.');
    } catch (error) {
      console.error('장바구니에 상품을 추가하는 도중 오류가 발생했습니다!', error);
      alert('상품을 장바구니에 추가하는 도중 오류가 발생했습니다.');
    }
  };

  return (
    <div className="row">
      {products.map(product => (
        <div className="col-md-4" key={product.id}>
          <div className="card mb-4">
            <div className="card-body">
              <h5 className="card-title">{product.productName}</h5>
              <h6 className="card-subtitle mb-2 text-muted">{product.productType}</h6>
              <p className="card-text">{product.explanation}</p>
              <p className="card-text">${product.price}</p>
              <Link to={`/products/${product.id}`} className="card-link">상세 보기</Link>
              <button onClick={() => handleEdit(product)} className="btn btn-secondary btn-sm">수정</button>
              <button onClick={() => deleteProduct(product.id)} className="btn btn-danger btn-sm">삭제</button>
              <input
                type="number"
                min="1"
                value={quantities[product.id] || 1}
                onChange={(e) => handleQuantityChange(product.id, e.target.value)}
                className="form-control d-inline w-auto mt-2"
              />
              <button onClick={() => addToCart(product)} className="btn btn-primary btn-sm mt-2">장바구니 추가</button>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
}

export default ProductList;
