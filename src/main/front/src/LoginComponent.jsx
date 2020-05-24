import React from 'react';
import axios from 'axios';
import {
    Link,
    Redirect,
    withRouter
} from "react-router-dom";
import {MDBContainer, MDBRow, MDBCol, MDBInput, MDBBtn} from 'mdbreact';
import {Alert} from "react-bootstrap";

class LoginComponent extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            login: "",
            password: "",
            valid: true,
            redirect: false,
            showAlert: false
        };

        this.onSubmit = this.onSubmit.bind(this);
    }

        setLogin = (e) => {
            this.setState({
                login: e.target.value
            })
        };

        setPassword = (e) => {
            this.setState({
                password: e.target.value
            })
        };

        onSubmit = e => {
            this.checkAuthData();
        };

    renderRedirect = () => {
        if (this.state.redirect) {
            return <Redirect to='/greenery' />
        }
    };

    checkAuthData () {
        if ((this.state.login === "") || (this.state.password === "")) {
            this.setState({valid: false, redirect: false, showAlert: true });
            return false;
        }
        console.log(this.state.password);
        axios.get(`http://localhost:8081/greenery/authData/${this.state.login}`)
            .then(response => {
                const authData = response.data;
                if (authData["password"] === this.state.password) {
                    this.setState({valid: true, redirect: true});
                    return true;
                }
                else {
                    this.setState({valid: false, redirect: false, showAlert: true});
                    return false;
                }
            }).catch(error => {
                console.log(error);
                this.setState({valid: false, redirect: false, showAlert: true});
                return false;
        })
    };

    closeAlert = () => {
        this.setState({showAlert: false});
    };

        render()
        {
            return (
                <MDBContainer>
                    {this.state.showAlert && <Alert variant={"danger"} onClose={this.closeAlert} dismissible>Incorrect input!</Alert> }
                    <MDBRow>
                        <MDBCol md="6">
                            <form>
                                <p className="h5 text-center mb-4">Sign in</p>
                                <div className="grey-text">
                                    <MDBInput label="Type your login" icon="envelope" group type="login" validate
                                              error="wrong"
                                              success="right"
                                                onChange={this.setLogin}/>
                                    <MDBInput label="Type your password" icon="lock" group type="password" validate
                                    onChange={this.setPassword}/>
                                </div>
                                <div className="text-center">
                                    {this.renderRedirect()}
                                    <MDBBtn onClick={this.onSubmit}>Login</MDBBtn>
                                </div>
                                <div className="text-center">
                                    <Link to={"/register"}>
                                        <MDBBtn color="unique">Not registered?</MDBBtn>
                                    </Link>
                                </div>
                            </form>
                        </MDBCol>
                    </MDBRow>
                </MDBContainer>
            )
        }
}

export default withRouter(LoginComponent);