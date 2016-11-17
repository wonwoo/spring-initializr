import React, { Component } from 'react';

export default class SimpleVersion extends Component {
    render() {
        return (
            <div>
                <div className="row hidden tosimpleversion">
                    <p>Too many options? <a href="#">Switch back to the simple version.</a></p>
                </div>
                <div className="row">
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
