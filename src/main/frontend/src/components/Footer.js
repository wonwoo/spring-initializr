import React, { Component } from 'react';

export default class Footer extends Component {
    render() {
        return (
            <footer>
                <div className="container">
                    <p>start.spring.io is powered by <a href="https://github.com/spring-io/initializr/">Spring Initializr</a>
                        and <a href="https://run.pivotal.io">Pivotal Web Services</a></p>
                </div>
            </footer>
        );
    }
}

