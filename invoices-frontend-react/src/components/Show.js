import React, { Component } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import DeleteButton from './DeleteButton';

class Show extends Component {

  constructor(props) {
    super(props);
    this.state = {
      invoice: {},
      items: [],
      invoiceCents: 0,
      invoiceEuros: 0
    };
  }

  

  componentDidMount() {
    axios.get('http://localhost:8080/api/invoices/'+this.props.match.params.id)
      .then(res => {
        this.setState({ invoice: res.data, items: res.data.items, invoiceCents: res.data.cents, invoiceEuros: res.data.euros });
        console.log(this.state.invoice);

        document.getElementById('showInvoiceSumOfMoney').innerHTML = "Euros: " +  this.state.invoiceEuros 
                    + ". Cents: " + this.state.invoiceCents;
      });
  }

  delete(id){
    console.log(id);
    axios.delete('http://localhost:8080/api/invoices/'+id)
      .then((result) => {
        this.props.history.push("/")
      });
  }

  deleteItem(id) {
    console.log('deleting item...');
    axios.delete('http://localhost:8080/api/invoices/delete/item/'+id);
    let items = this.state.items;
    let newItems = items.filter(i => i.id !== id);
      this.setState({items: newItems})

    console.log('item deleted');
  }

  render() {
    return (
      <div className="container show">
        <div className="panel panel-default">
          <div className="panel-heading">
            <h3 className="panel-title">
              Invoices
            </h3>
          </div>
          <div className="panel-body">
            <h4><Link to="/"><span className="glyphicon glyphicon-th-list" aria-hidden="true"></span> Invoices List</Link></h4>
            <dl>
              <dt>Number:</dt>
              <dd>{this.state.invoice.number}</dd>
              <dt>Writing Date:</dt>
              <dd>{this.state.invoice.writingDate}</dd>
              <dt>What Company Wrote:</dt>
              <dd>{this.state.invoice.whatCompanyWrote}</dd>
              <dt>Reciever:</dt>
              <dd>{this.state.invoice.reciever}</dd>
            </dl>
            <Link to={`/edit/${this.state.invoice.id}`} className="btn btn-success">Edit</Link>&nbsp;
            <button onClick={this.delete.bind(this, this.state.invoice.id)} className="btn btn-danger">Delete</button>
          </div>
        </div>

        <div>
          <h3>Items</h3>
          <div>
            <table>
              <thead>
              <tr>
                <th>Name</th>
                <th>Quantity</th>
                <th>Height Cm</th>
                <th>Width Cm</th>
                <th>Price Euro Cents(quantity: 1)</th>
                <th>Total Euro Cents</th>
              </tr>
              </thead>
              <tbody>
                {this.state.items.map(i =>
                  <tr key={i.id}>
                  <td>{i.name}</td>
                  <td>{i.quantity}</td>
                  <td>{i.heightCm}</td>
                  <td>{i.widthCm}</td>
                  <td>{i.priceEuroCents}</td>
                  <td>{i.totalEuroCents}</td>
                  <td><button type="button" className="btn btn-danger" onClick={()=>this.deleteItem(i.id)}>Delete</button></td>
                 
                  </tr>
                  )}
                  
              </tbody>
            </table>
            <h4 id="showInvoiceSumOfMoney"></h4>
            
            <Link to={`/addItem/${this.state.invoice.id}`}>Add New Item</Link>
          </div>
        </div>
      </div>
    );
  }
}

export default Show;
