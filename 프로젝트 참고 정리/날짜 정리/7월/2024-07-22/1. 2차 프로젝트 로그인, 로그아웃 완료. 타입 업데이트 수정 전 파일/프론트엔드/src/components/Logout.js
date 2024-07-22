import React from 'react';
import { useNavigate } from 'react-router-dom'; // useNavigate 추가
import api from './api';

const Logout = ({ onLogout }) => {
  const navigate = useNavigate(); // useNavigate 사용 준비

  const handleLogout = async () => {
    try {
      await api.post('/api/logout');
      localStorage.removeItem('token'); // 수정된 부분: 토큰 삭제
      alert('로그아웃 성공!');
      onLogout();
      navigate('/login'); // 수정된 부분: 로그아웃 후 로그인 페이지로 리다이렉션
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
