import React from 'react'
import {Glyphicon} from 'react-bootstrap';

const CartMenu = ({count}) => {
    return (
        <div>
        <Glyphicon glyph="glyphicon glyphicon-shopping-cart"/>
             Cart 
            {count>0 ?
            <span id="itemCount">{count}</span> : "" }
            </div>
    
    )
}

export default CartMenu