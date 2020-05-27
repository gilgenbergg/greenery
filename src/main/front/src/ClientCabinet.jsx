import React from 'react';
import axios from "axios";
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';
import BootstrapTable from 'react-bootstrap-table-next';
import {Button} from "react-bootstrap";
import NewCreq from "./modals/NewCreq";

const plantsColumns = [{
    dataField: 'plantId',
    text: 'Plant ID'
}, {
    dataField: 'type',
    text: 'Type'
}, {
    dataField: 'lastInspection',
    text: 'Last Inspection'
}, {
    dataField: 'nextInspection',
    text: 'Next Inspection'
}];

const creqsColumns = [{
    dataField: 'plantId',
    text: 'Plant ID'
}, {
    dataField: 'creqType',
    text: 'Type'
}, {
    dataField: 'status',
    text: 'Status'
}];

class ClientCabinet extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            userId: this.props.info.userId,
            firstName: this.props.info.firstName,
            secondName: this.props.info.secondName,
            role: this.props.info.role,
            plants: [],
            creqs: [],
            plantsLoading: true,
            creqsLoading: true,
            newCreq: false,
            buttonText: "New request!",
            created: false
        }
    };

    componentDidMount() {
        this.fetchPlants(this.props.info.userId);
        this.fetchCreqs(this.props.info.userId);
    }

    fetchPlants = (userId) => {
        axios.get(`http://localhost:8081/greenery/users/uid/${userId}/plants`)
            .then(response => {
                this.setState({
                    plants: response.data,
                    plantsLoading: false
                });
            }).catch(error => {
            console.log("error while getting plants of user");
            console.log(error);
        });
    };

    fetchCreqs = (userId) => {
        axios.get(`http://localhost:8081/greenery/creqs/owner/${userId}`)
            .then(response => {
                this.setState({
                    creqs: response.data,
                    creqsLoading: false
                })
            }).catch(error => {
            console.log("error while getting creqs of user");
            console.log(error);
        });
    };

    showModal = (e) => {
        let text;
        if (this.state.buttonText === "New request!") {
            text = "Cancel"
        }
        else{
            text = "New request!";
        }
        this.setState({
            newCreq: !this.state.newCreq,
            buttonText: text
        });
    };

    render() {
        return (
            <div>
                <br/> Welcome to the GreenOffice, {this.state.firstName}!
                <br/>
                <br/>
                <div style={{ padding: "10px" }}>
                    <h3 className="h5">Your plants</h3>
                    <BootstrapTable keyField='plantId'
                                    data={ this.state.plants } columns={ plantsColumns } />
                </div>
                <br/>
                <br/>
                <h3 className="h5">Your requests</h3>
                <BootstrapTable keyField='creqId' data={ this.state.creqs } columns={ creqsColumns } />
                <Button
                    onClick={this.showModal}>{this.state.buttonText}</Button>
                <NewCreq
                    show={this.state.newCreq} clientId={this.state.userId} showAlert={false}/>
            </div>
        );
    }
}

export default ClientCabinet;




