import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import SignUp from './components/SignUp';
import Login from './components/Login';
import Logout from './components/Logout';
import Profile from './components/Profile';
import UserTypeUpdate from './components/UserTypeUpdate';
import AdminLogin from './components/AdminLogin';
import api from './components/api';

const App = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [isAdmin, setIsAdmin] = useState(false); // 추가: 관리자 여부 상태

  useEffect(() => {
    const checkTokenValidity = async () => {
      const token = localStorage.getItem('token');
      if (token) {
        try {
          await api.post('/api/token/validate', { token });
          const response = await api.get('/api/user/type', {
            headers: { Authorization: `Bearer ${token}` },
          });
          setIsAdmin(response.data.userType === 'SUPER_ADMIN'); // 추가: 관리자 여부 설정
          setIsLoggedIn(true);
        } catch (error) {
          localStorage.removeItem('token');
          setIsLoggedIn(false);
        }
      }
    };

    checkTokenValidity();
  }, []);

  const handleLogin = (token, isAdmin) => { // 수정: isAdmin 인수 추가
    setIsLoggedIn(true);
    setIsAdmin(isAdmin); // 추가: 관리자 여부 설정
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
    setIsAdmin(false); // 추가: 관리자 여부 리셋
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
              {isAdmin ? ( // 수정: 관리자인 경우 update로 이동
                <>
                  <Route path="/update" element={<UserTypeUpdate />} />
                  <Route path="*" element={<Navigate to="/update" />} />
                </>
              ) : ( // 일반 사용자인 경우 profile로 이동
                <>
                  <Route path="/profile" element={<Profile />} />
                  <Route path="*" element={<Navigate to="/profile" />} />
                </>
              )}
            </Routes>
          </div>
        ) : (
          <Routes>
            <Route path="/signup" element={<SignUp />} />
            <Route path="/login" element={<Login onLogin={handleLogin} />} />
            <Route path="/admin-login" element={<AdminLogin onLogin={handleLogin} />} /> {/* 관리자 로그인 경로 추가 */}
            <Route path="*" element={<Navigate to="/login" />} />
          </Routes>
        )}
      </div>
    </Router>
  );
};

export default App;
