import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import SignUp from './components/SignUp';
import Login from './components/Login';
import Logout from './components/Logout';
import Profile from './components/Profile'; // 새로 추가된 컴포넌트
import UserTypeUpdate from './components/UserTypeUpdate';
import api from './components/api';

const App = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    const checkTokenValidity = async () => {
      const token = localStorage.getItem('token');
      if (token) {
        try {
          await api.post('/api/token/validate', { token }); // 수정: 토큰을 객체로 감싸서 전송
          setIsLoggedIn(true); // 유효한 토큰일 경우 로그인 상태로 설정
        } catch (error) {
          localStorage.removeItem('token'); // 유효하지 않은 토큰 제거
          setIsLoggedIn(false);
        }
      }
    };

    checkTokenValidity();
  }, []);

  const handleLogin = (token) => {
    setIsLoggedIn(true);
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
    localStorage.removeItem('token');
  };

  return (
    <Router>
      <div>
        {isLoggedIn ? (
          <div>
            <h2>환영합니다!</h2>
            <Logout onLogout={handleLogout} />
            <Routes>
              <Route path="/profile" element={<Profile />} /> {/* Profile 경로 추가 */}
              <Route path="/update-user-type" element={<UserTypeUpdate />} /> {/* UserTypeUpdate 경로 추가 */}
              <Route path="*" element={<Navigate to="/profile" />} /> {/* 잘못된 경로 접근 시 /profile로 리다이렉트 */}
            </Routes>
          </div>
        ) : (
          <Routes>
            <Route path="/signup" element={<SignUp />} />
            <Route path="/login" element={<Login onLogin={handleLogin} />} />
            <Route path="*" element={<Navigate to="/login" />} /> {/* 잘못된 경로 접근 시 /login로 리다이렉트 */}
          </Routes>
        )}
      </div>
    </Router>
  );
};

export default App;
