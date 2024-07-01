import React, { useState } from 'react';
import SearchForm from './SearchForm';
import SearchResults from './SearchResults';

const SearchPage = () => {
    const [results, setResults] = useState([]);

    return (
        <div>
            <h1>Search Products</h1>
            <SearchForm setResults={setResults} />
            <SearchResults results={results} />
        </div>
    );
};

export default SearchPage;
