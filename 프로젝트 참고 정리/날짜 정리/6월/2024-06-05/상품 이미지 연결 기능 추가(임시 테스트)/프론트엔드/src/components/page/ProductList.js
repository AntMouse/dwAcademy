import React from 'react';
import useProducts from '../../hooks/useProducts';
import 'bootstrap/dist/css/bootstrap.min.css';

const ProductList = () => {
  const { products, error } = useProducts();

  return (
    <div className="container mt-5">
      {error && <div className="alert alert-danger">{error}</div>}
      <div className="row">
        {products.map(product => (
          <div className="col-md-4" key={product.id}>
            <div className="card mb-4 shadow-sm">
              <img src={`http://localhost:8080/api/images/${product.productImgUrl}`} className="card-img-top" alt={product.productName} />
              <div className="card-body">
                <h5 className="card-title">{product.productName}</h5>
                <p className="card-text">{product.price.toLocaleString()}원</p>
                <p className="card-text">{product.explanation}</p>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ProductList;
