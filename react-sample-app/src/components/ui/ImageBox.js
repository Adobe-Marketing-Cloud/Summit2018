import {Component} from 'react'

class ImageBox extends Component {

    render() {

        return (
            <div className="card rel clearfix">
                <div className="col-sm-6">
                    <img src="images/bumblebee2.jpg" className="img-responsive"/>
                </div>
                <div className="col-sm-6">
                    <h3 className="topTitle">Bumblebee is an Autobot from the live-action film continuity family.</h3>
                    <p className="paratop">
                        Bumblebee is one of Optimus Prime's most trusted lieutenants. Although he is not the strongest
                        or most powerful of the Autobots, Bumblebee more than makes up for this with a bottomless well
                        of luck, determination and bravery. He would gladly give his life to protect others and stop the
                        Decepticons.</p>
                    <a href="#products"
                       className="link rndbtn square">Buy a Bumbleebee toy</a>
                </div>
            </div>
        )
    }
}

export default ImageBox




