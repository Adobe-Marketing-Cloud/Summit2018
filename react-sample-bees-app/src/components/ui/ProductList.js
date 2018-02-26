import React from 'react';
import PropTypes from 'prop-types';
import Product from '../../containers/Product';
import {PageHeader} from 'react-bootstrap';

const ProductList = ({products}) => {
    return (
        <div className="card rel clearfix">
            <PageHeader>
                Buy a transformer toy
                <small>feel like a kid again</small>
            </PageHeader>
            {products.map((product, index) => (
                <div key={index} className="col-sm-4">
                    <Product {...product} />
                </div>
            ))}
        </div>
    );
}

ProductList.propTypes = {
    products: PropTypes.array,
}

export default ProductList;
