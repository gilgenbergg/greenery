import React from 'react';
import { MDBContainer, MDBRow, MDBCol, MDBBtn } from 'mdbreact';
import axios from 'axios';
import {
    Redirect,
} from "react-router-dom";
import {Alert} from "react-bootstrap";
import { ReactRadioButtonsGroup, ReactRadioButton } from 'react-radio-buttons-group';

class RegistrationComponent extends React.Component {
    constructor(props)
    {
        super(props);

        this.state = {
            firstName: "",
            secondName: "",
            login: "",
            password: "",
            role: "",
            authInfo: {},
            valid: true,
            redirect: false,
            showAlert: false
        };

        this.submitForm = this.submitForm.bind(this);

    }

        setFirstName = (e) => {
            this.setState({
                firstName: e.target.value
            })
        };

        setSecondName = (e) => {
            this.setState({
                secondName: e.target.value
            })
        };

        setNewLogin = (e) => {
            this.setState({
                login: e.target.value
            })
        };

        setNewPassword = (e) => {
            this.setState({
                password: e.target.value
            })
        };

        setRole = value => {
            this.setState({role: value});
        };

        renderRedirect = () => {
            if (this.state.redirect) {
                return <Redirect to='/' />
            }
        };

        submitForm = e => {
            e.preventDefault();
            if ((this.state.login === "") || (this.state.password === "") || (this.state.firstName === "") ||
                (this.state.secondName === "") || (this.state.role === "")) {
                console.log(this.state);
                this.setState({valid: false, redirect: false, showAlert: true });
                return false;
            }
            const authInfo = {
                login: this.state.login,
                password: this.state.password
            };
            console.log(authInfo);
            axios.post(`http://localhost:8081/greenery/authData/add`, authInfo, {
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                }
            })
                .then (response => {
                    let id = response.data;
                    console.log("auth data sent!");
                    this.sendUserInfo(id);
                })
                .catch(error => {
                    console.log("error happend");
                    console.log(error);
                });
        };

    sendUserInfo = (id) => {
        const userInfo = {
            firstName: this.state.firstName,
            secondName: this.state.secondName,
            authDataID: id,
            role: this.state.role
        };
        axios.post(`http://localhost:8081/greenery/users/add`, userInfo, {
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then (response => {
                let status = response.data.status;
                console.log("user info sent!");
                console.log(status);
                this.setState({valid: true, redirect: true});
            })
            .catch(error => {
                console.log("error happend");
                console.log(error);
                this.setState({valid: false, redirect: false, showAlert: true});
            });
    };

    closeAlert = () => {
        this.setState({showAlert: false});
    };

        render() {
            return (
                <MDBContainer>
                    {this.state.showAlert &&
                    <Alert variant={"danger"} onClose={this.closeAlert} dismissible>
                        Please, fill all the fields!
                    </Alert> }
                    <MDBRow>
                        <MDBCol md="6">
                            <form>
                                <p className="h4 text-center mb-4">Sign up</p>

                                <label htmlFor="firstName" className="grey-text">
                                    First Name
                                </label>
                                <input type="text" id="firstName" className="form-control"
                                       onChange={this.setFirstName}/>
                                <br/>

                                <label htmlFor="secondName" className="grey-text">
                                    Second Name
                                </label>
                                <input type="text" id="secondName" className="form-control"
                                       onChange={this.setSecondName}/>
                                <br/>

                                <label htmlFor="forLogin" className="grey-text">
                                    Login
                                </label>
                                <input type="text" id="forLogin" className="form-control"
                                       onChange={this.setNewLogin}/>
                                <br/>

                                <label htmlFor="forPass" className="grey-text">
                                    Password
                                </label>
                                <input type="text" id="forPass" className="form-control"
                                       onChange={this.setNewPassword}/>
                                <br/>

                                <ReactRadioButtonsGroup horizontal onChange={this.setRole}>
                                    <ReactRadioButton value="client">Client</ReactRadioButton>
                                    <ReactRadioButton value="admin">Admin</ReactRadioButton>
                                    <ReactRadioButton value="landscaper">Landscaper</ReactRadioButton>
                                </ReactRadioButtonsGroup>

                                <div className="text-center mt-4">
                                    {this.renderRedirect()}
                                        <MDBBtn color="unique" type="submit" onClick={this.submitForm}>
                                            Register
                                        </MDBBtn>
                                </div>
                            </form>
                        </MDBCol>
                    </MDBRow>
                </MDBContainer>
            );
        }
}

export default (RegistrationComponent);