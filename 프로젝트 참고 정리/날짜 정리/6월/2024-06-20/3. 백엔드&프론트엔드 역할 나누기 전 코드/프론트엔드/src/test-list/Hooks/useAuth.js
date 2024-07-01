import { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

const useAuth = () => {
  const [currentMember, setCurrentMember] = useState(() => {
    const member = sessionStorage.getItem('currentMember');
    return member ? JSON.parse(member) : null;
  });
  const [currentCart, setCurrentCart] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    if (currentMember) {
      fetchCart(currentMember.id);
    } else {
      setCurrentCart(null);
    }
  }, [currentMember]);

  const fetchCart = async (memberId) => {
    try {
      const token = sessionStorage.getItem('token');
      const response = await axios.get(`${apiUrl}/api/carts/member/${memberId}`, {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      });
      setCurrentCart(response.data);
    } catch (error) {
      console.error('장바구니를 가져오는 도중 오류가 발생했습니다!', error);
      setCurrentCart(null);
    }
  };

  const handleLoginSuccess = (member) => {
    setCurrentMember(member);
    sessionStorage.setItem('currentMember', JSON.stringify(member));
    navigate('/main'); // 로그인 성공 후 MainPage로 이동
  };

  const handleLogout = () => {
    setCurrentMember(null);
    sessionStorage.removeItem('currentMember');
    sessionStorage.removeItem('token');
    navigate('/login');
  };

  return {
    currentMember,
    currentCart,
    setCurrentMember,
    handleLoginSuccess,
    handleLogout,
  };
};

export default useAuth;
