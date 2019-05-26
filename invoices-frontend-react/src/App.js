import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Link } from 'react-router-dom';
import axios from 'axios';

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      invoices: []
    };
  }

  componentDidMount() {
    axios.get('http://localhost:8080/api/invoices/')
      .then(res => {
        this.setState({ invoices: res.data });
        console.log(this.state.invoices);
      });
  }

  render() {
    return (
      <div className="container">
        <div className="panel panel-default">
          <div className="panel-heading">
            <h3 className="panel-title">
              INVOICES LIST
            </h3>
          </div>
          <div className="panel-body">
            <h4><Link to="/create"><span className="glyphicon glyphicon-plus-sign" aria-hidden="true"></span> Add Invoice</Link></h4>
            <table className="table table-stripe">
              <thead>
                <tr>
                  <th>NUMBER</th>
                  <th>WRITING DATE</th>
                </tr>
              </thead>
              <tbody>
                {this.state.invoices.map(c =>
                  <tr key={c.id}>
                    <td><Link to={`/show/${c.id}`}>{c.number}</Link></td>
                    <td>{c.writingDate}</td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    );
  }
}

export default App;
