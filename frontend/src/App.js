import React, {Component} from 'react';
import './App.css';
import axios from 'axios';
import {
    BrowserRouter,
    Route,
    Switch
} from 'react-router-dom';
import Navigator from './Navigator/Navigator'

class App extends Component {

    constructor(props){
        super(props);
        this.state = {
            list:[]
        }
    }
    componentDidMount() {
        console.log('app did mount');
        this.query();
    }

    query = () =>{
        axios.get('/user/all').then(({data})=>{
            console.log(data, data.length);
            this.setState({
                list:data
            });
        });
    };

    render() {

        let users = this.state.list.map(user => {
            return <div key={user.userId}>{user.userId}, {user.account}</div>
        });



        return (
            <div className="App">
                <Navigator/>
                {users}
            </div>
        );
    }
}

export default App;
