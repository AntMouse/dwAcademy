import React, { useState, useEffect } from 'react';
import { Route, Routes, Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import ProductManagement from './components/Product/ProductManagement';
import ProductDetail from './components/Product/ProductDetail';
import CartPage from './components/Cart/CartPage';
import Navigation from './components/Navigation/Navigation';
import MemberListPage from './components/Member/MemberListPage';
import CartListPage from './components/Cart/CartListPage';
import LoginForm from './components/Login/LoginForm';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

function App() {
  const [currentMember, setCurrentMember] = useState(null);
  const [currentCart, setCurrentCart] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    if (currentMember) {
      fetchCart(currentMember.id);
    } else {
      setCurrentCart(null);
    }
  }, [currentMember]);

  const fetchCart = async (memberId) => {
    try {
      const token = localStorage.getItem('token');
      const response = await axios.get(`${apiUrl}/api/carts/member/${memberId}`, {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      });
      setCurrentCart(response.data);
    } catch (error) {
      console.error('장바구니를 가져오는 도중 오류가 발생했습니다!', error);
      setCurrentCart(null);
    }
  };

  const handleLoginSuccess = (member) => {
    setCurrentMember(member);
    navigate('/');
  };

  const handleLogout = () => {
    setCurrentMember(null);
    localStorage.removeItem('token');
    navigate('/login');
  };

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
        <Route path="/cart" element={<CartPage currentMember={currentMember} />} />  {/* 현재 로그인한 사용자의 장바구니 경로 */}
        <Route path="/cart/:cartId/:memberId" element={<CartPage />} />  {/* 다른 사용자의 장바구니 경로 */}
        <Route path="/member-list" element={<MemberListPage setCurrentMember={setCurrentMember} />} />
        <Route path="/cart-list" element={<CartListPage />} />  {/* CartListPage 경로 설정 */}
        <Route path="/login" element={<LoginForm onLoginSuccess={handleLoginSuccess} />} />
      </Routes>
    </div>
  );
}

export default App;
