// src/components/productreview/useAuth.js

import { useState, useEffect } from 'react';

// 사용자 인증을 위한 훅
const useAuth = () => {
  const [currentMember, setCurrentMember] = useState(null);

  useEffect(() => {
    const member = JSON.parse(localStorage.getItem('currentMember'));
    console.log("Loaded currentMember from localStorage:", member); // 디버깅용 로그 추가
    if (member) {
      setCurrentMember(member);
    }
  }, []);

  const handleLoginSuccess = (member) => {
    console.log("Received token:", member.token); // 디버깅용 로그 추가
    setCurrentMember(member);
    localStorage.setItem('currentMember', JSON.stringify(member));
    localStorage.setItem('token', member.token); // 로그인 성공 시 JWT 토큰 저장
  };

  const handleLogout = () => {
    setCurrentMember(null);
    localStorage.removeItem('currentMember');
    localStorage.removeItem('token'); // 로그아웃 시 JWT 토큰 제거
  };

  return { currentMember, handleLoginSuccess, handleLogout };
};

export default useAuth;
