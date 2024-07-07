import React, { useState, useEffect } from 'react'; // useEffect 추가
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import SignUp from './components/SignUp';
import Login from './components/Login';
import Logout from './components/Logout';
import Profile from './components/Profile';
import api from './components/api'; // api 경로가 정확한지 확인 필요

const App = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(!!localStorage.getItem('token'));

  useEffect(() => {
    const checkTokenValidity = async () => {
      const token = localStorage.getItem('token');
      if (token) {
        try {
          await api.get('/api/user/type'); // 서버에 토큰 유효성 검증 요청
          setIsLoggedIn(true);
        } catch (error) {
          localStorage.removeItem('token');
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
  };

  return (
    <Router>
      <div>
        {isLoggedIn ? (
          <div>
            <h2>환영합니다!</h2>
            <Logout onLogout={handleLogout} />
            <Routes>
              <Route path="/profile" element={<Profile />} />
            </Routes>
          </div>
        ) : (
          <Routes>
            <Route path="/signup" element={<SignUp />} />
            <Route path="/login" element={<Login onLogin={handleLogin} />} />
          </Routes>
        )}
      </div>
    </Router>
  );
};

export default App;
