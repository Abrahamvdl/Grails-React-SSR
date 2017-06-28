import React from 'react';

class HomeBox extends React.Component{
    constructor(){
        super();
    }

    componentDidMount(){
    }

    render() {
        return (
            <div>
                <p>This is a react component with extra data: ${this.props.data}</p>
            </div>
    );
    }
}

var renderClient = function(data){
    React.render(
        <HomeBox data={data}/>,
        document.getElementById("content")
    );
};

var renderServer = function(data){ //<-- this does not work since it is not bound to global when it is hidden behind the layers of Webpack.
    var data = Java.from(data);
    return React.renderToString(
        <HomeBox data={data}/>
    );
};
