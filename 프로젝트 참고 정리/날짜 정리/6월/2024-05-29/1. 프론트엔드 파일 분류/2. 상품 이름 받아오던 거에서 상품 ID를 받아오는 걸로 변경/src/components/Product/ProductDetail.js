import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

function ProductDetail() {
  const { id } = useParams();
  const [product, setProduct] = useState(null);

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

  if (!product) return <div>로딩 중...</div>;

  return (
    <div className="container">
      <h1>{product.productName}</h1>
      <h3>{product.productType}</h3>
      <p>{product.explanation}</p>
      <p>가격: ${product.price}</p>
    </div>
  );
}

export default ProductDetail;
