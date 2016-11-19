import React, { Component } from 'react';
import $ from "jquery";
import Bloodhound from "bloodhound-js"
import typeahead from "typeahead.js"

import MetaDataStore from '../stores/MetaDataStore';
import Utils from '../core/Utils';


export default class Dependencies extends Component {


    constructor(props) {
        super(props);
        this._onChange = this._onChange.bind(this);
        this.starters = new Bloodhound({
            datumTokenizer: Bloodhound.tokenizers.obj.nonword('name', 'description', 'keywords', 'group'),
            queryTokenizer: Bloodhound.tokenizers.nonword,
            identify: function (obj) {
                return obj.id;
            },
            sorter: function(a,b) {
                return b.weight - a.weight;
            },
            cache: false
        });
    }

    componentWillMount() {
        MetaDataStore.addChangeListener(this._onChange);
    }

    initializeSearchEngine (engine, bootVersion) {
        $.getJSON("/ui/dependencies?version=" + bootVersion, function (data) {
            engine.clear();
            $.each(data.dependencies, function (idx, item) {
                if (item.weight === undefined) {
                    item.weight = 0;
                }
            });
            engine.add(data.dependencies);
        });
    }

    componentWillUnmount() {
        MetaDataStore.removeChangeListener(this._onChange);
    }
    componentDidMount() {
        $('#autocomplete').typeahead(
            {
                minLength: 2,
                autoSelect: false
            }, {
                name: 'starters',
                display: 'name',
                source: this.starters,
                templates: {
                    suggestion: function (data) {
                        return "<div><strong>" + data.name + "</strong><br/><small>" + data.description + "</small></div>";
                    }
                }
            });
        $('#autocomplete').bind('typeahead:select', function (ev, suggestion) {
            var alreadySelected = $("#dependencies input[value='" + suggestion.id + "']").prop('checked');
            if(alreadySelected) {
                Utils.removeTag(suggestion.id);
                $("#dependencies input[value='" + suggestion.id + "']").prop('checked', false);
            }
            else {
                Utils.addTag(suggestion.id, suggestion.name);
                $("#dependencies input[value='" + suggestion.id + "']").prop('checked', true);
            }
            $('#autocomplete').typeahead('val', '');
        });
        $("#starters").on("click", "button", function () {
            var id = $(this).parent().attr("data-id");
            $("#dependencies input[value='" + id + "']").prop('checked', false);
            Utils.removeTag(id);
        });
        $("#groupId").on("change", function() {
            $("#packageName").val($(this).val());
        });
        $("#artifactId").on("change", function() {
            $("#name").val($(this).val());
        });
        $("#dependencies input").bind("change", function () {
            var value = $(this).val();
            if ($(this).prop('checked')) {
                var results = this.starters.get(value);
                Utils.addTag(results[0].id, results[0].name);
            } else {
                Utils.removeTag(value);
            }
        });

        var autocompleteTrap = new Mousetrap($("#autocomplete").get(0));
        autocompleteTrap.bind(['command+enter', 'alt+enter'], function (e) {
            $("#form").submit();
            return false;
        });
        autocompleteTrap.bind("enter", function(e) {
            if (e.preventDefault) {
                e.preventDefault();
            } else {
                e.returnValue = false;
            }
        });
    }

    _onChange() {
        this.initializeSearchEngine(this.starters, $("#bootVersion").val());
    }

    render() {
        return (
            <div className="col-sm-12 col-md-6">
                <h2>Dependencies</h2>

                <p>Add Spring Boot Starters and dependencies to your application</p>

                <div className="form-group">
                    <label htmlFor="autocomplete" className="control-label">Search for dependencies</label>
                    <input id="autocomplete" className="form-control" type="text"
                           placeholder="Web, Security, JPA, Actuator, Devtools..."
                           name="autocomplete" />
                </div>
                <div className="form-group">
                    <label htmlFor="starters" className="control-label">Selected Dependencies</label>
                    <div id="starters">
                    </div>
                </div>
            </div>
        );
    }
}