// messagesUtils.js

// 에러 메시지를 알림창으로 표시하는 함수
export const showError = (message) => {
    alert(message); // 간단한 에러 알림 예제
  };
  
  // 공통 에러 메시지
  const errorPrefix = "오류 발생: ";
  
  // 에러 메시지 객체
  export const searchErrorMessages = {
    fetchProductTypes: `${errorPrefix}상품 유형을 불러오는 데 실패했습니다.`,
    fetchParentTypes: `${errorPrefix}상위 유형을 불러오는 데 실패했습니다.`,
    generalError: `${errorPrefix}데이터를 불러오는 데 실패했습니다.`
  };
  