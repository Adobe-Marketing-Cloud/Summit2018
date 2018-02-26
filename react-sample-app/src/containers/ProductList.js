import {connect} from 'react-redux';
import ProductList from '../components/ui/ProductList';
import {getProducts} from '../controllers/products';

const mapStateToProps = (state, props) => {
    return {
        products: getProducts(state, props)
    }
}

export default connect(mapStateToProps)(ProductList);
