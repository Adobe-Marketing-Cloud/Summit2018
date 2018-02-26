import {Component} from 'react'
import {Image} from 'react-bootstrap';

class Footer extends Component {

    render() {
        return (
            <footer className="mtop">
                <div className="container inner clearfix">
                    <div className="col-sm-6 col-md-3 col-xs-12">
                        <h5>Contact</h5>
                        <p>
                            <small><b>Address</b></small>
                            <small>NO:876, California</small>
                        </p>
                        <p>
                            <small><b>Email Us</b></small>
                            <small>test@test.com</small>
                        </p>
                        <p>
                            <small><b>Call Us</b></small>
                            <small>test@test.com</small>
                        </p>
                    </div>
                    <div className="col-sm-6 col-md-3 col-xs-12">
                        <h5>Latest News</h5>
                        <div>
                            <div className="media">
                                <div className="media-left">
                                    <a href="http://adobe.com">
                                        <img src="images/square.png" className="media-object img-circle"
                                             alt="Sample Image"/>
                                    </a>
                                </div>
                                <div className="media-body">
                                    <h4 className="media-heading">John Test</h4>
                                    <small><i>Posted on February 13, 2018</i></small>
                                </div>
                            </div>
                        </div>
                        <div>
                            <div className="media">
                                <div className="media-left">
                                    <a href="http://adobe.com">
                                        <img src="images/square.png" className="media-object img-circle"
                                             alt="Sample Image"/>
                                    </a>
                                </div>
                                <div className="media-body">
                                    <h4 className="media-heading">John Test</h4>
                                    <small><i>Posted on February 17, 2018</i></small>
                                </div>
                            </div>
                        </div>
                        <div>
                            <div className="media">
                                <div className="media-left">
                                    <a href="http://adobe.com">
                                        <img src="images/square.png" className="media-object img-circle"
                                             alt="Sample Image"/>
                                    </a>
                                </div>
                                <div className="media-body">
                                    <h4 className="media-heading">John Test</h4>
                                    <small><i>Posted on February 18, 2018</i></small>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <Image src="images/Affiliate_728x90.png" width="100%"/>
                <div className="subfooter">
                    Copyright @2018
                </div>
            </footer>
        )
    }
}

export default Footer