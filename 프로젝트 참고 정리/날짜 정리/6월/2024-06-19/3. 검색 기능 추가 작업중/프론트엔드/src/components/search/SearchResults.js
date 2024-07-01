import React from 'react';

const SearchResults = ({ results }) => {
    return (
        <div>
            <h2>Search Results</h2>
            <ul>
                {results.map((result) => (
                    <li key={result.id}>
                        <h3>{result.productName}</h3>
                        <p>Type: {result.productType}</p>
                        <p>Price: {result.price}</p>
                        <p>{result.explanation}</p>
                        {result.imageUrl && <img src={result.imageUrl} alt={result.productName} />}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default SearchResults;
