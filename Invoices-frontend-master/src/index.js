import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import App from './App';
import './App.css';
import Edit from './components/Edit';
import Create from './components/Create';
import Show from './components/Show';
import AddItem from './components/AddItem'
import LoginPage from './LoginPage';

ReactDOM.render(
  <Router>
      <div>
        <Route exact path='/' component={App} />
        <Route path='/edit/:id' component={Edit} />
        <Route path='/create' component={Create} />
        <Route path='/show/:id' component={Show} />
        <Route path='/addItem/:id' component={AddItem} />
        <Route path='/login' component={LoginPage} />
      </div>
  </Router>,
  document.getElementById('root')
);