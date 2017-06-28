# Grails-React-SSR
Small taglib and service implementation to get a React Serverside Rendering working in Grails.

React ServerSide Rendering

We use the Nashorn scripting engine

First we tried to load java script files usign the UDM method that is taking the precompiled React minified files and executing code from there but that does not work because those files inherently call eachother which cannot work in our environment. See https://github.com/facebook/react/issues/8788 for more details.

That same thread suggests that one should use Webpack which combines all the files in the correct way.
The problem then with Webpack is that it uses an anonymous functions thus you cannot get to the code once again.

But by a brilliant hack you can bind a method to global, that you can call later.

Now the problem that we have is that when we load a new react file the global function are set. Which works wonderfully. Call it again with a new react file again works wonderfull, but calling the first one then fails. It returns the result of the second one. Thus the global gets populated and going back does not override the global agian, since it is already loaded.

We cannot remove that section of code from the webpack section, since we really need that. So the goal now is to use the 'loadWithNewGlobal' function of Nashorn, which helps us to create a new global. Thus we want to load a new global, load the current global into the new global and then load the function and execute the global function. Thus everytime a new file needs to be rendered, it is rendered with its own global.

The details of working with load and loadWithNewGlobal obtained from: https://books.google.co.za/books?id=qF8nCgAAQBAJ&pg=PA168&lpg=PA168&dq=loadWithNewGlobal&source=bl&ots=bLHeoOMpp1&sig=pGR1jcllCQJitUJzy8wdha-zUa4&hl=en&sa=X&ved=0ahUKEwi20cfSvNvUAhUGsBQKHanBDRcQ6AEIXTAJ#v=onepage&q=loadWithNewGlobal&f=false

The reason I want to move the global over to the new global is because it takes too much time to load a file, and especially the vendor file that contains all the react code.

But we seem to be struggling to copy the global correctly.

If we use : var global = arguments[0];

 then we get ReferenceError: "webpackJsonp" is not defined
which means that the global is not moved over correctly since we have webpackJsonp defined or atleast the other code works. which means that this file which needs vendor.js cannot be found, thus the global was not copied correctly.

Using: this = arguments[0];

results in: ReferenceError: "{U%}this" can not be used as the left-hand side of assignment
which makes sense.

Using Object.bindProperties(this,arguments[0]); results in:
Caused by
TypeError: Cannot read property "call" from undefined

It seems that the webpack loads wrong somehow. After reading up on it, it seems that it has somethign to do with the CommonsChunkPlugin and maybe the DedupePlugin and also cache: true.
My suspicion lies with CommonsChunk. but removing that, literaly removes all the advantage that I had.

So it seems we are still stuck at square one.

trying to break commons even further apart into common and vendor : nope

It works now that we have disabled the commonsChunk. And is still fast enough that it is not a problem.

Activating the commonsChunk and loading the vendor in the loadWithNewGlobal again breaks with ReferenceError: "webpackJsonp" is not defined

without any vendoring, response is around 300ms - 500ms, which is way way to long for a server to respond. We will try cleaning the code and compling some of it, to see if we can get it below 200ms max

Removing all the println uses we get to about 250ms on average.

Using the minified and optimized version of react we get down to between 100ms and 200ms with the weight to the lower end.

# Starting the project up:

npm install

npm run bundle

./gradlew

or if you have grails installed:

grails run-app


# Other usefull resources used:

Java and Nashorn, with some React
https://github.com/arrwhidev/nashorn-webpack-react-redux-boilerplate
https://synyx.de/blog/springboot-reactjs-server-side-rendering/
https://github.com/synyx/springboot-reactjs-demo
http://winterbe.com/posts/2014/04/05/java8-nashorn-tutorial/
https://medium.com/@jimmy_shen/use-nashorn-engine-to-do-server-side-rendering-with-react-eba835e33d77
https://github.com/shendepu/nashorn-polyfill
http://winterbe.com/posts/2015/02/16/isomorphic-react-webapps-on-the-jvm/

Learning Nashorn
http://www.n-k.de/riding-the-nashorn/

loadWithNewGlobal
https://books.google.co.za/books?id=qF8nCgAAQBAJ&pg=PA168&lpg=PA168&dq=loadWithNewGlobal&source=bl&ots=bLHeoOMpp1&sig=pGR1jcllCQJitUJzy8wdha-zUa4&hl=en&sa=X&ved=0ahUKEwi20cfSvNvUAhUGsBQKHanBDRcQ6AEIXTAJ#v=onepage&q=loadWithNewGlobal&f=false
