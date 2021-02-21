import './stylesheets/App.css';
import React from 'react';
import { Route, Switch, BrowserRouter as Router } from 'react-router-dom';
import Login from './Login';
import Signup from './Signup';
import Home from './Home';
import NotFound from './NotFound';

function App() {
  return (
    // <div className="App">
    //   <header className="App-header">
    //   </header>
    // </div>
    <Router>
      <main>
        <Switch>
          <Route exact path="/" component={Home} />
          <Route exact path="/login" component={Login} />
          <Route exact path="/signup" component={Signup} />
          <Route component={NotFound} />
        </Switch>
      </main>
    </Router>
  );
}

export default App;
