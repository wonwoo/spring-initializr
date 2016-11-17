import React, { Component } from 'react';

export default class Choice extends Component {
    render() {
        return (
            <div className="row form-inline text-center">
                <div className="form-group project-choice">
                    <h2>Generate a
                        <select className="form-control" id="type" name="type">
                            <option></option>
                        </select>
                        with Spring Boot
                        <select className="form-control" name="bootVersion" id="bootVersion">
                            <option></option>
                        </select>
                    </h2>
                </div>
            </div>
        );
    }
}
