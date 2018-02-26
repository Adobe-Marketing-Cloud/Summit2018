import { Component } from 'react'
import ProductList from '../containers/ProductList';
import Footer from '../components/ui/Footer';

class Products extends Component {

    constructor(props) {
        super(props)
    }

    render() {
        return (
            <div className="container">
                <ProductList />
                <Footer/>
            </div>
        )
    }
}

export default Products