import React from 'react';
import { Link } from 'react-router-dom';
import './stylesheets/Login.css';

const Login = () => {
  return (
    <div className="Main">
      <div>
        <h1 className="Logo">
          <Link to="/">연석이의 채팅 서비스</Link>
        </h1>
        <div className="InnerBlock">
          <h2>로그인</h2>
          <div className="InputBlock">
            <p>ID</p>
            <input className="CustomInput" />
          </div>
          <div className="InputBlock">
            <p>Password</p>
            <input className="CustomInput" type="password" />
          </div>
          <div className="ButtonWrapper">
            <button type="button">로그인</button>
          </div>
          <Link to="/signup" className="ButtonWrapper">
            <button type="button">회원가입</button>
          </Link>
        </div>
      </div>
    </div>
  );
};

export default Login;
