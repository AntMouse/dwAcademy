// SearchPage.js

import React from 'react';
import SearchBar from './SearchBar';
import SearchResults from './SearchResults';
import useSearch from '../../hooks/useSearch';
import './SearchPage.css';

const SearchPage = () => {
  // 검색 결과와 검색 결과 수, 검색 핸들러를 가져옴
  const { results, totalResults, handleSearch } = useSearch();

  return (
    <div>
      <SearchBar onSearch={handleSearch} />
      <div className="search-results-count">검색된 상품 수: {totalResults}</div>
      <SearchResults results={results} />
    </div>
  );
};

export default SearchPage;
