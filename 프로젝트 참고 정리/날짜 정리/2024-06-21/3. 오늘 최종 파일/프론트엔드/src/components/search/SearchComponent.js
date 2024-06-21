import React, { useState } from 'react';
import axios from 'axios';

const SearchComponent = () => {
  const [keyword, setKeyword] = useState('');
  const [searchType, setSearchType] = useState('name'); // 기본 검색 타입을 상품명으로 설정
  const [searchResult, setSearchResult] = useState({ results: [], totalResults: 0 });

  // 검색어 입력 핸들러
  const handleKeywordChange = (e) => {
    setKeyword(e.target.value);
  };

  // 검색 타입 변경 핸들러
  const handleSearchTypeChange = (e) => {
    setSearchType(e.target.value);
  };

  // 검색 요청 핸들러
  const handleSearch = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/api/search/${searchType}`, {
        params: { keyword }
      });
      setSearchResult(response.data);
    } catch (error) {
      console.error('검색 중 오류가 발생했습니다:', error);
    }
  };

  return (
    <div>
      <h1>상품 검색</h1>
      <div>
        <input
          type="text"
          placeholder="검색어를 입력하세요"
          value={keyword}
          onChange={handleKeywordChange}
        />
        <select value={searchType} onChange={handleSearchTypeChange}>
          <option value="all">전체보기</option> {/* 추가된 부분 */}
          <option value="name">상품명</option>
          <option value="type">카테고리</option>
        </select>
        <button onClick={handleSearch}>검색</button>
      </div>

      {searchResult.totalResults > 0 && (
        <div>
          <h2>검색 결과 ({searchResult.totalResults}개)</h2>
          <ul>
            {searchResult.results.map((product) => (
              <li key={product.id}>
                <p>상품명: {product.productName}</p>
                <p>가격: {product.price}</p>
                <p>설명: {product.explanation}</p>
                <p>타입: {product.productType}</p>
                <p>이미지: <img src={product.imageUrl} alt={product.productName} /></p>
              </li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
};

export default SearchComponent;
