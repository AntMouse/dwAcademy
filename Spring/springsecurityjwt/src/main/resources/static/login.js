// 버튼 관련 visible 실행되는 함수
function displayButtons() {
    const jwtToken = getCookie('Authorization'); // 쿠키에서 JWT 토큰 가져오기
    const employeeInfoButton = document.getElementById('employeeInfoButton');
    const loginButton = document.getElementById('loginButton');
    const logoutButton = document.getElementById('logoutButton');
    const signupButton = document.getElementById('signupButton');

    // JWT 토큰이 존재하면 로그인 상태
    if (jwtToken) {
        employeeInfoButton.style.display = "inline";
        logoutButton.style.display = "inline";
    } else {
        loginButton.style.display = "inline";
        signupButton.style.display = "inline";
    }
}

// 쿠키에서 지정된 이름의 쿠키 값을 가져오는 함수
function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}

displayButtons();