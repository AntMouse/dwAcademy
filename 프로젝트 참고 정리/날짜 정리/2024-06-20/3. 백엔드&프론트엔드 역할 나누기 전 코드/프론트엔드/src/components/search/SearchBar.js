// SearchBar.js

import React, { useState } from 'react';
import { useFetch } from '../../hooks/useFetch';
import { searchErrorMessages } from '../../utils/messagesUtils';

const SearchBar = ({ onSearch }) => {
  const [searchValue, setSearchValue] = useState('');
  const [searchFilter, setSearchFilter] = useState('all');

  // 상품 유형 데이터와 상위 유형 데이터를 가져옴
  const { data: productTypes } = useFetch('/api/product-types/enums', searchErrorMessages.fetchProductTypes);
  const { data: parentTypes } = useFetch('/api/product-types/parents', searchErrorMessages.fetchParentTypes);

  // 폼 제출 핸들러
  const handleSubmit = (e) => {
    e.preventDefault();

    let searchParams = { searchValue, searchFilter };

    if (searchFilter === 'category') {
      // 카테고리 검색의 경우, 입력된 검색어가 포함된 상품 유형과 상위 유형을 필터링
      const matchingTypes = productTypes.filter(
        type => type.displayName.toLowerCase().includes(searchValue.toLowerCase())
      );

      const matchingParentTypes = parentTypes.filter(
        type => type.displayName.toLowerCase().includes(searchValue.toLowerCase())
      );

      if (matchingTypes.length === 0 && matchingParentTypes.length === 0) {
        alert('유효하지 않은 카테고리입니다.');
        return;
      }

      searchParams.searchValue = searchValue; // 서버가 다중 검색어를 처리하도록 한다.
    }

    onSearch(searchParams);
  };

  return (
    <form onSubmit={handleSubmit} className="search-bar-form">
      <div className="search-bar-container">
        <select value={searchFilter} onChange={(e) => setSearchFilter(e.target.value)} className="search-bar-select">
          <option value="all">전체보기</option>
          <option value="productName">상품명</option>
          <option value="category">카테고리</option>
        </select>
        <input
          type="text"
          value={searchValue}
          onChange={(e) => setSearchValue(e.target.value)}
          placeholder="검색어를 입력하세요"
          className="search-bar-input"
        />
        <button type="submit" className="search-bar-button">검색</button>
      </div>
    </form>
  );
};

export default SearchBar;
