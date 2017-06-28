var React = require('react.min');

var HomeBox = React.createClass({
   render:function(){
       console.log(this.props.data);

       var output = "This is a react component with data: " + this.props.data;

       return(
           React.createElement('div',{data:this.props.data},
               React.createElement('p',{data:this.props.data},output))
               // React.createElement('span',{data:this.props.data,this.props.data})])
   );
   }
});

var renderClient = function(data){
    React.render(
        React.createElement(HomeBox, null,null),
        document.getElementById("content")
    );
};

var renderServer = function(data){
    var data = Java.from(data);
    return React.renderToString(
        React.createElement(HomeBox, {data: data},null)
    );
};
