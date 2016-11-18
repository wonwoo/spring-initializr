import React, { Component } from 'react';

import MetaDataStore from '../stores/MetaDataStore';

export default class Metadata extends Component {

    constructor(props) {
        super(props);
        this._onChange = this._onChange.bind(this);
        this.state = this.getStateStore();
    }

    getStateStore() {
        return {
            artifactId :"",
            groupId : "",
            name : "",
            description : "",
            packageName : "",
            packagings : [],
            languages : [],
            javaVersions : []
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
            artifactId : MetaDataStore.ArtifactId.content,
            groupId : MetaDataStore.GroupId.content,
            name : MetaDataStore.Name.content,
            description : MetaDataStore.Description.content,
            packageName : MetaDataStore.PackageName.content,
            packagings : MetaDataStore.Packagings.content,
            languages : MetaDataStore.Languages.content,
            javaVersions : MetaDataStore.javaVersions.content
        });
    }


    render() {
        return (
            <div className="col-sm-12 col-md-6">
                <h2>{this.props.content}123123</h2>

                <p>Artifact coordinates</p>

                <div className="form-group groupid-form-group">
                    <label htmlFor="groupId" className="control-label">Group</label>
                    <input id="groupId" className="form-control" type="text" value={this.state.groupId}
                           name="groupId" />
                </div>
                <div className="form-group artifactid-form-group">
                    <label htmlFor="artifactId" className="control-label">Artifact</label>
                    <input id="artifactId" className="form-control" type="text"
                           value={this.state.artifactId}
                           name="artifactId" />
                </div>
                <div className="form-group full hidden name-form-group">
                    <label htmlFor="name" className="control-label">Name</label>
                    <input id="name" className="form-control" type="text" value={this.state.name}
                           name="name" />
                </div>
                <div className="form-group full hidden description-form-group">
                    <label htmlFor="description" className="control-label">Description</label>
                    <input id="description" className="form-control" type="text"
                           value={this.state.description} name="description" />
                </div>
                <div className="form-group full hidden packagename-form-group">
                    <label htmlFor="packageName" className="control-label">PackageName</label>
                    <input id="packageName" className="form-control" type="text"
                           value={this.state.packageName}
                           name="packageName" />

                </div>
                <div className="form-group full hidden packaging-form-group">
                    <label htmlFor="packaging" className="control-label">Packagings</label>
                    <select className="form-control" id="packaging" name="packaging">
                        {
                            this.state.packagings.map((content, idx) => {
                                return <option key={idx}
                                               value={content.id}>{content.name}</option>;
                            })
                        }
                    </select>
                </div>
                <div className="form-group full hidden javaversion-form-group">
                    <label htmlFor="javaVersion" className="control-label">javaVersions</label>
                    <select className="form-control" name="javaVersion" id="javaVersion">
                        {
                            this.state.javaVersions.map((content, idx) => {
                                return <option key={idx}
                                               value={content.id}>{content.name}</option>;
                            })
                        }
                    </select>
                </div>
                <div className="form-group full hidden language-form-group">
                    <label htmlFor="language" className="control-label">Languages</label>
                    <select className="form-control" name="language" id="language">
                        {
                            this.state.languages.map((content, idx) => {
                                return <option key={idx}
                                               value={content.id}>{content.name}</option>;
                            })
                        }
                    </select>
                </div>
            </div>
        );
    }
}
