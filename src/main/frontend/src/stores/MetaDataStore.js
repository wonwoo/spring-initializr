import ActionConstants from '../constants/ActionConstants';
import BaseStore from './BaseStore'

class MetaDataStore extends BaseStore {

    constructor() {
        super();
        this.subscribe(() => this._registerToActions.bind(this));
        this.metas;
        this.types;
        this.bootVersions;
        this.artifactId;
        this.groupId;
        this.name;
        this.description;
        this.packageName;
        this.packagings;
        this.languages;
        this.javaVersions;
        this.dependencies;
        this.version;
    }

    _registerToActions(payload) {
        let action = payload.action;
        switch (action.actionType) {
            case ActionConstants.RECEIVE_METADATA:
                this.metas = action.metas;
                console.log(this.metas);
                this.types = this.metas.types;
                this.bootVersions = this.metas.bootVersions;
                this.artifactId = this.metas.artifactId;
                this.groupId = this.metas.groupId;
                this.name = this.metas.name;
                this.description = this.metas.description;
                this.packageName = this.metas.packageName;
                this.packagings = this.metas.packagings;
                this.languages = this.metas.languages;
                this.javaVersions = this.metas.javaVersions;
                this.dependencies = this.metas.dependencies;
                this.version = this.bootVersions.content.filter(boot => boot.default)[0].id
                break;
            case ActionConstants.UI_DEPENDENCIES:
                this.version = action.version;
                break;
            default:
                return true;
        }

        this.emitChange();

        return true;
    }

    get Version() {
        return this.version;
    }

    get Dependencies() {
        return this.dependencies
    }

    get Name() {
        return this.name;
    }

    get Description() {
        return this.description;
    }

    get PackageName() {
        return this.packageName;
    }

    get Packagings() {
        return this.packagings;
    }

    get Languages() {
        return this.languages;
    }

    get JavaVersions() {
        return this.javaVersions;
    }

    get ArtifactId() {
        return this.artifactId;
    }

    get GroupId () {
        return this.groupId;
    }

    get Types() {
        return this.types;
    }

    get BootVersions() {
        return this.bootVersions;
    }
}

export default new MetaDataStore();