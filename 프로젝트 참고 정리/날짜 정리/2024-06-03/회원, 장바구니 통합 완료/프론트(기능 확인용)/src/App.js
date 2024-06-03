import React from 'react';
import { Route, Routes, Link } from 'react-router-dom';
import ProductManagement from './components/Product/ProductManagement';
import ProductDetail from './components/Product/ProductDetail';
import CartPage from './components/Cart/CartPage';
import Navigation from './components/Navigation/Navigation';
import MemberListPage from './components/Member/MemberListPage';
import CartListPage from './components/Cart/CartListPage';
import LoginForm from './components/Login/LoginForm';
import useAuth from './hooks/useAuth';  // 새로 만든 훅 임포트

function App() {
  const { currentMember, currentCart, setCurrentMember, handleLoginSuccess, handleLogout } = useAuth();

  return (
    <div className="container">
      <h1>상품 관리</h1>
      <Navigation />
      <nav>
        {currentMember ? (
          <button onClick={handleLogout}>로그아웃</button>
        ) : (
          <Link to="/login">로그인</Link>
        )}
      </nav>
      <Routes>
        <Route path="/" element={<ProductManagement currentCart={currentCart} />} />
        <Route path="/products/:id" element={<ProductDetail currentCart={currentCart} />} />
        <Route path="/cart" element={<CartPage currentMember={currentMember} />} />
        <Route path="/cart/:cartId/:memberId" element={<CartPage />} />
        <Route path="/member-list" element={<MemberListPage setCurrentMember={setCurrentMember} />} />
        <Route path="/cart-list" element={<CartListPage />} />
        <Route path="/login" element={<LoginForm onLoginSuccess={handleLoginSuccess} />} />
      </Routes>
    </div>
  );
}

export default App;
