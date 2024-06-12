import React, { useEffect, useState } from 'react';
import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

function MemberListPage({ setCurrentMember }) {
  const [members, setMembers] = useState([]);
  const [errorMessage, setErrorMessage] = useState('');

  useEffect(() => {
    console.log("MemberListPage 컴포넌트 렌더링됨");  // 컴포넌트 렌더링 확인용 콘솔 로그
    const fetchMembers = async () => {
      try {
        const { data } = await axios.get(`${apiUrl}/api/members`);
        if (Array.isArray(data)) {
          setMembers(data);
        } else {
          setErrorMessage('회원 데이터를 가져오는 데 오류가 발생했습니다.');
        }
      } catch (error) {
        setErrorMessage('회원 데이터를 가져오는 도중 오류가 발생했습니다.');
        console.error(error);
      }
    };
    fetchMembers();
  }, []);

  const handleLoginAsMember = (member) => {
    setCurrentMember(member);
    alert(`${member.memberName}님으로 로그인되었습니다.`);
  };

  return (
    <div className="container">
      <h1>회원 목록</h1>
      {errorMessage ? (
        <div className="text-danger">{errorMessage}</div>
      ) : (
        <table className="table table-striped">
          <thead>
            <tr>
              <th>ID</th>
              <th>회원명</th>
              <th>이메일</th>
              <th>동작</th>
            </tr>
          </thead>
          <tbody>
            {members.map(member => (
              <tr key={member.id}>
                <td>{member.id}</td>
                <td>{member.memberName}</td>
                <td>{member.email}</td>
                <td>
                  <button
                    onClick={() => handleLoginAsMember(member)}
                    className="btn btn-primary btn-sm"
                  >
                    로그인
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default MemberListPage;
