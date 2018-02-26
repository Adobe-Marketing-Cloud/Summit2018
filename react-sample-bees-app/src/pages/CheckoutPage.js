import {Component} from 'react'
import Cart from '../containers/Cart';
import Footer from '../components/ui/Footer';

class CheckoutPage extends Component {

    constructor(props) {
        super(props)
    }

    render() {
        return (
            <div className="container">
                <Cart />
                <Footer/>
            </div>
        )
    }
}

export default CheckoutPage