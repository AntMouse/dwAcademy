import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import useAuth from '../../components/productreview/useAuth'; // 올바른 경로로 수정

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

const LoginForm = ({ onLoginSuccess }) => {
    const [memberId, setMemberId] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();
    const { currentMember, handleLoginSuccess, handleLogout } = useAuth(); // useAuth 훅에서 필요한 함수와 상태를 가져옵니다.

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post(`${apiUrl}/api/login`, { memberId, password });
            const token = response.data.trim(); // 공백 제거
            console.log("Received token:", token); // 디버깅 로그

            // 토큰을 세션 스토리지에 저장
            sessionStorage.setItem('token', token);

            // 사용자 정보 가져오기
            const userResponse = await axios.get(`${apiUrl}/api/mypage`, {
                headers: {
                    'Authorization': `Bearer ${token}` // Bearer 접두사를 추가합니다.
                }
            });
            const member = {
                ...userResponse.data,
                token: token
            };
            handleLoginSuccess(member); // 로그인 성공 시 상태 업데이트
            onLoginSuccess && onLoginSuccess(member); // 추가된 콜백 호출
            navigate('/main'); // 로그인 성공 후 MainPage로 이동
        } catch (err) {
            console.error("Login error:", err.response ? err.response.data : err.message);
            setError('로그인 실패: ' + (err.response ? err.response.data : err.message));
        }
    };

    return (
        <div>
            {currentMember ? (
                <button onClick={handleLogout}>로그아웃</button>
            ) : (
                <form onSubmit={handleSubmit}>
                    <div>
                        <label htmlFor="memberId">아이디:</label>
                        <input
                            type="text"
                            id="memberId"
                            required
                            autoComplete="username"
                            value={memberId}
                            onChange={(e) => setMemberId(e.target.value)}
                        />
                    </div>
                    <div>
                        <label htmlFor="password">비밀번호:</label>
                        <input
                            type="password"
                            id="password"
                            required
                            autoComplete="current-password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                    </div>
                    {error && <div>{error}</div>}
                    <button type="submit">로그인</button>
                </form>
            )}
        </div>
    );
};

export default LoginForm;
