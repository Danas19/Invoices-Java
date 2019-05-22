import React, {Component} from 'react';
import axios from 'axios';
axios.defaults.withCredentials = true;

class LoginPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: ''
        }
    }

    onChange = (e) => {
        const state = this.state;
        state[e.target.name] = e.target.value;
        this.setState(state);
    }

    onSubmit = (e) => {
        e.preventDefault();
        let userData = new URLSearchParams();
        userData.append('username', this.state.username);
        userData.append('password', this.state.password);
        axios.post('http://localhost:8080/login', userData,
        {headers:{'Content-type':'application/x-www-form-urlencoded'}})
        .then((response) => {console.log(response);}).catch((ex) =>  { console.log(ex); });
    }

    render() {
        return (
            <div className='container'>
                <form onSubmit={this.onSubmit}>
                    <label>Username: </label>
                    <input type='text' name='username' value={this.state.username} onChange={this.onChange}></input>
                    <label>Password: </label>
                    <input type='password' name='password' value={this.state.password} onChange={this.onChange}></input>
                    <button>Login</button>
                </form>
                
            </div>
        )
    }
}

export default LoginPage;