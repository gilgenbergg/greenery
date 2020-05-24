import React from 'react';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link,
    Redirect,
    useHistory,
    useLocation
} from "react-router-dom";
import './App.css';
import LoginComponent from "./LoginComponent";
import RegistrationComponent from "./RegistrationComponent";
import OfficeMainComponent from "./OfficeMainComponent";


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
                                <OfficeMainComponent/>
                            </Route>
                        </Switch>
                    </div>
                </Router>
            </div>
        );
    }
}

export default App;
