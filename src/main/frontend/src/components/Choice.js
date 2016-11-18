import React, { Component } from 'react';
import MetaDataStore from '../stores/MetaDataStore';

export default class Choice extends Component {

    constructor(props) {
        super(props);
        this._onChange = this._onChange.bind(this);
        this.state = this.getStateStore();
    }

    getStateStore() {
        return {
            types :[],
            bootVersion : []
        }
    }

    componentWillMount() {
        MetaDataStore.addChangeListener(this._onChange);
    }


    componentWillUnmount() {
        MetaDataStore.removeChangeListener(this._onChange);
    }

    _onChange() {
        this.setState({
            types : MetaDataStore.Types.content,
            bootVersion : MetaDataStore.BootVersions.content,
        });
    }

    render() {
        return (
            <div className="row form-inline text-center">
                <div className="form-group project-choice">
                    <h2>Generate a  &nbsp;
                        <select className="form-control" id="type" name="type">
                            {
                                this.state.types.map((content, idx) => {
                                    return <option key={idx}
                                                   value={content.id}>{content.name}</option>;
                                })
                            }
                        </select>
                        &nbsp; with Spring Boot &nbsp;
                        <select className="form-control" name="bootVersion" id="bootVersion">
                            {
                                this.state.bootVersion.map((content, idx) => {
                                    return <option key={idx}
                                                   value={content.id}>{content.name}</option>;
                                })
                            }
                        </select>
                    </h2>
                </div>
            </div>
        );
    }
}
