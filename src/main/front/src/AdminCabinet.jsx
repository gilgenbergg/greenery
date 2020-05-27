import React from 'react';
import axios from "axios";
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';
import BootstrapTable from 'react-bootstrap-table-next';
import cellEditFactory from 'react-bootstrap-table2-editor';
import {Button} from "react-bootstrap";
import NewPreq from "./modals/NewPreq";
import NewPlant from "./modals/NewPlant";

const creqsColumns = [{
    dataField: 'creqId',
    text: 'Request ID'
}, {
    dataField: 'plantId',
    text: 'Plant ID',
    editable: false
}, {
    dataField: 'type',
    text: 'Type',
    editable: true
}, {
    dataField: 'status',
    text: 'Status',
    editable: true
}, {
    dataField: 'landscaperId',
    text: 'Landscaper ID',
    editable: true
}];

const preqsColumns = [{
    dataField: 'preqId',
    text: 'Purchase ID'
}, {
    dataField: 'creqId',
    text: 'Request ID'
}, {
    dataField: 'plantId',
    text: 'Plant ID'
}, {
    dataField: 'status',
    text: 'Status'
}];


class AdminCabinet extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            userId: this.props.info.userId,
            firstName: this.props.info.firstName,
            secondName: this.props.info.secondName,
            role: this.props.info.role,
            creqs: [],
            preqs: [],
            plantsLoading: true,
            creqsLoading: true,
            newPreq: false,
            buttonText: "New purchase!",
            created: false,
            newPlant: false,
            plantButtonText: "New plant"
        };

        this.afterSaveCell = this.onAfterSaveCell.bind(this)
    };

    componentDidMount() {
        this.fetchCreqs(this.props.info.userId);
        this.fetchPreqs(this.props.info.userId);
    }

    fetchCreqs = (userId) => {
        axios.get(`http://localhost:8081/greenery/creqs/admin/${userId}`)
            .then(response => {
                this.setState({
                    creqs: response.data,
                    creqsLoading: false
                });
            }).catch(error => {
            console.log("error while getting creqs of admin");
            console.log(error);
        });
    };

    fetchPreqs = (userId) => {
        axios.get(`http://localhost:8081/greenery/preqs/admin/${userId}`)
            .then(response => {
                this.setState({
                    preqs: response.data,
                    preqsLoading: false
                })
            }).catch(error => {
            console.log("error while getting creqs of user");
            console.log(error);
        });
    };

    showModal = (e) => {
        let text;
        if (this.state.buttonText === "New purchase!") {
            text = "Cancel"
        }
        else{
            text = "New request!";
        }
        this.setState({
            newPreq: !this.state.newPreq,
            buttonText: text
        });
    };

    editData = (row, column, rowIndex, columnIndex) => {
        let data = this.state.creqs;
        data[rowIndex][column["dataField"]] = row[column["dataField"]];
        this.setState({creqs: data, creqToUpdate: rowIndex});
    };

    onAfterSaveCell = (oldValue, newValue, row, column) => {
        let creqToUpdate = this.state.creqs[this.state.creqToUpdate];
        let creqId = creqToUpdate.creqId;
        if (column["dataField"] === "landscaperId") {
            let landscaperId = parseInt(creqToUpdate["landscaperId"], 10);
            this.updateLandscaper(landscaperId, creqId);
        }
        else if (column["dataField"] === "status") {
            this.updateCreqStatus(creqToUpdate["status"], creqId);
        }
        else if (column["dataField"] === "type") {
            this.updateCreqType(creqToUpdate["type"], creqId);
        }
    };

    updateLandscaper = (landscaperId, creqId) => {
        axios.get(`http://localhost:8081/greenery/creqs/${creqId}/set/landscaper`,
            {params: {landscaperId: landscaperId}})
            .then(response => {
                console.log(response.status);
            }).catch(error => {
            console.log("error in landscaper update");
            console.log(error);
        });
    };

    updateCreqStatus = (creqStatus, creqId) => {
        axios.get(`http://localhost:8081/greenery/creqs/${creqId}/set/status`,
            {params: {status: creqStatus}})
            .then(response => {
                console.log(response.status);
            }).catch(error => {
            console.log("error in creqStatus update");
            console.log(error);
        });
    };

    updateCreqType = (creqType, creqId) => {
        axios.get(`http://localhost:8081/greenery/creqs/${creqId}/set/type`,
            {params: {type: creqType}})
            .then(response => {
                console.log(response.status);
            }).catch(error => {
            console.log("error in creqType update");
            console.log(error);
        });
    };

    showPlantModal = (e) => {
        let text;
        if (this.state.plantButtonText === "New plant") {
            text = "Cancel"
        }
        else{
            text = "New plant";
        }
        this.setState({
            newPlant: !this.state.newPlant,
            plantButtonText: text
        });
    };

    render() {
        return(
            <div>
                <br/> Welcome to the GreenOffice, {this.state.firstName}!
                <br/>
                <br/>
                <div style={{ padding: "10px" }}>
                    <Button
                        onClick={this.showPlantModal}>{this.state.plantButtonText}</Button>
                    <NewPlant
                        show={this.state.newPlant} showAlert={false}/>
                    <h3 className="h5">Client Requests</h3>
                    <BootstrapTable keyField='creqId'
                                    data={ this.state.creqs } columns={ creqsColumns }
                                    cellEdit={ cellEditFactory({
                                        mode: 'click',
                                        blurToSave: true,
                                        afterSaveCell: (oldValue, newValue, row, column) =>
                                        {this.onAfterSaveCell(oldValue, newValue, row, column)},
                                        onStartEdit: (row, column, rowIndex, columnIndex) =>
                                        { this.editData(row, column, rowIndex, columnIndex) }
                                    }) }
                    />
                </div>
                <br/>
                <br/>
                <h3 className="h5">Your purchases</h3>
                <BootstrapTable keyField='preqId' data={ this.state.preqs } columns={ preqsColumns } />
                <Button
                    onClick={this.showModal}>{this.state.buttonText}</Button>
                <NewPreq
                    show={this.state.newPreq} adminId={this.state.userId} showAlert={false}/>
            </div>
        );
    }

}

export default AdminCabinet;