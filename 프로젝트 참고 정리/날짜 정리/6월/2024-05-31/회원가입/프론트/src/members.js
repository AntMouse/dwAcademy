import React, { useState, useEffect } from 'react';

function MemberList() {
  const [members, setMembers] = useState([]);
  const [editingMember, setEditingMember] = useState(null); // 수정 중인 멤버 정보 저장
  const [editedValues, setEditedValues] = useState({}); // 수정된 값 저장
  const [error, setError] = useState(null); // 오류 메시지 상태 추가

  useEffect(() => {
    fetch('http://localhost:8080/api/members')
      .then(response => {
        if (!response.ok) {
          throw new Error('Failed to fetch members');
        }
        return response.json();
      })
      .then(data => {
        setMembers(data);
      })
      .catch(error => {
        setError(error.message); // 오류 발생 시 오류 메시지 설정
      });
  }, []);

  const handleDelete = id => {
    fetch(`http://localhost:8080/api/members/${id}`, {
      method: 'DELETE'
    })
    .then(response => {
      if (response.ok) {
        setMembers(members.filter(member => member.id !== id));
      } else {
        throw new Error('Failed to delete member');
      }
    })
    .catch(error => {
      setError(error.message); // 오류 발생 시 오류 메시지 설정
    });
  };

  const handleEdit = member => {
    setEditingMember(member);
    setEditedValues(member);
  };

  const handleChange = e => {
    const { name, value } = e.target;
    setEditedValues({
      ...editedValues,
      [name]: value
    });
  };

  const handleSubmit = () => {
    fetch(`http://localhost:8080/api/members/${editingMember.id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(editedValues)
    })
    .then(response => {
      if (response.ok) {
        const updatedMembers = members.map(member =>
          member.id === editingMember.id ? { ...member, ...editedValues } : member
        );
        setMembers(updatedMembers);
        setEditingMember(null);
      } else {
        throw new Error('Failed to update member');
      }
    })
    .catch(error => {
      setError(error.message); // 오류 발생 시 오류 메시지 설정
    });
  };

  return (
    <div>
      <h1>Member List</h1>
      {error && <p>Error: {error}</p>}
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Type</th>
            <th>Name</th>
            <th>Member ID</th>
            <th>Password</th>
            <th>Birthdate</th>
            <th>Gender</th>
            <th>Email</th>
            <th>Contact</th>
            <th>Address</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {members.map(member => (
            <tr key={member.id}>
              <td>{member.id}</td>
              <td>{member.membertype}</td>
              <td>{member.membername}</td>
              <td>{member.memberId}</td>
              <td>{member.password}</td>
              <td>{member.birthdate}</td>
              <td>{member.gender}</td>
              <td>{member.email}</td>
              <td>{member.contact}</td>
              <td>{member.address}</td>
              <td>
                <button onClick={() => handleDelete(member.id)}>Delete</button>
                <button onClick={() => handleEdit(member)}>Edit</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      {editingMember && (
        <div>
          <h2>Edit Member</h2>
          <input type="text" name="membertype" value={editedValues.membertype} onChange={handleChange} />
          <input type="text" name="membername" value={editedValues.membername} onChange={handleChange} />
          <input type="text" name="memberId" value={editedValues.memberId} onChange={handleChange} />
          <input type="text" name="password" value={editedValues.password} onChange={handleChange} />
          <input type="text" name="birthdate" value={editedValues.birthdate} onChange={handleChange} />
          <input type="text" name="gender" value={editedValues.gender} onChange={handleChange} />
          <input type="text" name="email" value={editedValues.email} onChange={handleChange} />
          <input type="text" name="contact" value={editedValues.contact} onChange={handleChange} />
          <input type="text" name="address" value={editedValues.address} onChange={handleChange} />
          <button onClick={handleSubmit}>Submit</button>
        </div>
      )}
    </div>
  );
}

export default MemberList;
