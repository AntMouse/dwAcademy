// src/hooks/useCart.js
import { useState, useEffect, useRef } from 'react';
import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

const useCart = (currentMember) => {
  const [items, setItems] = useState([]);
  const [selectAll, setSelectAll] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');
  const timeouts = useRef({});

  useEffect(() => {
    if (currentMember) {
      console.log('Current Member ID:', currentMember.id); // 디버깅용 로그 추가
      fetchCartByMemberId(currentMember.id);
    }
    return () => {
      // 컴포넌트 언마운트 시 모든 타이머 정리
      Object.values(timeouts.current).forEach(clearTimeout);
    };
  }, [currentMember]);

  const fetchCartByMemberId = async (memberId) => {
    try {
      const response = await axios.get(`${apiUrl}/api/carts/member/${memberId}`);
      console.log('Cart Data:', response.data); // 디버깅용 로그 추가
      setItems(response.data.items.map(item => ({
        id: item.product.id,
        name: item.product.productName,
        price: item.product.price,
        image: item.product.imageUrl, // Assuming the image URL is provided by the API
        quantity: item.quantity,
        checked: false,
        warningMessage: '', // 초기 경고 메시지 상태
        warningVisible: false, // 초기 경고 메시지 가시성 상태
      })));
    } catch (error) {
      setErrorMessage('장바구니를 가져오는 도중 오류가 발생했습니다.');
      console.error(error);
    }
  };

  const toggleCheckbox = (id) => {
    setItems(items.map(item =>
      item.id === id ? { ...item, checked: !item.checked } : item
    ));
  };

  const toggleSelectAll = () => {
    const newSelectAll = !selectAll;
    setSelectAll(newSelectAll);
    setItems(items.map(item => ({ ...item, checked: newSelectAll })));
  };

  const increaseQuantity = async (id) => {
    const item = items.find(item => item.id === id);
    if (item.quantity < 99) { // 최대 수량 99로 제한
      const newQuantity = item.quantity + 1;
      try {
        await updateCart(id, newQuantity, 'put');
        setItems(items.map(item =>
          item.id === id ? { ...item, quantity: newQuantity, warningMessage: '', warningVisible: false } : item // checked 상태를 변경하지 않음
        ));
      } catch (error) {
        setErrorMessage('수량을 변경하는 도중 오류가 발생했습니다.');
        console.error(error);
      }
    } else {
      if (!item.warningVisible) {
        showWarningMessage(id, '최대 수량은 99입니다.');
      }
    }
  };

  const decreaseQuantity = async (id) => {
    const item = items.find(item => item.id === id);
    if (item.quantity > 1) {
      const newQuantity = item.quantity - 1;
      try {
        await updateCart(id, newQuantity, 'put');
        setItems(items.map(item =>
          item.id === id ? { ...item, quantity: newQuantity, warningMessage: '', warningVisible: false } : item // checked 상태를 변경하지 않음
        ));
      } catch (error) {
        setErrorMessage('수량을 변경하는 도중 오류가 발생했습니다.');
        console.error(error);
      }
    }
  };

  const handleQuantityChange = async (id, value) => {
    const item = items.find(item => item.id === id);
    const newQuantity = parseInt(value, 10);

    if (isNaN(newQuantity) || newQuantity <= 0) { // 값이 NaN이거나 0 이하이면 1로 설정
      setItems(items.map(item =>
        item.id === id ? { ...item, quantity: 1, warningMessage: '', warningVisible: false } : item
      ));
      try {
        await updateCart(id, 1, 'put');
      } catch (error) {
        setErrorMessage('수량을 변경하는 도중 오류가 발생했습니다.');
        console.error(error);
      }
    } else if (newQuantity <= 99) { // 1 이상 99 이하인 경우
      try {
        await updateCart(id, newQuantity, 'put');
        setItems(items.map(item =>
          item.id === id ? { ...item, quantity: newQuantity, warningMessage: '', warningVisible: false } : item
        ));
      } catch (error) {
        setErrorMessage('수량을 변경하는 도중 오류가 발생했습니다.');
        console.error(error);
      }
    } else {
      if (!item.warningVisible) {
        showWarningMessage(id, '최대 수량은 99입니다.');
      }
    }
  };

  const showWarningMessage = (id, message) => {
    // 기존 타이머가 있으면 제거
    if (timeouts.current[id]) {
      clearTimeout(timeouts.current[id]);
    }

    setItems(items.map(item =>
      item.id === id ? { ...item, warningMessage: message, warningVisible: true } : item
    ));

    // 새로운 타이머 설정
    const timeoutId = setTimeout(() => {
      setItems(currentItems =>
        currentItems.map(item =>
          item.id === id ? { ...item, warningMessage: '', warningVisible: false } : item
        )
      );
      delete timeouts.current[id]; // 타이머 ID 제거
    }, 1500); // 1.5초 후 경고 메시지 제거

    timeouts.current[id] = timeoutId; // 새로운 타이머 ID 저장
  };

  const updateCart = async (productId, quantity, method) => {
    const url = `${apiUrl}/api/carts/${currentMember.id}/items/${productId}`;
    const params = { quantity };
    try {
      await axios({ method, url, params });
    } catch (error) {
      setErrorMessage('장바구니 업데이트 도중 오류가 발생했습니다.');
      console.error(error);
    }
  };

  const removeAllFromCart = async (productId) => {
    try {
      await axios.delete(`${apiUrl}/api/carts/${currentMember.id}/items/${productId}`);
      fetchCartByMemberId(currentMember.id);
    } catch (error) {
      setErrorMessage('상품 삭제 중 오류가 발생했습니다.');
      console.error(error);
    }
  };

  const totalAmount = items.reduce((sum, item) => 
    item.checked ? sum + (item.price * item.quantity) : sum, 0
  );

  const handleCheckout = () => {
    console.log('결제하기 버튼이 클릭되었습니다.');
  };

  return {
    items,
    selectAll,
    errorMessage,
    totalAmount,
    toggleCheckbox,
    toggleSelectAll,
    increaseQuantity,
    decreaseQuantity,
    handleQuantityChange,
    showWarningMessage,
    updateCart,
    removeAllFromCart,
    handleCheckout
  };
};

export default useCart;
