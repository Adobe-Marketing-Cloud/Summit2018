import React from 'react'
import {Component} from 'react'
import {Router, Route, hashHistory, Redirect} from 'react-router'
import Home from './pages/Home'
import TransformerList from './pages/TransformerList'
import  {Left, Right, Whoops404} from './components'
import CheckoutPage from './pages/CheckoutPage'
import Products from './pages/Products'

class Routes extends Component {
    render() {
        return (
            <Router history={hashHistory}>
                <Redirect from="/" to="home"></Redirect>
                <Route path="/" component={Left}>
                    <Route path="/home" component={Home}/>
                    <Route path="/transformers" component={TransformerList}/>
                    <Route path="/products" component={Products}/>
                    <Route path="/cart" component={CheckoutPage}/>
                </Route>
                <Route path="*" component={Whoops404}/>
            </Router>

        )
    }
}

export default Routes