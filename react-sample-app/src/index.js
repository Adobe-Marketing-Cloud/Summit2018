import React from 'react';
import {render} from 'react-dom';
import Routes from './Routes';
import productsData from './data/products';
import cartReducer from './controllers/cart';
import productsReducer from './controllers/products';
import {Provider} from 'react-redux';
import {combineReducers, createStore} from 'redux';

const rootReducer = combineReducers({
    cart: cartReducer,
    products: productsReducer
});

let store = createStore(
    rootReducer,
    {
        products: productsData // initial store values
    },
    window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__() // for debugging
);

window.React = React

render(
    <Provider store={store}>
        <Routes/>
    </Provider>

    ,
    document.getElementById('react-container'));
