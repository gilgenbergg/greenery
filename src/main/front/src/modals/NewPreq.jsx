import React from "react";
import {Alert, Button} from "react-bootstrap";
import {ReactRadioButton, ReactRadioButtonsGroup} from "react-radio-buttons-group";
import axios from "axios";
import {MDBBtn} from "mdbreact";

const ListItem = ({ value, onClick }) => (
    <li onClick={onClick}>{value}</li>
);

const List = ({ items, onItemClick }) => (
    <ul>
        {
            items.map((item, i) => <ListItem key={i} value={item} onClick={onItemClick} />)
        }
    </ul>
);

export default class NewPreq extends React.Component {
    constructor(props)
    {
        super(props);

        this.state = {
            type: "",
            plantId: null,
            plantName: "",
            landscaper: null,
            creq: null,
            status: "",
            resources: [],
            newResource: '',
            showAlert: false
        };
        this.submitForm = this.submitForm.bind(this);
        this.onChange = this.onChange.bind(this);
        this.onClick = this.onClick.bind(this);
    }

    submitForm = e => {
        e.preventDefault();
        let newPreqInfo = {};
        if ((this.state.status === "") || (!this.state.creq) || (!this.state.landscaper) || (!this.state.plantId)){
            this.setState({showAlert: true});
            return false;
        }
        this.sendResources();
        if (this.state.status) {
            newPreqInfo = {
                preqType: "newOne",
                plantId: this.state.plantId,
                adminId: this.props.adminId,
                creqId: this.state.creq,
                landscaperId: this.state.landscaper,
                status: this.state.status
            };
        }
        else {
            newPreqInfo = {
                preqType: "newOne",
                plantId: this.state.plantId,
                adminId: this.props.adminId,
                creqId: this.state.creq,
                landscaperId: this.state.landscaper,
                status: "inProgress"
            };
        }
        axios.post(`http://localhost:8081/greenery/preqs/add`, newPreqInfo, {
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

    setPlantId = (e) => {
        let plantId = e.target.value;
        this.getPlantName(plantId);

    };

    sendResources = () => {
        this.state.resources.forEach(resource => this.sendResource(resource));
    };

    sendResource = (resourceType) => {
        let item = {
            plantId: this.state.plantId,
            type: resourceType
        };
        axios.post(`http://localhost:8081/greenery/resources/add`, item, {
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then (response => {
                console.log("resource was added!");
            })
            .catch(error => {
                console.log("error happend");
                console.log(error);
            });
    };

    getPlantName = (plantId) => {
        axios.get(`http://localhost:8081/greenery/plants/plantid/${plantId}`)
            .then(response => {
                this.setState({
                    plantId: plantId,
                    plantName: response.data["type"]
                });
            }).catch(error => {
            console.log("error while getting plant name");
            console.log(error);
        });
    };

    setLandscaper = (e) => {
        this.setState({
            landscaper: e.target.value
        })
    };

    setCreq = (e) => {
        this.setState({
            creq: e.target.value
        })
    };

    setStatus = (value) => {
        this.setState({
            status: value
        })
    };

    closeAlert = () => {
        this.setState({showAlert: false})
    };

    onClose = (e) => {
        this.props.onClose && this.props.created && this.props.onClose(e);
    };

    onClick = () => {
        const { newResource, resources } = this.state;
        if (newResource) {
            const nextState = [...resources, newResource];
            this.setState({ resources: nextState, newResource: '' });
        }
    };

    onChange = (e) => this.setState({ newResource: e.target.value });

    handleItemClick = (e) => {console.log(e.target.innerHTML)};

    render() {
        if(!this.props.show){
            return null;
        }
        const { resources, newResource } = this.state;
        return (
            <div>
                {this.state.showAlert && <Alert variant={"danger"} onClose={this.closeAlert} dismissible>
                    Please, fill all the fields!
                </Alert> }
                <div>{this.props.children}</div>
                {this.state.created && <div>New purchase has been added!</div>}

                <label htmlFor="plantId" className="grey-text">
                    Plant ID
                </label>
                <input type="number" id="plantId" className="form-control"
                       onChange={this.setPlantId}/>
                <input disabled type="text" id="plantName" className="form-control"
                       value={this.state.plantName}/>
                       <br/>

                <label htmlFor="landscaper" className="grey-text">
                   Assigned landscaper
                </label>
                <input type="number" id="landscaper" className="form-control"
                       onChange={this.setLandscaper}/>
                <br/>

                <label htmlFor="creq" className="grey-text">
                    Assigned client request
                </label>
                <input type="number" id="creq" className="form-control"
                       onChange={this.setCreq}/>
                <br/>

                <label htmlFor="status" className="grey-text">
                    Status
                </label>
                <label htmlFor="status" className="grey-text">
                    Resources to buy:
                </label>

                <div>
                    <ReactRadioButtonsGroup horizontal onChange={this.setStatus}>
                        <ReactRadioButton value="inProgressd">In progress</ReactRadioButton>
                        <ReactRadioButton value="inCheck">In check</ReactRadioButton>
                        <ReactRadioButton value="done">Done</ReactRadioButton>
                    </ReactRadioButtonsGroup>
                </div>
                <br/>

                <div>
                    <input type="text" value={newResource} onChange={this.onChange} />
                    <button onClick={this.onClick}>Add</button>
                    <List items={resources} onItemClick={this.handleItemClick} />
                </div>

                <MDBBtn color="unique" type="submit" onClick={this.submitForm}>
                    Done!
                </MDBBtn>
            </div>
        );
    }
}