import React, { useState } from 'react';
import './PriceEdit.css';
import Logo from '../../assets/Logo.png'; // 로고 이미지 경로에 따라 수정해야 합니다.

const PriceEdit = () => {
  const [product, setProduct] = useState({
    productName: '',
    price: '',
    type: '',
    subType: '',
    imageUrl: '',
    description: ''
  });
  const [successMessage, setSuccessMessage] = useState('');

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setProduct({
      ...product,
      [name]: value
    });
  };

  const handleProductSubmit = (e) => {
    e.preventDefault();
    console.log('등록된 상품:', product);
    setSuccessMessage('성공적으로 등록되었습니다.');
    setProduct({
      productName: '',
      price: '',
      type: '',
      subType: '',
      imageUrl: '',
      description: ''
    });
    setTimeout(() => {
      setSuccessMessage('');
    }, 3000); // 3초 후에 메시지가 자동으로 사라짐
  };

  return (
    <div className="container">
      <div className="price-edit">
        <img src={Logo} alt="로고" className="Admin-logo" />
        <h2>[관리자전용 상품 등록]</h2>
        <form onSubmit={handleProductSubmit}>
          <div className="Adiminform-group">
            <label>상품명</label>
            <input
              type="text"
              name="productName"
              value={product.productName}
              onChange={handleInputChange}
              required
            />
          </div>
          <div className="Adiminform-group">
            <label>가격</label>
            <input
              type="number"
              name="price"
              value={product.price}
              onChange={handleInputChange}
              required
            />
          </div>
          <div className="Adiminform-group">
            <label>타입</label>
            <select
              name="type"
              value={product.type}
              onChange={handleInputChange}
              required
            >
              <option value="">선택하세요</option>
              <option value="타입A">타입 A</option>
              <option value="타입B">타입 B</option>
              <option value="타입C">타입 C</option>
            </select>
          </div>
          {product.type && (
            <div className="Adiminform-group">
              <label>서브타입</label>
              <select
                name="subType"
                value={product.subType}
                onChange={handleInputChange}
              >
                <option value="">선택하세요</option>
                {product.type === "타입A" && (
                  <>
                    <option value="타입A-1">타입 A-1</option>
                    <option value="타입A-2">타입 A-2</option>
                  </>
                )}
                {product.type === "타입B" && (
                  <>
                    <option value="타입B-1">타입 B-1</option>
                    <option value="타입B-2">타입 B-2</option>
                  </>
                )}
                {product.type === "타입C" && (
                  <>
                    <option value="타입C-1">타입 C-1</option>
                    <option value="타입C-2">타입 C-2</option>
                  </>
                )}
              </select>
            </div>
          )}
          <div className="Adiminform-group">
            <label>이미지 URL</label>
            <input
              type="text"
              name="imageUrl"
              value={product.imageUrl}
              onChange={handleInputChange}
              required
            />
          </div>
          <div className="Adiminform-group">
            <label>상품 설명</label>
            <textarea
              name="description"
              value={product.description}
              onChange={handleInputChange}
              required
            />
          </div>
          <button type="submit" className="AdminEdit-button">상품 등록</button>
          {successMessage && <div className="success-message">{successMessage}</div>}
        </form>
      </div>
    </div>
  );
};

export default PriceEdit;
