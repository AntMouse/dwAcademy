// messagesUtils.js

// 에러 메시지를 알림창으로 표시하는 함수
export const showError = (message) => {
  alert(message); // 간단한 에러 알림 예제
};

// 공통 에러 메시지
const errorPrefix = "중 오류가 발생했습니다.";

// 에러 메시지 객체
export const orderErrorMessages = {
  getOrders: `주문 목록을 불러오는 ${errorPrefix}`,
  getOrder: `주문 정보를 불러오는 ${errorPrefix}`,
  getOrderStatuses: `주문 상태를 불러오는 ${errorPrefix}`,
  putOrder: `주문을 업데이트하는 ${errorPrefix}`,
  putOrderStatus: `주문 상태를 업데이트하는 ${errorPrefix}`,
  deleteOrder: `주문을 삭제하는 ${errorPrefix}`
};