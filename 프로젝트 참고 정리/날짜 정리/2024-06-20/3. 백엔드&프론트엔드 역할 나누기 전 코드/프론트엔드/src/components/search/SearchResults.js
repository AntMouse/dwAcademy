// SearchResults.js

import React from 'react';

// 가격을 천 단위로 쉼표를 추가하여 포맷하는 함수
const formatPrice = (price) => {
  return new Intl.NumberFormat('ko-KR').format(price);
};

const SearchResults = ({ results }) => {
  if (!results.length) {
    return <div>검색 결과가 없습니다.</div>;
  }

  return (
    <div>
      {results.map((product) => (
        <div key={product.id} className="search-results-item">
          <h3>{product.productName}</h3>
          <p>타입: {product.productType}</p>
          <p>가격: {formatPrice(product.price)} 원</p> {/* 가격을 포맷하여 표시 */}
          <p>{product.explanation}</p>
          {product.imageUrl && <img src={product.imageUrl} alt={product.productName} />}
        </div>
      ))}
    </div>
  );
};

export default SearchResults;
