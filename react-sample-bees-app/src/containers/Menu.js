import {connect} from 'react-redux';
import MainMenu from '../components/ui/MainMenu';
import {getCount} from '../controllers/cart';

const mapStateToProps = (state, props) => {
    return {
        count: getCount(state, props)
    }
}

export default connect(mapStateToProps)(MainMenu);
