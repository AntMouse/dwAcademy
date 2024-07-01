import React from 'react';
import { Link } from 'react-router-dom';

function ProductList({ products, handleEdit, deleteProduct }) {
  return (
    <div className="row">
      {products.map(product => (
        <div className="col-md-4" key={product.id}>
          <div className="card mb-4">
            <div className="card-body">
              <h5 className="card-title">{product.productName}</h5>
              <h6 className="card-subtitle mb-2 text-muted">{product.productType}</h6>
              <p className="card-text">{product.explanation}</p>
              <p className="card-text">${product.price}</p>
              <Link to={`/products/${product.id}`} className="card-link">View Details</Link>
              <button onClick={() => handleEdit(product)} className="btn btn-secondary btn-sm">Edit</button>
              <button onClick={() => deleteProduct(product.id)} className="btn btn-danger btn-sm">Delete</button>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
}

export default ProductList;