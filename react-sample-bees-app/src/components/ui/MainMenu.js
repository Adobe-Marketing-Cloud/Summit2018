import React from 'react'
import {Navbar, Nav, NavItem, Image, Glyphicon} from 'react-bootstrap';
import CartMenu from './CartMenu';

const MainMenu = ({count}) => {
    return (
        <Navbar inverse>
            <Navbar.Header>
                <Navbar.Brand className="navBrand">
                    <Image className="beesImage"/>
                    <a href="#home">Bees Application</a>
                </Navbar.Brand>
            </Navbar.Header>
            <Navbar.Collapse>
                <Nav>
                    <NavItem eventKey={1} href="#home">
                        Home
                    </NavItem>
                    <NavItem eventKey={2} href="#transformers">
                        Transformer Family
                    </NavItem>
                    <NavItem eventKey={3} href="#products">
                        Product List
                    </NavItem>

                </Nav>
                <Nav pullRight>
                    <NavItem eventKey={4} href="#cart">
                        <CartMenu count={count}/>
                    </NavItem>
                </Nav>
            </Navbar.Collapse>
        </Navbar>
    )
}

export default MainMenu