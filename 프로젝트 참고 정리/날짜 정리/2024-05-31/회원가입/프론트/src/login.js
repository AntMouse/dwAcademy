import React, { useState} from 'react';

const LoginForm = () => {
  const [loginData, setLoginData] = useState({
    memberId: '',
    password: ''
  });
  const [errorMessage, setErrorMessage] = useState('');
  const [greeting, setGreeting] = useState('');
  const [userData, setUserData] = useState(null); // 사용자 정보 상태 추가

  const handleChange = (e) => {
    const { name, value } = e.target;
    setLoginData({
      ...loginData,
      [name]: value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch('http://localhost:8080/api/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(loginData)
      });
  
      if (!response.ok) {
        throw new Error('로그인 중 오류가 발생했습니다.');
      }
  
      const Authorization = await response.text();
      const token = Authorization.replace('Bearer ', ''); // "Bearer " 부분을 제거
  
      // Store authorization token in local storage
      localStorage.setItem('Authorization', token);

      // Get greeting message
      const greetingResponse = await fetch('http://localhost:8080/api/hello', {
        headers: {
          Authorization: token // "Bearer" 접두어를 제거하고 토큰만 전달
        }
      });
      const greetingMessage = await greetingResponse.text();
      setGreeting(greetingMessage);

      // Get user data
      const myPageResponse = await fetch('http://localhost:8080/api/mypage', {
        headers: {
          Authorization: token
        }
      });
      const userData = await myPageResponse.json();
      setUserData(userData);
    } catch (error) {
      setErrorMessage('로그인 중 오류가 발생했습니다. 다시 시도해주세요.');
      console.error(error.message);
    }
  };

  return (
    <div>
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
      {greeting && <div>{greeting}</div>}
      {userData && (
        <div>
          <h2>사용자 정보</h2>
          <p>이름: {userData.memberId}</p>
          <p>이메일: {userData.email}</p>
          {/* 필요한 사용자 정보 출력 */}
        </div>
      )}
    </div>
  );
};

export default LoginForm;
