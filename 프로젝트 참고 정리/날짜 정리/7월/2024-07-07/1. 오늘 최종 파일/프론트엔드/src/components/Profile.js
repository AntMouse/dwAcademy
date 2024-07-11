import React, { useState, useEffect } from 'react';
import api from './api';

const Profile = () => {
  const [user, setUser] = useState(null);

  useEffect(() => {
    const fetchUserProfile = async () => {
      try {
        const response = await api.get('/api/users/mypage');
        setUser(response.data);
      } catch (error) {
        console.error('Error fetching user profile:', error);
      }
    };

    fetchUserProfile();
  }, []);

  if (!user) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <h2>내 정보</h2>
      <p>사용자 ID: {user.userId}</p>
      <p>이름: {user.username}</p>
      <p>이메일: {user.email}</p>
      <p>생년월일: {user.birthdate}</p>
      <p>성별: {user.gender}</p>
      <p>연락처: {user.contact}</p>
    </div>
  );
};

export default Profile;
