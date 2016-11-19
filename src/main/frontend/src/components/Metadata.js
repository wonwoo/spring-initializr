import React, { Component } from 'react';

import MetaDataStore from '../stores/MetaDataStore';

export default class Metadata extends Component {

    constructor(props) {
        super(props);
        this._onChange = this._onChange.bind(this);
        this.state = this.getStateStore();
        this.handleGroupIdChange = this.handleGroupIdChange.bind(this);
        this.handleArtifactIdChange = this.handleArtifactIdChange.bind(this);
        this.handleNameChange = this.handleNameChange.bind(this);
        this.handleDescriptionChange = this.handleDescriptionChange.bind(this);
        this.handlePackageNameChange = this.handlePackageNameChange.bind(this);
    }

    getStateStore() {
        return {
            artifactId :"",
            artifactTitle :"",
            groupId : "",
            groupTitle : "",
            name : "",
            nameTitle : "",
            description : "",
            descriptionTitle : "",
            packageName : "",
            packageNameTitle : "",
            packagings : [],
            packagingsTitle : "",
            languages : [],
            languagesTitle : "",
            javaVersions : [],
            javaVersionsTitle : "",
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
            artifactTitle : MetaDataStore.ArtifactId.title,
            groupId : MetaDataStore.GroupId.content,
            groupTitle : MetaDataStore.GroupId.title,
            name : MetaDataStore.Name.content,
            nameTitle : MetaDataStore.Name.title,
            description : MetaDataStore.Description.content,
            descriptionTitle : MetaDataStore.Description.title,
            packageName : MetaDataStore.PackageName.content,
            packageNameTitle : MetaDataStore.PackageName.title,
            packagings : MetaDataStore.Packagings.content,
            packagingsTitle : MetaDataStore.Packagings.title,
            languages : MetaDataStore.Languages.content,
            languagesTitle : MetaDataStore.Languages.title,
            javaVersions : MetaDataStore.javaVersions.content,
            javaVersionsTitle : MetaDataStore.javaVersions.title
        });
    }

    handleGroupIdChange(event) {
        this.setState({groupId: event.target.value});
    }

    handleArtifactIdChange(event) {
        this.setState({artifactId: event.target.value});
    }

    handleNameChange(event) {
        this.setState({name: event.target.value});
    }

    handleDescriptionChange(event) {
        this.setState({description: event.target.value});
    }

    handlePackageNameChange(event) {
        this.setState({packageName: event.target.value});
    }

    render() {
        return (
            <div className="col-sm-12 col-md-6">
                <h2>Project Metadata</h2>
                <p>Artifact coordinates</p>
                <div className="form-group groupid-form-group">
                    <label htmlFor="groupId" className="control-label">{this.state.groupTitle}</label>
                    <input id="groupId" className="form-control" type="text" value={this.state.groupId} onChange={this.handleGroupIdChange}
                           name="groupId" />
                </div>
                <div className="form-group artifactid-form-group">
                    <label htmlFor="artifactId" className="control-label">{this.state.artifactTitle}</label>
                    <input id="artifactId" className="form-control" type="text" onChange={this.handleArtifactIdChange}
                           value={this.state.artifactId}
                           name="artifactId" />
                </div>
                <div className="form-group full hidden name-form-group">
                    <label htmlFor="name" className="control-label">{this.state.nameTitle}</label>
                    <input id="name" className="form-control" type="text" value={this.state.name} onChange={this.handleNameChange}
                           name="name" />
                </div>
                <div className="form-group full hidden description-form-group">
                    <label htmlFor="description" className="control-label">{this.state.descriptionTitle}</label>
                    <input id="description" className="form-control" type="text" onChange={this.handleDescriptionChange}
                           value={this.state.description} name="description" />
                </div>
                <div className="form-group full hidden packagename-form-group">
                    <label htmlFor="packageName" className="control-label">{this.state.packageNameTitle}</label>
                    <input id="packageName" className="form-control" type="text" onChange={this.handlePackageNameChange}
                           value={this.state.packageName}
                           name="packageName" />

                </div>
                <div className="form-group full hidden packaging-form-group">
                    <label htmlFor="packaging" className="control-label">{this.state.packagingsTitle}</label>
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
                    <label htmlFor="javaVersion" className="control-label">{this.state.javaVersionsTitle}</label>
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
                    <label htmlFor="language" className="control-label">{this.state.languagesTitle}</label>
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
