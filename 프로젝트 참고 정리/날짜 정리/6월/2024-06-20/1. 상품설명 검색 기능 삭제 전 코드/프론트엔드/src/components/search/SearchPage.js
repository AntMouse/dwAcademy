import React, { useState } from 'react';
import axios from 'axios';
import SearchBar from './SearchBar';
import SearchResults from './SearchResults';

const SearchPage = () => {
  const [results, setResults] = useState([]);
  const [totalResults, setTotalResults] = useState(0);

  const handleSearch = async (searchParams) => {
    try {
      const response = await axios.post('http://localhost:8080/api/search/products', searchParams);
      setResults(response.data.results);
      setTotalResults(response.data.totalResults);
    } catch (error) {
      console.error('Error fetching search results', error);
    }
  };

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
