import React from 'react';
import api from './api';

const Logout = ({ onLogout }) => {
  const handleLogout = async () => {
    try {
      const response = await api.post('/api/logout');
      localStorage.removeItem('token');
      alert('로그아웃 성공!');
      onLogout();
    } catch (error) {
      alert('로그아웃 실패: ' + (error.response?.data || '알 수 없는 오류'));
      console.error('Error:', error);
    }
  };

  return (
    <div>
      <button onClick={handleLogout}>로그아웃</button>
    </div>
  );
};

export default Logout;
