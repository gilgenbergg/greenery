import React from "react";
import {Alert, Button} from "react-bootstrap";
import {ReactRadioButton, ReactRadioButtonsGroup} from "react-radio-buttons-group";
import axios from "axios";
import {MDBBtn} from "mdbreact";
export default class Modal extends React.Component {

    constructor(props)
    {
        super(props);

        this.state = {
            type: "",
            plantId: null,
            showAlert: false
        };
        this.submitForm = this.submitForm.bind(this);
    }

    submitForm = e => {
        e.preventDefault();
        let newCreqInfo = {};
        if (this.state.type === "") {
            this.setState({showAlert: true});
            return false;
        }
        if (this.state.plantId) {
            newCreqInfo = {
                type: this.state.type,
                plantId: this.state.plantId,
                clientId: this.props.clientId,
                status: "inProgress"
            };
        }
        else {
            newCreqInfo = {
                type: this.state.type,
                clientId: this.props.clientId,
                status: "inProgress"
            };
        }
        axios.post(`http://localhost:8081/greenery/creqs/add`, newCreqInfo, {
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then (response => {
                window.location.reload();
            })
            .catch(error => {
                console.log("error happend");
                console.log(error);
            });
    };

    onClose = (e) => {
        this.props.onClose && this.props.created && this.props.onClose(e);
    };

    setType = (value) => {
        this.setState({type: value});
    };

    setPlantId = (e) => {
        this.setState({
            plantId: e.target.value
        })
    };

    closeAlert = () => {
        this.setState({showAlert: false})
    };

    render() {
        if(!this.props.show){
            return null;
        }
        return (
            <div>
                {this.state.showAlert && <Alert variant={"danger"} onClose={this.closeAlert} dismissible>
                    Please, chose the type!
                </Alert> }
                <div>{this.props.children}</div>
                {this.state.created && <div>New request has been created!</div>}
                <div>
                    <ReactRadioButtonsGroup horizontal onChange={this.setType}>
                        <ReactRadioButton value="planned">Planned</ReactRadioButton>
                        <ReactRadioButton value="firstOne">First One</ReactRadioButton>
                    </ReactRadioButtonsGroup>
                </div>
                <label htmlFor="plantId" className="grey-text">
                    Plant ID (if planned)
                </label>
                <input type="number" id="plantId" className="form-control"
                       onChange={this.setPlantId}/>
                <br/>
                <MDBBtn color="unique" type="submit" onClick={this.submitForm}>
                    Done!
                </MDBBtn>
            </div>
        );
    }
}