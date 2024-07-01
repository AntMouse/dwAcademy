import React from 'react';
import { Route, Routes } from 'react-router-dom';
import LoginForm from './test-list/Login/LoginForm';
import MainPage from './components/page/MainPage';
import CartPage from './test-list/Cart/CartPage'; // CartPage 컴포넌트 import
import useAuth from './test-list/Hooks/useAuth';

function App() {
  const { currentMember, handleLoginSuccess, handleLogout } = useAuth();

  return (
    <div className="container">
      <Routes>
        <Route path="/" element={<LoginForm onLoginSuccess={handleLoginSuccess} currentMember={currentMember} handleLogout={handleLogout} />} />
        <Route path="/login" element={<LoginForm onLoginSuccess={handleLoginSuccess} currentMember={currentMember} handleLogout={handleLogout} />} />
        <Route path="/main" element={<MainPage />} />
        <Route path="/cart" element={<CartPage currentMember={currentMember} />} /> {/* CartPage 경로 추가 */}
      </Routes>
    </div>
  );
}

export default App;
