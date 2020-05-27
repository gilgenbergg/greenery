import React from "react";
import {Alert} from "react-bootstrap";
import axios from "axios";
import {MDBBtn} from "mdbreact";

export default class NewPlant extends React.Component {

    constructor(props)
    {
        super(props);

        this.state = {
            type: "",
            clientId: null,
            showAlert: false
        };
        this.submitForm = this.submitForm.bind(this);
    }

    submitForm = e => {
        e.preventDefault();
        let newPlantInfo = {};
        if (this.state.type === "" || !this.state.clientId) {
            this.setState({showAlert: true});
            return false;
        }
        else {
            newPlantInfo = {
                type: this.state.type,
                clientId: this.state.clientId
            };
        }
        axios.post(`http://localhost:8081/greenery/plants/add`, newPlantInfo, {
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then (response => {
                window.location.reload();
            })
            .catch(error => {
                console.log("error happend while sending new plant info");
                console.log(error);
            });
    };

    onClose = (e) => {
        this.props.onClose && this.props.created && this.props.onClose(e);
    };

    setType = (e) => {
        this.setState({type: e.target.value});
    };

    setClientId = (e) => {
        this.setState({
            clientId: e.target.value
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
                    Please, fill required fields!
                </Alert> }
                <div>{this.props.children}</div>
                <label htmlFor="type" className="grey-text">
                   Provide client ID
                </label>
                <input type="number" id="clientId" className="form-control"
                       onChange={this.setClientId}/>
                <label htmlFor="type" className="grey-text">
                    Type of plant
                </label>
                <input type="text" id="type" className="form-control"
                       onChange={this.setType}/>
                <br/>
                <MDBBtn color="unique" type="submit" onClick={this.submitForm}>
                    Done!
                </MDBBtn>
            </div>
        );
    }
}