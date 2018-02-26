import {Component} from 'react'

class MidleImageBox extends Component {

    render() {

        const {title, category, imageSrc, description} = this.props;
        return (
            <div className="card bigpad clearfix mtop">
                <div className="col-sm-4 tophead">
                    <h5 className="smtitle">{category}</h5>
                    <h3>{title}
                    </h3>
                </div>
                <div className="col-sm-4">
                    <img src={imageSrc} className="img-responsive"/>
                </div>
                <div className="col-sm-4 tophead">
                    <p>
                        {description}
                    </p>
                </div>
            </div>
        )
    }
}

export default MidleImageBox




