// messagesUtils.js

// 에러 메시지를 알림창으로 표시하는 함수
export const showError = (message) => {
    alert(message); // 간단한 에러 알림 예제
  };
  
// 공통 에러 메시지
const errorPrefix = "중 오류가 발생했습니다.";

// 에러 메시지 객체
export const searchErrorMessages = {
    getOrders: `검색 정보를 불러오는 ${errorPrefix}`,
    getOrder: `검색 정보를 불러오는 ${errorPrefix}`,
};