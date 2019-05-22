import React, { Component } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

class Edit extends Component {

  constructor(props) {
    super(props);
    this.state = {
      invoice: {}
    };
  }

  componentDidMount() {
    axios.get('http://localhost:8080/api/invoices/'+this.props.match.params.id)
      .then(res => {
        this.setState({ invoice: res.data });
        console.log(this.state.invoice);
      });
  }

  onChange = (e) => {
    const state = this.state.invoice
    state[e.target.name] = e.target.value;
    this.setState({invoice:state});
  }

  onSubmit = (e) => {
    e.preventDefault();

    const { number, writingDate, whatCompanyWrote, reciever } = this.state.invoice;

    axios.put('http://localhost:8080/api/invoices/'+this.props.match.params.id, { number, writingDate, whatCompanyWrote, reciever })
      .then((result) => {
        this.props.history.push("/show/"+this.props.match.params.id)
      });
  }

  render() {
    return (
      <div className="container">
        <div className="panel panel-default">
          <div className="panel-heading">
            <h3 className="panel-title">
              EDIT Invoice
            </h3>
          </div>
          <div className="panel-body">
            <h4><Link to={`/show/${this.state.invoice.id}`}><span className="glyphicon glyphicon-eye-open" aria-hidden="true">
            </span> Invoice List</Link></h4>
            <form onSubmit={this.onSubmit}>
              <div className="form-group">
                <label htmlFor="number">Number:</label>
                <input type="text" className="form-control" name="number" value={this.state.invoice.number} onChange={this.onChange} 
                placeholder="Number" />
              </div>
              <div className="form-group">
                <label htmlFor="writingDate">Writing Date:</label>
                <input type="text" className="form-control" name="writingDate" value={this.state.invoice.writingDate} 
                onChange={this.onChange} placeholder="Writing Date" />
              </div>
              <div className="form-group">
                <label htmlFor="whatCompanyWrote">What Company Wrote:</label>
                <input type="text" className="form-control" name="whatCompanyWrote" value={this.state.invoice.whatCompanyWrote} 
                onChange={this.onChange} placeholder="What Company Wrote" />
              </div>
              <div className="form-group">
                <label htmlFor="reciever">Reciever:</label>
                <input type="text" className="form-control" name="reciever" value={this.state.invoice.reciever} 
                onChange={this.onChange} placeholder="Reciever" />
              </div>
              <button type="submit" className="btn btn-default">Update Invoice</button>
            </form>
          </div>
        </div>
      </div>
    );
  }
}

export default Edit;
