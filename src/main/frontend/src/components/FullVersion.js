import React, { Component } from 'react';
import MetaDataStore from '../stores/MetaDataStore';

export default class FullVersion extends Component {
    constructor(props) {
        super(props);
        this._onChange = this._onChange.bind(this);
        this.state = this.getStateStore();
    }

    getStateStore() {
        return {
            dependencies : []
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
            dependencies : MetaDataStore.Dependencies.content
        });
    }

    render() {
        var DependenciesNode = this.state.dependencies.map((content, id) => {
            return (
                <div className="form-group col-sm-6" key={id}>
                    <h3>{content.name}</h3>
                    {
                        content.content.map((innerContent, innerId) => {
                            return (
                                <div className="checkbox" key={innerId}>
                                    <label>
                                        <input type="checkbox" name="style" value={innerContent.id}/>
                                        {innerContent.name}
                                        <p className="help-block">{innerContent.description}</p>
                                    </label>
                                </div>
                            )
                        })
                    }
                </div>
            )}
        );
        return (
            <div>
                <div className="row tofullversion">
                    <p>Don't know what to look for? Want more options? <a href="#">Switch to the full version.</a></p>
                </div>
                <div className="row">
                    <div id="dependencies" className="full hidden">
                        {DependenciesNode}
                    </div>
                </div>
                <div className="row full hidden">
                    <p className="text-center">
                        <button name="generate-project" type="submit" className="btn btn-lg btn-primary"
                                role="button">
                            Generate Project
                        </button>
                    </p>
                </div>
            </div>

        );
    }
}


