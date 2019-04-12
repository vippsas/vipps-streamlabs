import React from "react";
import ReactDOM from "react-dom";
import {BrowserRouter, Switch, Route} from 'react-router-dom';

import {FallBack} from "./fallBack";
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
                        <Route exact path="/fallBack" component={FallBack}/>
                        <Route exact path="/:streamer" component={Home}/>
                        <Route component={notFound}/>
                    </Switch>
            </div>
        </BrowserRouter>
    );

};

ReactDOM.render(<App />, document.getElementById("root"));