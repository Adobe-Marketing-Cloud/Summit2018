// Quick-n-dirty polyfill for require
function require(module) {
    if (module === 'react') {
        return React;
    } else if (module === 'react-dom') {
        return ReactDOM;
    }
}