import axios from 'axios';
import { SERVER_URL } from '../constants';

export const login = async body => {
  const { data } = await axios
    .post(`${SERVER_URL}/login`, body)
    .catch(err => err.response);

  return data;
};

export const signup = async ({ username, password }) => {
  try {
    const { data } = await axios.post(`${SERVER_URL}/login`, {
      username,
      password,
    });

    return data;
  } catch (err) {
    console.log(Object.keys(err), err.message);
  }
  return null;
};
