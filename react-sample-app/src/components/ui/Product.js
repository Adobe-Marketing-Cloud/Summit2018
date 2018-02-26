import React, {Component} from 'react';
import PropTypes from 'prop-types';

class Product extends Component {
    handleClick = () => {
        const {id, addToCart, removeFromCart, isInCart} = this.props;
        
            addToCart(id);
        
    }

    render() {
        const {name, price, currency, image, count} = this.props;

        return (
            <div className="product thumbnail">
                <img src={image} alt="product"/>
                <div className="nameDiv">
                    <h4>{name}</h4>
                </div>
                <div className="buttonGroup">
                    <div className="productPrice">{price} {currency}</div>
                    <div className="productButton-wrap">

                        <button
                            className='btn btn-primary btn-lg'
                            onClick={this.handleClick}>
                            Add to cart
                        </button>
                    </div>
                </div>
            </div>
        );
    }
}

Product.propTypes = {
    id: PropTypes.number.isRequired,
    name: PropTypes.string.isRequired,
    price: PropTypes.number,
    currency: PropTypes.string,
    image: PropTypes.string,
    isInCart: PropTypes.bool.isRequired,
    addToCart: PropTypes.func.isRequired,
    removeFromCart: PropTypes.func.isRequired,
}

export default Product;
