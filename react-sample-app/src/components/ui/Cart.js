import React from 'react';
import PropTypes from 'prop-types';
import {Image} from 'react-bootstrap';
import CartItem from './CartItem';

const Cart = ({items, total, currency, removeFromCart}) => {
    return (
        <div className="cart">
            <div className="panel panel-default">

                <div className="panel-body">
                    <h3>Shopping Cart</h3>
                    <div className="cartBanner">
                        {items.length > 0 && (
                            <div className="cart-container">
                                {items.map(item => (
                                    <CartItem key={item.id} {...item} onClick={() => removeFromCart(item.id)}/>
                                ))}
                                <div className="row">
                                    <div className="col-1">
                                    </div>
                                    <div className="col-2">
                                        <p>Total:</p>
                                    </div>
                                    <div className="col-3">
                                        <p>{total} {currency}</p>
                                    </div>
                                </div>
                            </div>
                        )}
                        {items.length === 0 && (
                            <div className="alert alert-info">Your Shopping cart is empty</div>
                        )}
                    </div>
                    <div className="summitImageBanner">
                        <Image src="images/Affiliate_300x600.png" rounded />
                    </div>
                </div>
            </div>
        </div>
    );
}

Cart.propTypes = {
    items: PropTypes.array,
    total: PropTypes.number,
    currency: PropTypes.string,
    removeFromCart: PropTypes.func.isRequired
}

export default Cart;
