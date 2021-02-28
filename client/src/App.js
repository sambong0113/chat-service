import './stylesheets/App.css';
import React from 'react';
import {
  Route,
  Switch,
  BrowserRouter as Router,
  Redirect,
} from 'react-router-dom';
import Login from './Login';
import Signup from './Signup';
import Home from './Home';
import NotFound from './NotFound';
import { AuthedRoute, UnAuthedRoute } from './AuthRoute';

const App = () => {
  return (
    <Router>
      <main>
        <Switch>
          <Route
            exact
            path="/"
            render={() => {
              return <Redirect to="/home" />;
            }}
          />
          <AuthedRoute exact path="/home" component={Home} />
          <UnAuthedRoute exact path="/login" component={Login} />
          <UnAuthedRoute exact path="/signup" component={Signup} />
          <Route component={NotFound} />
        </Switch>
      </main>
    </Router>
  );
};

export default App;
