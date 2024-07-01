// useSearch.js

import { useState } from 'react';
import axios from 'axios';

const useSearch = () => {
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

  return { results, totalResults, handleSearch };
};

export default useSearch;
