import React, { useState } from 'react';
import api from './api';

const Login = ({ onLogin }) => {
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
      const response = await api.post('/api/login', formData);
      // const token = response.data.split(' ')[1]; // Bearer 토큰에서 실제 토큰 부분만 추출
      const token = response.data;
      localStorage.setItem('token', token);
      alert('로그인 성공!');
      onLogin(token);
    } catch (error) {
      // alert('로그인 실패: ' + (error.response?.data || '알 수 없는 오류'));
      alert('로그인 실패: ' + (error.response?.data?.message || '알 수 없는 오류'));
      console.error('Error:', error);
    }
  };

  return (
    <div>
      <h2>로그인</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>사용자 ID: </label>
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

export default Login;
