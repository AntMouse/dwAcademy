import React from 'react';
import { Route, Routes } from 'react-router-dom';
import LoginForm from './test-list/Login/LoginForm';
import MainPage from './components/page/MainPage';
import CartList from './components/page/cartlist'; // CartList 컴포넌트 import 경로 수정
import useAuth from './test-list/Hooks/useAuth';
import PriceEdit from './components/page/PriceEdit';
import ProductListPage from './components/page/ProductListPage';
import ProductEditPage from './components/page/ProductEditPage';
import MemberListPage from './components/user-list/MemberListPage';
import MemberEditPage from './components/user-list/MemberEditPage'; // MemberEditPage 컴포넌트 import
import OrderListPage from './components/order/OrderListPage';
import OrderEditPage from './components/order/OrderEditPage';
import ProductReviewList from './components/productreview/ProductReviewList'; // ProductReviewList 컴포넌트 import
import SearchComponent from './components/search/SearchComponent';

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
        <Route path="/edit-product/:id" element={<ProductEditPage />} />
        <Route path="/memberlistpage" element={<MemberListPage />} />
        <Route path="/edit-member/:id" element={<MemberEditPage />} /> {/* MemberEditPage 경로 추가 */}
        <Route path="/orderlistpage" element={<OrderListPage />} />
        <Route path="/order/:id" element={<OrderEditPage />} />
        <Route path="/productreviews" element={<ProductReviewList />} /> {/* ProductReviewList 경로 추가 */}
        <Route path="/search" element={<SearchComponent />} />
      </Routes>
    </div>
  );
}

export default App;
