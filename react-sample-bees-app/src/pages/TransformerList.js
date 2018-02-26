import {Component} from 'react'
import MidleImageBox from '../components/ui/MidleImageBox'
import transformers from '../data/data'
import Footer from '../components/ui/Footer';


class TransformerList extends Component {

    constructor(props) {
        super(props)
        this.state = {
            transformers: transformers
        }
    }

    render() {
        const {transformers} = this.state
        return (
            <div className="container">
                {
                    transformers.map((transformer, index) => (
                        <MidleImageBox key={index} title={transformer.title}
                                       description={transformer.description}
                                       imageSrc={transformer.imageSrc}
                                       category={transformer.category}/>

                    ))
                }
                <Footer/>
            </div>
        )
    }
}

export default TransformerList