import React, { Component } from 'react';

export default class Dependencies extends Component {
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
