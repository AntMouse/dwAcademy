import React, { useState, useEffect } from 'react';
import './ProductListPage.css';
import axios from 'axios';
import FilterControls from './FilterControls';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

function ProductListPage() {
  const [products, setProducts] = useState([]);
  const [productTypes, setProductTypes] = useState([]);
  const [selectedParentType, setSelectedParentType] = useState('');
  const [selectedSubType, setSelectedSubType] = useState('');
  const [editProductId, setEditProductId] = useState(null);
  const [editProductData, setEditProductData] = useState(null);
  const [error, setError] = useState('');
  const [showProducts, setShowProducts] = useState(false);
  const [displayedProductCount, setDisplayedProductCount] = useState(0);

  useEffect(() => {
    fetchProductTypes();
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    try {
      const response = await axios.get(`${apiUrl}/api/products`);
      const productsWithMainType = await Promise.all(response.data.map(async (product) => {
        const mainType = await fetchMainType(product.productType);
        return { ...product, mainType };
      }));
      setProducts(productsWithMainType);
      setDisplayedProductCount(productsWithMainType.length);
    } catch (error) {
      setError('상품을 불러오는 도중 오류가 발생했습니다.');
      alert('상품을 불러오는 도중 오류가 발생했습니다.');
    }
  };

  const fetchProductTypes = async () => {
    try {
      const response = await axios.get(`${apiUrl}/api/product-types`);
      setProductTypes(response.data);
    } catch (error) {
      setError('상품 타입을 불러오는 도중 오류가 발생했습니다.');
      alert('상품 타입을 불러오는 도중 오류가 발생했습니다.');
    }
  };

  const fetchMainType = async (subType) => {
    try {
      const response = await axios.get(`${apiUrl}/api/product-types`);
      const productType = response.data.find(pt => pt.productType === subType);
      return productType ? productType.parentType : '알 수 없음';
    } catch (error) {
      console.error('메인 타입을 불러오는 도중 오류가 발생했습니다.', error);
      return '알 수 없음';
    }
  };

  const handleEditClick = (productId) => {
    const productToEdit = products.find(product => product.id === productId);
    setEditProductData({ ...productToEdit });  // 현재 값을 임시 저장
    setEditProductId(productId);
  };

  const handleInputChange = (event, key) => {
    const { value } = event.target;
    setEditProductData(prevState => ({
      ...prevState,
      [key]: value
    }));
  };

  const handleKeyDown = (event) => {
    const { key } = event;
    const isControlKey = key === 'Backspace' || key === 'Delete' || key === 'ArrowLeft' || key === 'ArrowRight' || key === 'Tab';
    if (!/^\d$/.test(key) && !isControlKey) {
      event.preventDefault();
    }
  };

  const handleSaveClick = async () => {
    // 가격이 숫자가 아닌 경우 경고 메시지 표시
    if (isNaN(editProductData.price) || editProductData.price === '') {
      alert('가격 칸에는 숫자만 입력해야 합니다.');
      return;
    }

    try {
      const productToUpdate = { ...editProductData };
      await axios.put(`${apiUrl}/api/products/${editProductId}`, productToUpdate);
      const updatedProducts = products.map(product => 
        product.id === editProductId ? productToUpdate : product
      );
      setProducts(updatedProducts);
      setEditProductId(null);
      setEditProductData(null);  // 임시 저장된 값 초기화
      alert('성공적으로 저장되었습니다.');
    } catch (error) {
      alert('상품 저장 중 오류가 발생했습니다.');
    }
  };

  const handleCancelClick = () => {
    setEditProductId(null);
    setEditProductData(null);  // 임시 저장된 값 초기화
  };

  const handleDeleteClick = async (productId) => {
    if (window.confirm('정말 삭제하시겠습니까?')) {
      try {
        await axios.delete(`${apiUrl}/api/products/${productId}`);
        const updatedProducts = products.filter(product => product.id !== productId);
        setProducts(updatedProducts);
        setDisplayedProductCount(filteredProducts(updatedProducts).length);
        alert('성공적으로 삭제되었습니다.');
      } catch (error) {
        alert('상품 삭제 중 오류가 발생했습니다.');
      }
    }
  };

  const handleParentTypeChange = (event) => {
    setSelectedParentType(event.target.value);
    setSelectedSubType('');
    setShowProducts(false);
  };

  const handleSubTypeChange = (event) => {
    setSelectedSubType(event.target.value);
    setShowProducts(false);
  };

  const handleShowProductsClick = () => {
    setShowProducts(true);
    setDisplayedProductCount(filteredProducts().length);
  };

  const filteredProducts = (productsToFilter = products) => {
    if (selectedParentType === '전체보기') {
      return productsToFilter;
    }
    if (selectedSubType === '전체보기') {
      const subTypes = productTypes
        .filter(pt => pt.parentType === selectedParentType)
        .map(pt => pt.productType);
      return productsToFilter.filter(product => subTypes.includes(product.productType));
    }
    if (selectedSubType) {
      return productsToFilter.filter(product => product.productType === selectedSubType);
    }
    return [];
  };

  const uniqueParentTypes = Array.from(new Set(productTypes.map(item => item.parentType)));

  return (
    <div className="product-list-container">
      <h1>Logo 사진파일 들어갈 예정입니다.(사진이 집컴에 없음)</h1>
      {error && <div className="alert alert-danger">{error}</div>}
      <FilterControls
        productTypes={productTypes}
        selectedParentType={selectedParentType}
        selectedSubType={selectedSubType}
        onParentTypeChange={handleParentTypeChange}
        onSubTypeChange={handleSubTypeChange}
        onShowProductsClick={handleShowProductsClick}
      />
      {showProducts && (
        <>
          <div className="product-count">
            현재 표시된 상품 수: {displayedProductCount}개
          </div>
          <ul className="product-list">
            {filteredProducts().map(product => (
              <li key={product.id} className="product-item">
                <div className="product-field">
                  {editProductId === product.id ? <label>이름</label> : null}
                  {editProductId === product.id ? (
                    <input type="text" className="product-input" value={editProductData.productName} onChange={(e) => handleInputChange(e, 'productName')} />
                  ) : (
                    <div className="product-name">{product.productName}</div>
                  )}
                </div>
                <div className="product-field">
                  {editProductId === product.id ? <label>가격</label> : null}
                  {editProductId === product.id ? (
                    <input type="number" className="product-input" value={editProductData.price} onKeyDown={handleKeyDown} onChange={(e) => handleInputChange(e, 'price')} />
                  ) : (
                    <div className="product-price">가격: {`${product.price.toLocaleString()}원`}</div>
                  )}
                </div>
                <div className="product-field">
                  {editProductId === product.id ? <label>메인 타입</label> : null}
                  {editProductId === product.id ? (
                    <select
                      className="product-input"
                      value={editProductData.mainType}
                      onChange={(e) => handleInputChange(e, 'mainType')}
                    >
                      <option value="">선택하세요</option>
                      {uniqueParentTypes.map((parentType) => (
                        <option key={parentType} value={parentType}>
                          {parentType}
                        </option>
                      ))}
                    </select>
                  ) : (
                    <div className="product-main-type">{`메인 타입: ${product.mainType}`}</div>
                  )}
                </div>
                <div className="product-field">
                  {editProductId === product.id ? <label>서브 타입</label> : null}
                  {editProductId === product.id ? (
                    <select
                      className={`product-input ${!editProductData.mainType ? 'disabled' : ''}`}
                      value={editProductData.productType}
                      onChange={(e) => handleInputChange(e, 'productType')}
                      disabled={!editProductData.mainType}
                    >
                      <option value="">선택하세요</option>
                      {productTypes
                        .filter(subType => subType.parentType === editProductData.mainType)
                        .map(subType => (
                          <option key={subType.productType} value={subType.productType}>
                            {subType.productType}
                          </option>
                        ))}
                    </select>
                  ) : (
                    <div className="product-sub-type">{`서브 타입: ${product.productType}`}</div>
                  )}
                </div>
                <div className="product-field">
                  {editProductId === product.id ? <label>설명</label> : null}
                  {editProductId === product.id ? (
                    <textarea
                      className="product-input product-textarea"
                      value={editProductData.explanation}
                      onChange={(e) => handleInputChange(e, 'explanation')}
                    />
                  ) : (
                    <div className="product-explanation">{`상품설명: ${product.explanation}`}</div>
                  )}
                </div>
                <div className="product-edit-buttons">
                  {editProductId === product.id ? (
                    <>
                      <button onClick={handleSaveClick}>저장</button>
                      <button onClick={handleCancelClick}>취소</button>
                    </>
                  ) : (
                    <>
                      <button onClick={() => handleEditClick(product.id)}>수정</button>
                      <button onClick={() => handleDeleteClick(product.id)}>삭제</button>
                    </>
                  )}
                </div>
              </li>
            ))}
          </ul>
        </>
      )}
    </div>
  );
}

export default ProductListPage;
