import Dispatcher from '../core/Dispatcher';
import ActionConstants from '../constants/ActionConstants';
import { Promise } from 'es6-promise';
import Api from '../services/Api';

export default {
    getMetaConfig: function () {
        Api
            .get('metadata/config')
            .then(function (metas) {
                Dispatcher.handleViewAction({
                    actionType: ActionConstants.RECEIVE_METADATA,
                    metas: metas
                });
            })
            .catch(function (error) {
                Dispatcher.handleViewAction({
                    actionType: ActionConstants.RECEIVE_ERROR,
                    error: error
                });
            });
    }
}