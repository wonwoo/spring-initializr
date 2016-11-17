import ActionConstants from '../constants/ActionConstants';
import BaseStore from './BaseStore'

class MetaDataStore extends BaseStore {

    constructor() {
        super();
        this.subscribe(() => this._registerToActions.bind(this));
        this.metas;
    }

    _registerToActions(payload) {
        let action = payload.action;
        switch (action.actionType) {
            case ActionConstants.RECEIVE_METADATA:
                this.metas = action;
                break;
            default:
                return true;
        }

        this.emitChange();

        return true;
    }

    get Metas() {
        return this.metas;
    }
}

export default new MetaDataStore();