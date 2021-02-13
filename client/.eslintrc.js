module.exports = {
  parser: "babel-eslint",
  parserOptions: {
    sourceType: "module",
  },
  env: { browser: true, es6: true, node: true },
  extends: ['eslint:recomended', 'airbnb', 'plugin:prettier/recommended'],
  rules: {
    'react/jsx-filename-extension': ['error', { extensions: ['.js', '.jsx']}]
  }
};