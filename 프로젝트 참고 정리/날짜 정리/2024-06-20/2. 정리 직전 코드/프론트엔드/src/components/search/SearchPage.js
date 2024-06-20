// SearchPage.js

import React from 'react';
import SearchBar from './SearchBar';
import SearchResults from './SearchResults';
import useSearch from '../../hooks/useSearch';

const SearchPage = () => {
  const { results, totalResults, handleSearch } = useSearch();

  return (
    <div>
      <h1>Product Search</h1>
      <SearchBar onSearch={handleSearch} />
      <div>Total Results: {totalResults}</div>
      <SearchResults results={results} />
    </div>
  );
};

export default SearchPage;
