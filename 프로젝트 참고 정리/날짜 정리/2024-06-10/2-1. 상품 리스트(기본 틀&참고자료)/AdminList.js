import React, { useState, useEffect } from 'react';
import './AdminList.css';

function ProductListPage() {
  const [products, setProducts] = useState([]);
  const [editProductId, setEditProductId] = useState(null);

  const fetchProducts = async () => {
    try {
      const data = [
        { id: 1, name: 'Product 1', price: 10000, type: 'A', subtype: 'A-1', explanation: "A-1 상품설명입니다."},
        { id: 2, name: 'Product 2', price: 20000, type: 'B', subtype: 'B-1', explanation: "B-1 상품설명입니다."},
        { id: 3, name: 'Product 3', price: 30000, type: 'C', subtype: 'C-1', explanation: "C-1 상품설명입니다."}
      ];
      setProducts(data);
    } catch (error) {
      console.error('Error fetching products:', error);
    }
  };

  useEffect(() => {
    fetchProducts();
  }, []);

  const handleEditClick = (productId) => {
    setEditProductId(productId);
  };

  const handleInputChange = (event, productId, key) => {
    const updatedProducts = products.map(product => {
      if (product.id === productId) {
        return {
          ...product,
          [key]: event.target.value
        };
      }
      return product;
    });
    setProducts(updatedProducts);
  };

  const handleSaveClick = (productId) => {
    // 여기에서 수정된 상품 정보를 서버로 전송하는 로직을 추가할 수 있습니다.
    // 예: fetch('API_URL', {
    //        method: 'PUT',
    //        body: JSON.stringify(products.find(product => product.id === productId)),
    //        headers: {
    //          'Content-Type': 'application/json'
    //        }
    //      });
    setEditProductId(null);
  };

  return (
    <div className="product-list-container">
      <h1>Logo 사진파일 들어갈예정입니다.(사진이 집컴에 없음)</h1>
      <ul className="product-list">
        {products.map(product => (
          <li key={product.id} className="product-item">
            <div className="product-name">
              {editProductId === product.id ? (
                <input type="text" value={product.name} onChange={(e) => handleInputChange(e, product.id, 'name')} />
              ) : (
                product.name
              )}
            </div>
            <div className="product-price">
              {editProductId === product.id ? (
                <input type="text" value={product.price} onChange={(e) => handleInputChange(e, product.id, 'price')} />
              ) : (
                `${product.price}원`
              )}
            </div>
            <div className="product-type">
              {editProductId === product.id ? (
                <input type="text" value={product.type} onChange={(e) => handleInputChange(e, product.id, 'type')} />
              ) : (
                `타입: ${product.type}`
              )}
            </div>
            <div className="product-subtype">
              {editProductId === product.id ? (
                <input type="text" value={product.subtype} onChange={(e) => handleInputChange(e, product.id, 'subtype')} />
              ) : (
                `서브타입: ${product.subtype}`
              )}
            </div>
            <div className="product-explanation">
              {editProductId === product.id ? (
                <input type="text" value={product.explanation} onChange={(e) => handleInputChange(e, product.id, 'explanation')} />
              ) : (
                `상품설명: ${product.explanation}`
              )}
            </div>
            <div className="product-edit-buttons">
              {editProductId === product.id ? (
                <button onClick={() => handleSaveClick(product.id)}>저장</button>
              ) : (
                <button onClick={() => handleEditClick(product.id)}>수정</button>
              )}
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default ProductListPage;
