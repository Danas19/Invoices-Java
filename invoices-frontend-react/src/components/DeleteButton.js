import React, { Component } from 'react';


class DeleteButton extends Component {
    state = {
        value: this.props.value,
        name: this.props.name
    };

    render() {
        return(
            <div>
        <button type="button" className="btn btn-danger">Delete</button>
            </div>
        )
    }
    
}

export default DeleteButton;