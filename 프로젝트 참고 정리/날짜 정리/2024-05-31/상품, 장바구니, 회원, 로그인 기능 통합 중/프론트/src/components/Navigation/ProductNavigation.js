import React from 'react';
import { Link } from 'react-router-dom';

function ProductNavigation() {
  return (
    <nav className="mb-4">
      <Link to="/" className="btn btn-primary mr-2">홈</Link>
      <Link to="/cart-list" className="btn btn-secondary mr-2">장바구니 목록</Link>
      <Link to="/members" className="btn btn-secondary">회원 목록</Link>
    </nav>
  );
}

export default ProductNavigation;
