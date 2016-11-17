import React, { Component } from 'react';

export default class Metadata extends Component {
    render() {
        return (
            <div className="col-sm-12 col-md-6">
                <h2>Project Metadata</h2>

                <p>Artifact coordinates</p>

                <div className="form-group groupid-form-group">
                    <label htmlFor="groupId" className="control-label">Group</label>
                    <input id="groupId" className="form-control" type="text" value=""
                           name="groupId" />
                </div>
                <div className="form-group artifactid-form-group">
                    <label htmlFor="artifactId" className="control-label">Artifact</label>
                    <input id="artifactId" className="form-control" type="text"
                           value=""
                           name="artifactId" />
                </div>
                <div className="form-group full hidden name-form-group">
                    <label htmlFor="name" className="control-label">Name</label>
                    <input id="name" className="form-control" type="text" value=""
                           name="name" />
                </div>
                <div className="form-group full hidden description-form-group">
                    <label htmlFor="description" className="control-label">Description</label>
                    <input id="description" className="form-control" type="text"
                           value="" name="description" />
                </div>
                <div className="form-group full hidden packagename-form-group">
                    <label htmlFor="packageName" className="control-label">PackageName</label>
                    <input id="packageName" className="form-control" type="text"
                           value=""
                           name="packageName" />

                </div>
                <div className="form-group full hidden packaging-form-group">
                    <label htmlFor="packaging" className="control-label">Packagings</label>
                    <select className="form-control" id="packaging" name="packaging">
                        <option></option>
                    </select>
                </div>
                <div className="form-group full hidden javaversion-form-group">
                    <label htmlFor="javaVersion" className="control-label">title</label>
                    <select className="form-control" name="javaVersion" id="javaVersion">
                        <option></option>
                    </select>
                </div>
                <div className="form-group full hidden language-form-group">
                    <label htmlFor="language" className="control-label">Languages</label>
                    <select className="form-control" name="language" id="language">
                        <option></option>
                    </select>
                </div>
            </div>
        );
    }
}
