import React from 'react';
import { Link } from 'react-router-dom';
import './stylesheets/Signup.css';

const Signup = () => {
  return (
    <div className="Main">
      <div>
        <h1 className="Logo">
          <Link to="/">연석이의 채팅 서비스</Link>
        </h1>
        <div className="InnerBlock">
          <h2>회원가입</h2>
          <div className="InputBlock">
            <p>ID</p>
            <input className="CustomInput" />
          </div>
          <div className="InputBlock">
            <p>Password</p>
            <input className="CustomInput" type="password" />
          </div>
          <div className="InputBlock">
            <p>Password Check</p>
            <input className="CustomInput" type="password" />
          </div>
          <div className="InputBlock">
            <p>NickName</p>
            <input className="CustomInput" type="password" />
          </div>
          <div className="ButtonWrapper">
            <button type="button">가입하기</button>
          </div>
          <Link to="/login" className="ButtonWrapper">
            <button type="button">로그인 하러 가기</button>
          </Link>
        </div>
      </div>
    </div>
  );
};

export default Signup;
