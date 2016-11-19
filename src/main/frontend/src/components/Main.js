import React, { Component } from 'react';
import Footer from './Footer';
import Choice from './Choice';
import Metadata from './Metadata';
import Dependencies from './Dependencies';
import SimpleVersion from './SimpleVersion';
import FullVersion from './FullVersion';
import $ from 'jquery';


import MetaDataStore from '../stores/MetaDataStore';
import MetaDataCreator from '../actions/MetaDataCreator';

export default class Main extends Component {


    constructor(props) {
        super(props);
        this._onChange = this._onChange.bind(this);
        this.state = this.getStateStore();
    }

    getStateStore() {
        return {
            configs: {}
        }
    }

    componentWillMount() {
        MetaDataStore.addChangeListener(this._onChange);
    }

    componentDidMount() {
        MetaDataCreator.getMetaConfig()

        $(".tofullversion a").on("click", function() {
            $(".full").removeClass("hidden");
            $(".tofullversion").addClass("hidden");
            $(".tosimpleversion").removeClass("hidden");
            $("body").scrollTop(0);
            return false;
        });
        $(".tosimpleversion a").on("click", function() {
            $(".full").addClass("hidden");
            $(".tofullversion").removeClass("hidden");
            $(".tosimpleversion").addClass("hidden");
            $("body").scrollTop(0);
            return false;
        });
    }

    componentWillUnmount() {
        MetaDataStore.removeChangeListener(this._onChange);
    }

    _onChange() {
        this.setState({
            configs: MetaDataStore.metas
        });
    }

    render() {
        return (
            <div>
                <form id="form" action="/starter.zip" method="get" role="form">
                    <div className="container-fluid">
                        <div className="row start-header">
                            <div className="container">
                                <h1>Spring Initializr <small>bootstrap your application now</small></h1>
                            </div>
                        </div>
                        <div className="container start-main">
                            <Choice/>
                            <input id="baseDir" name="baseDir" type="hidden"  />
                            <div className="row">
                                <Metadata />
                                <Dependencies/>
                            </div>
                            <SimpleVersion />
                            <FullVersion />
                        </div>
                    </div>
                </form>
                <Footer />
            </div>
        );
    }
}