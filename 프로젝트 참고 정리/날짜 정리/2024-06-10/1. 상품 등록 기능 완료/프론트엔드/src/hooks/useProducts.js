import { useState, useEffect } from 'react';
import axios from 'axios';

const useProducts = () => {
  const [products, setProducts] = useState([]);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/products');
        setProducts(response.data);
      } catch (err) {
        setError('상품을 불러오는 도중 오류가 발생했습니다.');
      }
    };

    fetchProducts();
  }, []);

  return { products, error };
};

export default useProducts;
