import React from 'react';
import PropTypes from 'prop-types';
import {Image} from 'react-bootstrap';

const CartItem = ({name, price, currency, image, onClick}) => {
    return (
        <div className="row">
            <div className="col-1">
                <Image src={image} rounded width="80" height="80"/>
            </div>
            <div className="col-2">
                <p>{name}</p>
                <p>{price} {currency}</p>
            </div>
            <div className="col-3">
                <button className="btn btn-danger btn-xs" onClick={onClick}>X</button>
            </div>
        </div>
    );
}

CartItem.propTypes = {
    name: PropTypes.string.isRequired,
    price: PropTypes.number.isRequired,
    currency: PropTypes.string.isRequired,
    image: PropTypes.string.isRequired,
    onClick: PropTypes.func.isRequired
}

export default CartItem;
