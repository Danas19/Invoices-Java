import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import { Link } from 'react-router-dom';

class Create extends Component {

  constructor() {
    super();
    this.state = {
      number: '',
      writingDate: '',
      whatCompanyWrote: '',
      reciever: ''
    };
  }
  onChange = (e) => {
    const state = this.state
    state[e.target.name] = e.target.value;
    this.setState(state);
  }

  onSubmit = (e) => {
    e.preventDefault();

    const { number, writingDate, whatCompanyWrote, reciever } = this.state;

    axios.post('http://localhost:8080/api/invoices', { number, writingDate, whatCompanyWrote, reciever })
      .then((result) => {
        this.props.history.push("/")
      });
  }

  render() {
    const { number, writingDate, whatCompanyWrote, reciever } = this.state;
    return (
      <div className="container">
        <div className="panel panel-default">
          <div className="panel-heading">
            <h3 className="panel-title">
              ADD Invoice
            </h3>
          </div>
          <div className="panel-body">
            <h4><Link to="/"><span className="glyphicon glyphicon-th-list" aria-hidden="true"></span> Invoices List</Link></h4>
            <form onSubmit={this.onSubmit}>
              <div className="form-group">
                <label htmlFor="number">Number:</label>
                <input type="text" className="form-control" name="number" value={number} onChange={this.onChange} placeholder="Number" />
              </div>
              <div className="form-group">
                <label htmlFor="writingDate">Writing Date:</label>
                <input type="text" className="form-control" name="writingDate" value={writingDate} onChange={this.onChange} placeholder="Example: 2012-07-13" />
              </div>
              <div className="form-group">
                <label htmlFor="whatCompanyWrote">What Company Wrote:</label>
                <input type="text" className="form-control" name="whatCompanyWrote" value={whatCompanyWrote} onChange={this.onChange} placeholder="Company Name" />
              </div>
              <div className="form-group">
                <label htmlFor="reciever">Reciever:</label>
                <input type="text" className="form-control" name="reciever" value={reciever} onChange={this.onChange} placeholder="Reciever" />
              </div>
              <button type="submit" className="btn btn-default">Submit</button>
            </form>
          </div>
        </div>
      </div>
    );
  }
}

export default Create;