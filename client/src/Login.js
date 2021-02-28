import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import jwt from 'jsonwebtoken';
import Cookies from 'js-cookie';
import { login } from './api';
import './stylesheets/Login.css';

const Login = props => {
  const [values, setValues] = useState({ username: '', password: '' });

  const handleChange = e => {
    const { name, value } = e.target;
    setValues({ ...values, [name]: value });
  };

  const onSubmit = async e => {
    e.preventDefault();
    const { username, password } = values;

    if (username.length === 0 || password.length === 0) {
      alert('아이디와 패스워드를 입력해주세요!');
      return;
    }

    const { success, token } = await login(values);

    if (success) {
      Cookies.set('session', token);
      const decoded = jwt.verify(
        token,
        'MyNameIsChoiYeonSukThisIsMySecretKeyWelComeToTheWOrld123456123451',
      );
      console.log(decoded.username);
      props.history.push('/');
    } else {
      alert('아이디 혹은 패스워드가 잘못되었습니다. 확인해주세요!');
    }
  };

  console.log(Cookies.get('session'));

  return (
    <div className="Main">
      <div>
        <h1 className="Logo">
          <Link to="/">연석이의 채팅 서비스</Link>
        </h1>
        <form onSubmit={onSubmit}>
          <div className="InnerBlock">
            <h2>로그인</h2>
            <div className="InputBlock">
              <p>ID</p>
              <input
                className="CustomInput"
                name="username"
                onChange={handleChange}
              />
            </div>
            <div className="InputBlock">
              <p>Password</p>
              <input
                className="CustomInput"
                name="password"
                type="password"
                onChange={handleChange}
              />
            </div>
            <div className="ButtonWrapper">
              <button onClick={onSubmit} type="submit">
                로그인
              </button>
            </div>
            <Link to="/signup" className="ButtonWrapper">
              <button type="button">회원가입</button>
            </Link>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Login;
