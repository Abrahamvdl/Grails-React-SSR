import React from 'react';
import { render } from 'react-dom'
import { renderToString } from 'react-dom/server'
// import ReactDom from 'react-dom';
// import ReactDomServer from 'react-dom/server';

// Console.log('pow0');

class App02 extends React.Component{
    constructor(){
        super();
        this.state = {
            somethingelse: "pam this a totally new thing"
        };
    }

    render() {
        // Console.log('pow');
        return (
            <div>
                <p>This is an entirely new component. </p>
                <p>Pow: {this.props.data.singing}</p>
                <p>{this.state.somethingelse}</p>
                <p>This is try 4</p>
            </div>
        );
    }
}

// ReactDom.render(
{/*<HomeBox/>*/}
// ,document.getElementById("mainColumn"));

// Console.log('pow1');
// let outhtml = ReactDomServer.renderToString(
//     <HomeBox/>
// );
//
// var renderClient = function(data){
//     React.render(
//         <HomeBox data={data}/>,
//         document.getElementById("content")
//     );
// };
//
// var renderServer = function(data){
//     var data = Java.from(data);
//     return React.renderToString(
//         <HomeBox data={data}/>
//     );
// };




if (typeof window !== 'undefined' && typeof document !== 'undefined' && typeof document.createElement === 'function') {
    window.renderClient = (inData) => {
        const jsData = JSON.parse(inData);
        render (
            <App02 data={jsData}/>,
            document.getElementById ('content')
        );
    }
}
else {
    global.renderServer = (data) => {
        const jsData = JSON.parse(data);
        return renderToString (
            <App02 data={jsData}/>
        )
    };
}
