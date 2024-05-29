import React from 'react';
import { Link } from 'react-router-dom';

function ProductNavigation() {
  return (
    <nav className="mb-4">
      <Link to="/" className="btn btn-primary mr-2">홈</Link>
      <Link to="/cart-list" className="btn btn-secondary">장바구니 목록</Link>
    </nav>
  );
}

export default ProductNavigation;
