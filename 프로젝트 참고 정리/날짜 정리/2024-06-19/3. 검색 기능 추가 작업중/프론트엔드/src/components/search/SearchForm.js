import React, { useState } from 'react';
import axios from 'axios';

const SearchForm = ({ setResults }) => {
    const [dateFilter, setDateFilter] = useState('all');
    const [searchType, setSearchType] = useState('productName');
    const [keyword, setKeyword] = useState('');
    const [category, setCategory] = useState('');

    const handleSearch = async (e) => {
        e.preventDefault();
        const searchRequest = {
            dateFilter,
            searchType,
            keyword,
            category
        };

        try {
            const response = await axios.post('http://localhost:8080/api/search', searchRequest);
            setResults(response.data);
        } catch (error) {
            console.error('Error during search:', error);
        }
    };

    return (
        <form onSubmit={handleSearch}>
            <div>
                <label>Date Filter:</label>
                <select value={dateFilter} onChange={(e) => setDateFilter(e.target.value)}>
                    <option value="all">All</option>
                    <option value="1day">Last 1 Day</option>
                    <option value="1week">Last 1 Week</option>
                    <option value="1month">Last 1 Month</option>
                    <option value="6months">Last 6 Months</option>
                    <option value="1year">Last 1 Year</option>
                </select>
            </div>
            <div>
                <label>Search Type:</label>
                <select value={searchType} onChange={(e) => setSearchType(e.target.value)}>
                    <option value="productName">Product Name</option>
                    <option value="productDescription">Product Description</option>
                    <option value="productNameAndDescription">Product Name + Description</option>
                    <option value="category">Category</option>
                </select>
            </div>
            {searchType === 'category' && (
                <div>
                    <label>Category:</label>
                    <input 
                        type="text" 
                        value={category} 
                        onChange={(e) => setCategory(e.target.value)} 
                    />
                </div>
            )}
            {searchType !== 'category' && (
                <div>
                    <label>Keyword:</label>
                    <input 
                        type="text" 
                        value={keyword} 
                        onChange={(e) => setKeyword(e.target.value)} 
                    />
                </div>
            )}
            <button type="submit">Search</button>
        </form>
    );
};

export default SearchForm;
