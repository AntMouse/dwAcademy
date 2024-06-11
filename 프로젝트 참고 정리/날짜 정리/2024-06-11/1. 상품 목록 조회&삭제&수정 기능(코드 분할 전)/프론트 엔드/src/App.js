import React from 'react';
import { Route, Routes } from 'react-router-dom';
import LoginForm from './test-list/Login/LoginForm';
import MainPage from './components/page/MainPage';
import CartList from './components/page/cartlist'; // CartList 컴포넌트 import 경로 수정
import useAuth from './test-list/Hooks/useAuth';
import PriceEdit from './components/page/PriceEdit';
import ProductListPage from './components/page/ProductListPage';

function App() {
  const { currentMember, handleLoginSuccess, handleLogout } = useAuth();

  return (
    <div className="container">
      <Routes>
        <Route path="/" element={<LoginForm onLoginSuccess={handleLoginSuccess} currentMember={currentMember} handleLogout={handleLogout} />} />
        <Route path="/login" element={<LoginForm onLoginSuccess={handleLoginSuccess} currentMember={currentMember} handleLogout={handleLogout} />} />
        <Route path="/main" element={<MainPage />} />
        <Route path="/cart" element={<CartList currentMember={currentMember} />} /> {/* CartList 경로 추가 */}
        <Route path="/priceedit" element={<PriceEdit />} />
        <Route path="/productlistpage" element={<ProductListPage />} />
      </Routes>
    </div>

  );
}

export default App;
