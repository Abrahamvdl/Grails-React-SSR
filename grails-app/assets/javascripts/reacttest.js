var React = require('react.min');
// var createReactClass = require('create-react-class');

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

// class HomeBox extends React.Component{
//     render() {
//         // Console.log('pow');
//         return (
//             React.createElement('div',null,
//                 React.createElement('p',null,"This is a react component."))
//         );
//     }
// }

// console.log('pow');

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

