import React, { useState } from 'react';
import { Route, Routes } from 'react-router-dom';
import ProductManagement from './components/Product/ProductManagement';
import ProductDetail from './components/Product/ProductDetail';
import CartPage from './components/Cart/CartPage';
import CartListPage from './components/Cart/CartListPage';
import CartNavigation from './components/Navigation/CartNavigation';
import ProductNavigation from './components/Navigation/ProductNavigation';

function App() {
  const [currentCart, setCurrentCart] = useState(null);

  return (
    <div className="container">
      <h1>상품 관리</h1>
      <ProductNavigation />
      <CartNavigation setCurrentCart={setCurrentCart} currentCart={currentCart} />
      <Routes>
        <Route path="/" element={<ProductManagement currentCart={currentCart} />} />
        <Route path="/products/:id" element={<ProductDetail />} />
        <Route path="/cart/:id" element={<CartPage />} />
        <Route path="/cart-list" element={<CartListPage />} />
      </Routes>
    </div>
  );
}

export default App;
