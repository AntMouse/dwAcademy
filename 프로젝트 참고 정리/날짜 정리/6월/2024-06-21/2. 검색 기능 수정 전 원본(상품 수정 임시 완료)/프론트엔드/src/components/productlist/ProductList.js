import React, { useState, useEffect } from 'react';
import axios from 'axios';

const ProductList = () => {
  const [product, setProduct] = useState({
    id: '',
    productType: '',
    productName: '',
    price: '',
    explanation: '',
    imageUrl: '',
  });

  const [productId, setProductId] = useState('');
  const [mainTypes, setMainTypes] = useState([]);
  const [mainType, setMainType] = useState('');
  const [subTypes, setSubTypes] = useState([]);
  const [productTypes, setProductTypes] = useState([]);

  // 모든 메인 타입 정보를 백엔드에서 가져오기
  const fetchMainTypes = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/product-types/main-types');
      setMainTypes(response.data);
    } catch (error) {
      console.error('메인 타입 정보를 가져오는 중 오류가 발생했습니다:', error);
    }
  };

  // 모든 상품 타입 정보를 백엔드에서 가져오기
  const fetchProductTypes = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/product-types');
      setProductTypes(response.data);
    } catch (error) {
      console.error('상품 타입 정보를 가져오는 중 오류가 발생했습니다:', error);
    }
  };

  // 상품 정보를 백엔드에서 가져오기
  const fetchProduct = async (id) => {
    try {
      const response = await axios.get(`http://localhost:8080/api/products/${id}`);
      setProduct(response.data);
      const mainTypeResponse = await axios.get(`http://localhost:8080/api/products/${id}/main-type`);
      setMainType(mainTypeResponse.data);
      fetchSubTypes(mainTypeResponse.data);
    } catch (error) {
      console.error('상품을 가져오는 중 오류가 발생했습니다:', error);
    }
  };

  // 메인 타입에 속한 서브 타입들을 백엔드에서 가져오기
  const fetchSubTypes = async (mainType) => {
    try {
      const response = await axios.get(`http://localhost:8080/api/product-types/sub-types/${mainType}`);
      setSubTypes(response.data);
    } catch (error) {
      console.error('서브 타입 정보를 가져오는 중 오류가 발생했습니다:', error);
    }
  };

  useEffect(() => {
    fetchMainTypes();
    fetchProductTypes();
  }, []);

  useEffect(() => {
    if (productId) {
      fetchProduct(productId);
    }
  }, [productId]);

  // 입력된 값을 상태로 설정
  const handleChange = (e) => {
    const { name, value } = e.target;
    setProduct((prevProduct) => ({
      ...prevProduct,
      [name]: value,
    }));
    if (name === 'mainType') {
      setMainType(value);
      fetchSubTypes(value);
    }
  };

  // 수정된 상품 정보를 백엔드로 전송
  const handleUpdate = async () => {
    try {
      const response = await axios.put(`http://localhost:8080/api/products/${product.id}`, product);
      setProduct(response.data);
      alert('상품이 성공적으로 수정되었습니다.');
    } catch (error) {
      console.error('상품을 수정하는 중 오류가 발생했습니다:', error);
      alert('상품 수정 중 오류가 발생했습니다.');
    }
  };

  return (
    <div>
      <h1>상품 수정</h1>
      <input
        type="text"
        placeholder="상품 ID를 입력하세요"
        value={productId}
        onChange={(e) => setProductId(e.target.value)}
      />
      <button onClick={() => fetchProduct(productId)}>상품 정보 가져오기</button>

      {product.id && (
        <div>
          <div>
            <label>메인 타입: </label>
            <select
              name="mainType"
              value={mainType}
              onChange={handleChange}
            >
              {mainTypes.map((type) => (
                <option key={type} value={type}>
                  {type}
                </option>
              ))}
            </select>
          </div>
          <div>
            <label>상품 타입: </label>
            <select
              name="productType"
              value={product.productType}
              onChange={handleChange}
            >
              {subTypes.map((type) => (
                <option key={type} value={type}>
                  {type}
                </option>
              ))}
            </select>
          </div>
          <div>
            <label>상품 이름: </label>
            <input
              type="text"
              name="productName"
              value={product.productName}
              onChange={handleChange}
            />
          </div>
          <div>
            <label>가격: </label>
            <input
              type="text"
              name="price"
              value={product.price}
              onChange={handleChange}
            />
          </div>
          <div>
            <label>설명: </label>
            <textarea
              name="explanation"
              value={product.explanation}
              onChange={handleChange}
            />
          </div>
          <div>
            <label>이미지 URL: </label>
            <input
              type="text"
              name="imageUrl"
              value={product.imageUrl}
              onChange={handleChange}
            />
          </div>
          <button onClick={handleUpdate}>상품 수정</button>
        </div>
      )}
    </div>
  );
};

export default ProductList;
