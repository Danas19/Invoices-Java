import React, { Component } from 'react'
import ReactDOM from 'react-dom';
import axios from 'axios';
import { Link } from 'react-router-dom'

class AddItem extends Component {
    constructor() {
        super();
        this.state = {
            name: '',
            quantity: '',
            heightCm: '',
            widthCm: '',
            priceEuroCents: ''
        }
    }

    onChange = (e) => {
        const state = this.state
        state[e.target.name] = e.target.value;
        this.setState(state);
    }

    onSubmit = (e) => {
        e.preventDefault();
        
        const { name, quantity, heightCm, widthCm, priceEuroCents } = this.state;
        
        axios.post('http://localhost:8080/api/invoices/'+ this.props.match.params.id+ '/item/', { name, quantity,
        heightCm, widthCm, priceEuroCents })
        .then((result) => {
            
            this.props.history.push("/show/"+this.props.match.params.id)
        });
    }

    render() {
        const { name, quantity, heightCm, widthCm, priceEuroCents } = this.state;
        return (
        <main>
            <h3><Link to="/">Main Page</Link></h3>

            <form onSubmit={this.onSubmit}>
            <input type="text" name="name" value={name} onChange={this.onChange} placeholder="Item name"></input>
            <input type="text" name="quantity" value={quantity} onChange={this.onChange} placeholder="Item quantity"></input>
            <input type="text" name="heightCm" value={heightCm} onChange={this.onChange} placeholder="Item height cm"></input>
            <input type="text" name="widthCm" value={widthCm} onChange={this.onChange} placeholder="Item width cm"></input>
            <input type="text" name="priceEuroCents" value={priceEuroCents} onChange={this.onChange} placeholder="Item price Euro Cents"></input>
            <button type="submit">Add Item</button>
            </form>
        </main>
        );
        
    }
}

export default AddItem;