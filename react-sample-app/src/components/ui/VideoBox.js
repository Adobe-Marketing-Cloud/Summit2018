import {Component} from 'react'

class VideoBox extends Component {

    render() {

        return (
            <div className="rel clearfix">
                <div className="col-sm-3 nopad">
                    <div className="box big videoBlock abs mright animate fadeIn" data-wow-delay="0.5s"
                    >
                        <p>BumbleBee is a life changing experience</p>
                        <div className="mindivdr"></div>
                    </div>
                </div>
                <div className="col-sm-9 top nopad mcol">
                    <div className="playBtn"><img src="images/playBtn.png"/></div>
                    <div className="embed-responsive embed-responsive-16by9">
                        <iframe className="embed-responsive-item" src="https://www.youtube.com/embed/OWRQQjlfrQM">

                        </iframe>
                    </div>
                </div>
            </div>
        )
    }
}

export default VideoBox




