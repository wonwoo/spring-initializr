import React, { Component } from 'react';
import $ from "jquery";

import MetaDataStore from '../stores/MetaDataStore';
import Utils from '../core/Utils';


export default class Dependencies extends Component {


    constructor(props) {
        super(props);
        this._onChange = this._onChange.bind(this);

    }

    componentWillMount() {
        MetaDataStore.addChangeListener(this._onChange);
    }


    componentWillUnmount() {
        MetaDataStore.removeChangeListener(this._onChange);
    }

    _onChange() {
        console.log(1111111111);
        Utils.initializeSearchEngine(Utils.starters, $("#bootVersion").val());
    }

    render() {
        return (
            <div className="col-sm-12 col-md-6">
                <h2>Dependencies</h2>

                <p>Add Spring Boot Starters and dependencies to your application</p>

                <div className="form-group">
                    <label htmlFor="autocomplete" className="control-label">Search for dependencies</label>
                    <input id="autocomplete" className="form-control" type="text"
                           placeholder="Web, Security, JPA, Actuator, Devtools..."
                           name="autocomplete" />
                </div>
                <div className="form-group">
                    <label htmlFor="starters" className="control-label">Selected Dependencies</label>
                    <div id="starters">
                    </div>
                </div>
            </div>
        );
    }
}
