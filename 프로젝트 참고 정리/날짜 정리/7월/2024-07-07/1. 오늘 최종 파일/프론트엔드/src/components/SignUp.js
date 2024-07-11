import React, { useState, useEffect } from 'react';
import api from './api';

const SignUp = () => {
  const [formData, setFormData] = useState({
    accountId: '',
    username: '',
    password: '',
    confirmPassword: '',
    email: '',
    birthdate: '',
    gender: '', // 기본값 설정
    contact: '',
    street: '',  // 주소 필드 추가
    city: '',    // 주소 필드 추가
    zipCode: '', // 주소 필드 추가
    country: ''  // 주소 필드 추가
  });

  const [genders, setGenders] = useState([]);

  useEffect(() => {
    // 백엔드에서 성별 정보를 가져오는 함수
    const fetchGenders = async () => {
      try {
        const response = await api.get('/api/genders');
        setGenders(response.data);
      } catch (error) {
        console.error('성별 정보를 가져오는 중 오류가 발생했습니다.', error);
      }
    };

    fetchGenders();
  }, []);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
        const data = {
            ...formData,
            type: 'user', // 사용자가 UserDto일 경우 'user'로 설정
            address: {
                street: formData.street,
                city: formData.city,
                zipCode: formData.zipCode,
                country: formData.country
            }
        };
        const response = await api.post('/api/signup', data);
        alert('회원가입 성공!');
        console.log('Response:', response.data);
    } catch (error) {
        alert('회원가입 실패: ' + (error.response?.data || '알 수 없는 오류'));
        console.error('Error:', error);
    }
  };

return (
  <div>
    <h2>회원가입</h2>
    <form onSubmit={handleSubmit}>
      <div>
        <label>사용자 ID: </label>
        <input type="text" name="accountId" value={formData.accountId} onChange={handleChange} required />
      </div>
      <div>
        <label>이름: </label>
        <input type="text" name="username" value={formData.username} onChange={handleChange} required />
      </div>
      <div>
        <label>비밀번호: </label>
        <input type="password" name="password" value={formData.password} onChange={handleChange} required />
      </div>
      <div>
        <label>비밀번호 확인: </label>
        <input type="password" name="confirmPassword" value={formData.confirmPassword} onChange={handleChange} required />
      </div>
      <div>
        <label>이메일: </label>
        <input type="email" name="email" value={formData.email} onChange={handleChange} required />
      </div>
      <div>
        <label>생년월일: </label>
        <input type="date" name="birthdate" value={formData.birthdate} onChange={handleChange} required />
      </div>
      <div>
        <label>성별: </label>
        <select name="gender" value={formData.gender} onChange={handleChange} required>
          <option value="">성별을 선택하세요</option>
          {genders.map((gender) => (
            <option key={gender.code} value={gender.code}>
              {gender.displayName}
            </option>
          ))}
        </select>
      </div>
      <div>
        <label>연락처: </label>
        <input type="text" name="contact" value={formData.contact} onChange={handleChange} required />
      </div>
      <div>
        <label>도로명 주소: </label>
        <input type="text" name="street" value={formData.street} onChange={handleChange} required />
      </div>
      <div>
        <label>시/군/구: </label>
        <input type="text" name="city" value={formData.city} onChange={handleChange} required />
      </div>
      <div>
        <label>우편번호: </label>
        <input type="text" name="zipCode" value={formData.zipCode} onChange={handleChange} required />
      </div>
      <div>
        <label>국가: </label>
        <input type="text" name="country" value={formData.country} onChange={handleChange} required />
      </div>
      <button type="submit">회원가입</button>
    </form>
  </div>
  );
};

export default SignUp;
