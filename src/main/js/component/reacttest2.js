import React from 'react';
import { render } from 'react-dom'
import { renderToString } from 'react-dom/server'
// import ReactDom from 'react-dom';
// import ReactDomServer from 'react-dom/server';

// Console.log('pow0');

class App01 extends React.Component{
    constructor(){
        super();
        this.state = {
            pow: "pew pew pew"
        };
    }

    render() {
        // Console.log('pow');
        return (
            <div>
                {/*<p>This is a react component with extra data: {this.props.data}</p>*/}
                <p>Pow: {this.props.data.show}</p>
                <p>{this.state.pow}</p>
                <p>This is try 3</p>
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
// console.log('inside');
    window.renderClient = (inData) => {
        // console.log('now we render');
        // console.log("data: " + inData);
        const jsData = JSON.parse(inData);
        // console.log("jsData: " + jsData);
        render (
            <App01 data={jsData}/>,
            document.getElementById ('content')
        );
    }
}
else {
    global.renderServer = (data) => {
        // console.log ("this is before Java.from");
        // const jsdata = Java.from(data);

        // console.log("data: " + data);
        // console.log("jsdata: " + jsdata);
        const jsData = JSON.parse(data);

        // console.log("jsData: " + jsData);

        return renderToString (
            <App01 data={jsData}/>
        )
    };
}
