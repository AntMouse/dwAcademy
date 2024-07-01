// SearchResults.js

import React from 'react';

const SearchResults = ({ results }) => {
  if (!results.length) {
    return <div>No products found.</div>;
  }

  return (
    <div>
      {results.map((product) => (
        <div key={product.id}>
          <h3>{product.productName}</h3>
          <p>Type: {product.productType}</p>
          <p>Price: ${product.price}</p>
          <p>{product.explanation}</p>
          {product.imageUrl && <img src={product.imageUrl} alt={product.productName} />}
        </div>
      ))}
    </div>
  );
};

export default SearchResults;
