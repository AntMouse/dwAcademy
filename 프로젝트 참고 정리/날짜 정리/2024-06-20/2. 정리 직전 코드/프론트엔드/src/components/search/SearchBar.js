// SearchBar.js

import React, { useState, useEffect } from 'react';
import axios from 'axios';

const SearchBar = ({ onSearch }) => {
  const [searchValue, setSearchValue] = useState('');
  const [searchFilter, setSearchFilter] = useState('all');
  const [productTypes, setProductTypes] = useState([]);
  const [parentTypes, setParentTypes] = useState([]);

  useEffect(() => {
    const fetchProductTypes = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/product-types/enums');
        setProductTypes(response.data);
      } catch (error) {
        console.error('Error fetching product types', error);
      }
    };

    fetchProductTypes();
  }, []);

  useEffect(() => {
    const fetchParentTypes = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/product-types/parents');
        setParentTypes(response.data);
      } catch (error) {
        console.error('Error fetching parent types', error);
      }
    };

    fetchParentTypes();
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();

    let searchParams = { searchValue, searchFilter };

    if (searchFilter === 'category') {
      const matchingTypes = productTypes.filter(
        type => type.displayName.toLowerCase().includes(searchValue.toLowerCase())
      );

      const matchingParentTypes = parentTypes.filter(
        type => type.displayName.toLowerCase().includes(searchValue.toLowerCase())
      );

      if (matchingTypes.length === 0 && matchingParentTypes.length === 0) {
        alert('Invalid category');
        return;
      }

      searchParams.searchValue = searchValue; // 서버가 다중 검색어를 처리하도록 한다.
    }

    onSearch(searchParams);
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Search:</label>
        <input
          type="text"
          value={searchValue}
          onChange={(e) => setSearchValue(e.target.value)}
        />
        <select value={searchFilter} onChange={(e) => setSearchFilter(e.target.value)}>
          <option value="all">전체보기</option>
          <option value="productName">상품명</option>
          <option value="category">카테고리</option>
        </select>
      </div>
      <button type="submit">Search</button>
    </form>
  );
};

export default SearchBar;
