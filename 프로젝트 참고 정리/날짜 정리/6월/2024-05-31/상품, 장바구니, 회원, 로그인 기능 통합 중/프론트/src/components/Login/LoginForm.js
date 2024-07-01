import React, { useState } from 'react';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

const LoginForm = ({ onLoginSuccess }) => {
  const [loginData, setLoginData] = useState({ memberId: '', password: '' });
  const [errorMessage, setErrorMessage] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setLoginData({ ...loginData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(`${apiUrl}/api/login`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(loginData),
      });
      if (!response.ok) {
        const errorText = await response.text();
        throw new Error(errorText);
      }
      const token = await response.text();
      document.cookie = `token=${token}; path=/; max-age=604800; Secure; SameSite=Strict;`;
      const member = { id: loginData.memberId };
      onLoginSuccess(member);
    } catch (error) {
      setErrorMessage(error.message || '로그인 중 오류가 발생했습니다. 다시 시도해주세요.');
      console.error(error.message);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label htmlFor="memberId">회원 ID:</label>
        <input
          type="text"
          id="memberId"
          name="memberId"
          value={loginData.memberId}
          onChange={handleChange}
          required
        />
      </div>
      <div>
        <label htmlFor="password">비밀번호:</label>
        <input
          type="password"
          id="password"
          name="password"
          value={loginData.password}
          onChange={handleChange}
          required
        />
      </div>
      {errorMessage && <div>{errorMessage}</div>}
      <button type="submit">로그인</button>
    </form>
  );
};

export default LoginForm;
