import React from "react";
import ReactDOM from "react-dom";
import {BrowserRouter, Switch, Route} from 'react-router-dom';

import {Fallback} from "./fallback";
import {Home} from "./home";

const notFound = () => {
    return (
        <div>
            <h2>file not found 404</h2>
            <p>
                Oops, couldn't find that!
            </p>
        </div>
    );
};

const App = ()  => {
    return (
        <BrowserRouter>
            <div>
                    <Switch>
                        <Route path="/fallback/:orderId" component={Fallback}/>
                        <Route exact path="/" component={Home}/>
                        <Route component={notFound}/>
                    </Switch>
            </div>
        </BrowserRouter>
    );

};

ReactDOM.render(<App />, document.getElementById("root"));