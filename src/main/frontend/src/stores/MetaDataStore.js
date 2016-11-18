import ActionConstants from '../constants/ActionConstants';
import BaseStore from './BaseStore'

class MetaDataStore extends BaseStore {

    constructor() {
        super();
        this.subscribe(() => this._registerToActions.bind(this));
        this.metas;
        this.types;
        this.bootVersions;
    }

    _registerToActions(payload) {
        let action = payload.action;
        switch (action.actionType) {
            case ActionConstants.RECEIVE_METADATA:
                this.metas = action.metas;
                console.log(this.metas);
                this.types = this.metas.types;
                this.bootVersions = this.metas.bootVersions;
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

    get Types() {
        return this.types;
    }

    get BootVersions() {
        return this.bootVersions;
    }
}

export default new MetaDataStore();