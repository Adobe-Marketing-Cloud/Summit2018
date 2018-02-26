import MainMenu from '../containers/Menu'

export const Left = ({children}) =>
    <div className="page">
        <MainMenu className="main-menu"/>
        {children}
    </div>

export const Right = ({children}) =>
    <div className="page">
        {children}
        <MainMenu className="main-menu"/>
    </div>

export const Whoops404 = ({location}) =>
    <div>
        <MainMenu className="main-menu"/>
        <h1>Whoops, resource not found</h1>
        <p>Could not find {location.pathname}</p>
    </div>

