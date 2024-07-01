import { useState, useEffect } from 'react';
import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

const useProducts = () => {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await axios.get(`${apiUrl}/api/products`);
        setProducts(response.data);
      } catch (err) {
        alert('상품을 불러오는 도중 오류가 발생했습니다.');
      }
    };

    fetchProducts();
  }, []);

  const deleteProduct = async (id) => {
    try {
      await axios.delete(`${apiUrl}/api/products/${id}`);
      setProducts(products.filter(product => product.id !== id));
    } catch (err) {
      alert('상품을 삭제하는 도중 오류가 발생했습니다.');
    }
  };

  return { products, deleteProduct };
};

export default useProducts;
