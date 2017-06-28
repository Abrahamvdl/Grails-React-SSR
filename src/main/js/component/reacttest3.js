import React from 'react';
import { render } from 'react-dom'
import { renderToString } from 'react-dom/server'

class App02 extends React.Component{
    constructor(){
        super();
        this.state = {
            somethingelse: "pam this a totally new thing"
        };
    }

    render() {
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
