import request from 'superagent';
import {Promise} from  'es6-promise'; // jshint ignore:line
import ActionConstants from  '../constants/ActionConstants'; // jshint ignore:line

var Api = {
    get: function (url) {
        return new Promise(function (resolve, reject) {
            request
                .get(ActionConstants.API_URL + url).accept('json')
                .end(function (err, res) {
                    response(res, resolve, reject);
                });
        });
    },
    post: function (url, data) {
        return new Promise(function (resolve, reject) {
            request
                .post(ActionConstants.API_URL + url, data)
                .end(function (res) {
                    response(res, resolve, reject);
                });
        });
    }
};

function response(res, resolve, reject) {
    if (res.status >= 200 && res.status < 300) {
        resolve(JSON.parse(res.text));
    } else {
        reject();
    }
}

export default Api;