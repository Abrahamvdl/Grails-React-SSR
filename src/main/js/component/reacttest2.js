import React from 'react';
import { render } from 'react-dom'
import { renderToString } from 'react-dom/server'

class App01 extends React.Component{
    constructor(){
        super();
        this.state = {
            pow: "pew pew pew"
        };
    }

    render() {
        return (
            <div>
                <p>Pow: {this.props.data.show}</p>
                <p>{this.state.pow}</p>
                <p>This is try 3</p>
            </div>
    );
    }
}

if (typeof window !== 'undefined' && typeof document !== 'undefined' && typeof document.createElement === 'function') {
    window.renderClient = (inData) => {
        const jsData = JSON.parse(inData);
        render (
            <App01 data={jsData}/>,
            document.getElementById ('content')
        );
    }
}
else {
    global.renderServer = (data) => {
        const jsData = JSON.parse(data);
        return renderToString (
            <App01 data={jsData}/>
        )
    };
}
