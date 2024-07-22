import React, { useState, useEffect } from 'react';
import api from './api';

const UserTypeUpdate = () => {
  const [users, setUsers] = useState([]);
  const [userTypes, setUserTypes] = useState([]);
  const [selectedTypes, setSelectedTypes] = useState({});

  useEffect(() => {
    // 모든 회원을 가져옴
    const fetchUsers = async () => {
      try {
        const response = await api.get('/api/users');
        setUsers(response.data);
      } catch (error) {
        console.error('Error fetching users', error);
      }
    };

    // 회원 타입을 가져옴
    const fetchUserTypes = async () => {
      try {
        const response = await api.get('/api/users/user-types'); // 백엔드에서 모든 유저 타입을 가져오는 엔드포인트
        setUserTypes(response.data);
      } catch (error) {
        console.error('Error fetching user types', error);
      }
    };

    fetchUsers();
    fetchUserTypes();
  }, []);

  const handleTypeChange = (userId, newType) => {
    setSelectedTypes((prevSelectedTypes) => ({
      ...prevSelectedTypes,
      [userId]: newType,
    }));
  };

  const handleUpdateUserType = async (userId) => {
    try {
      const newType = selectedTypes[userId] || users.find(user => user.id === userId).accountType;
      await api.put(`/api/superadmins/users/${userId}/type`, { accountType: newType });
      alert('User type updated successfully');
    } catch (error) {
      console.error('Error updating user type', error);
      alert('Error updating user type');
    }
  };

  return (
    <div>
      <h2>Update User Type</h2>
      <table>
        <thead>
          <tr>
            <th>User ID</th>
            <th>Username</th>
            <th>Current Type</th>
            <th>Change Type</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {users.map((user) => (
            <tr key={user.id}>
              <td>{user.userId}</td>
              <td>{user.username}</td>
              <td>{user.accountType}</td>
              <td>
                <select
                  value={selectedTypes[user.id] || user.accountType}
                  onChange={(e) => handleTypeChange(user.id, e.target.value)}
                >
                  {userTypes.map((type) => (
                    <option key={type} value={type}>
                      {type}
                    </option>
                  ))}
                </select>
              </td>
              <td>
                <button onClick={() => handleUpdateUserType(user.id)}>확인</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default UserTypeUpdate;
