import React from 'react';
import axios from "axios";
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';
import BootstrapTable from 'react-bootstrap-table-next';
import cellEditFactory from "react-bootstrap-table2-editor";

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

class LandCabinet extends React.Component {

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
            creqsLoading: true
        }
    };

    componentDidMount() {
        this.fetchCreqs(this.props.info.userId);
        this.fetchPreqs(this.props.info.userId);
    }

    fetchCreqs = (userId) => {
        axios.get(`http://localhost:8081/greenery/creqs/landscaper/${userId}`)
            .then(response => {
                this.setState({
                    creqs: response.data,
                    creqsLoading: false
                });
            }).catch(error => {
            console.log("error while getting creqs of landscaper");
            console.log(error);
        });
    };

    fetchPreqs = (userId) => {
        axios.get(`http://localhost:8081/greenery/preqs/landscaper/${userId}`)
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

    render() {
        return(
            <div>
            <br/> Welcome to the GreenOffice, {this.state.firstName}!
            <br/>
            <br/>
                <h3 className="h5">Client requests</h3>
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
            <br/>
            <br/>
            <h3 className="h5">Your purchases</h3>
        <BootstrapTable keyField='preqId' data={ this.state.preqs } columns={ preqsColumns } />
            </div>
        );
    }
}

export default LandCabinet;