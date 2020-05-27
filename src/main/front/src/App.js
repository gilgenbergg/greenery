import React from 'react';
import {
    BrowserRouter as Router,
    Switch,
    Route
} from "react-router-dom";
import './App.css';
import LoginComponent from "./LoginComponent";
import RegistrationComponent from "./RegistrationComponent";
import PersonalCabinet from "./PersonalCabinet";


class App extends React.Component {
    render() {
        return (
            <div className="App">
                <Router>
                    <div>
                        <Switch>
                            <Route exact path={["/"]}>
                                <LoginComponent/>
                            </Route>
                            <Route path={["/register"]}>
                                <RegistrationComponent/>
                            </Route>
                            <Route path={["/greenery"]}>
                                <PersonalCabinet/>
                            </Route>
                        </Switch>
                    </div>
                </Router>
            </div>
        );
    }
}

export default App;
