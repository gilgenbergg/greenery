import React from 'react';
import axios from "axios";
import {
    withRouter
} from "react-router-dom";
import ClientCabinet from "./ClientCabinet";
import AdminCabinet from "./AdminCabinet";
import LandCabinet from "./LandCabinet";

class PersonalCabinet extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            firstName: "",
            secondName: "",
            role: "",
            userId: 0,
            authId: this.props.location.state.id
        }
    }

    componentDidMount() {
        this.fetchData(this.state.authId);
    };

    fetchData = (authId) => {
        axios.get(`http://localhost:8081/greenery/users/authId/${authId}`)
            .then(response => {
                const userData = response.data;
                this.setState({
                    firstName: userData["firstName"],
                    secondName: userData["secondName"],
                    role: userData["role"],
                    userId: userData["userId"]
                })
            }).catch(error => {
            console.log("component did mount error");
            console.log(error);
        });
    };

    render() {
        return(
            <div>
                {this.state.role === "client" && <ClientCabinet info={this.state}/>}
                {this.state.role === "admin" && <AdminCabinet info={this.state}/>}
                {this.state.role === "landscaper" && <LandCabinet info={this.state}/>}
            </div>
        )
    }
}

export default withRouter(PersonalCabinet);