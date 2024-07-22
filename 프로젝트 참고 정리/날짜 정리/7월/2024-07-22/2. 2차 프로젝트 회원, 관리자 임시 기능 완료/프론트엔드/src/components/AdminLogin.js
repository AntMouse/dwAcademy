import React, { useState } from 'react';
import api from './api';

const AdminLogin = ({ onLogin }) => {
  const [formData, setFormData] = useState({
    accountId: '',
    password: '',
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await api.post('/api/admin/login', formData); // 수정된 엔드포인트
      const token = response.data.split(' ')[1]; // Bearer 토큰에서 실제 토큰 부분만 추출
      localStorage.setItem('token', token);

      // 로그인 성공 후 토큰 유효성 확인 및 사용자 유형 확인 로직 추가
      try {
        await api.post('/api/token/validate', { token });
        const userTypeResponse = await api.get('/api/user/type', {
          headers: { Authorization: `Bearer ${token}` },
        });
        const isAdmin = userTypeResponse.data.userType === 'SUPER_ADMIN'; // 관리자 여부 확인
        alert('관리자 로그인 성공!');
        onLogin(token, isAdmin); // 관리자 여부 전달
      } catch (error) {
        localStorage.removeItem('token');
        alert('로그인 실패: 유효하지 않은 토큰');
      }
    } catch (error) {
      alert('로그인 실패: ' + (error.response?.data || '알 수 없는 오류'));
      console.error('Error:', error);
    }
  };

  return (
    <div>
      <h2>관리자 로그인</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>관리자 ID: </label>
          <input type="text" name="accountId" value={formData.accountId} onChange={handleChange} required />
        </div>
        <div>
          <label>비밀번호: </label>
          <input type="password" name="password" value={formData.password} onChange={handleChange} required />
        </div>
        <button type="submit">로그인</button>
      </form>
    </div>
  );
};

export default AdminLogin;
