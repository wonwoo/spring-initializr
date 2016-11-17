import React, { Component } from 'react';

export default class FullVersion extends Component {
    render() {
        return (
            <div>
                <div className="row tofullversion">
                    <p>Don't know what to look for? Want more options? <a href="#">Switch to the full version.</a></p>
                </div>
                <div className="row">
                    <div id="dependencies" className="full hidden">
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


