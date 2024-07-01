// src/components/search/SearchComponent.js
import React, { useState } from 'react';

function SearchComponent() {
  const [keyword, setKeyword] = useState('');
  const [results, setResults] = useState([]);
  const [searchType, setSearchType] = useState('name'); // 검색 기준 상태 추가
  const [error, setError] = useState(null); // 에러 상태 추가

  const handleSearch = async (e) => {
    e.preventDefault();
    setError(null); // 이전 에러 상태 초기화
    try {
      const response = await fetch(`http://localhost:8080/api/search?keyword=${keyword}&type=${searchType}`);
      if (!response.ok) {
        // 에러 응답 처리
        const errorText = await response.text();
        setError(errorText);
        return;
      }
      const data = await response.json();
      setResults(data);
    } catch (err) {
      console.error('Error:', err);
      setError('An error occurred while searching.');
    }
  };

  return (
    <div>
      <form onSubmit={handleSearch}>
        <select value={searchType} onChange={(e) => setSearchType(e.target.value)}>
          <option value="name">상품명</option>
          <option value="all">전체보기</option>
          <option value="category">카테고리</option>
        </select>
        <input 
          type="text" 
          value={keyword} 
          onChange={(e) => setKeyword(e.target.value)} 
          placeholder="Search for products..." 
          required 
        />
        <button type="submit">Search</button>
      </form>
      {error && <p style={{color: 'red'}}>{error}</p>} {/* 에러 메시지 표시 */}
      <ul>
        {results.map(result => (
          <li key={result.id}>
            <h2>{result.name}</h2>
            <p>{result.description}</p>
            <p>Price: {result.price}</p>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default SearchComponent;
