/**
 * Created by wonwoo on 2016. 11. 19..
 */
import Utils from './Utils'

export default class Versions {
    constructor() {
        this.strict_range = /\[(.*),(.*)\]/;
        this.halfopen_right_range = /\[(.*),(.*)\)/;
        this.halfopen_left_range = /\((.*),(.*)\]/;
    }

    matchRange(range) {
        var strict_match = range.match(this.strict_range);
        if (strict_match) {
            return function (version) {
                return Utils.compareVersions(strict_match[1], version) <= 0
                    && Utils.compareVersions(strict_match[2], version) >= 0;
            }
        }
        var hor_match = range.match(this.halfopen_right_range);
        if (hor_match) {
            return function (version) {
                return Utils.compareVersions(hor_match[1], version) <= 0
                    && Utils.compareVersions(hor_match[2], version) > 0;
            }
        }
        var hol_match = range.match(this.halfopen_left_range);
        if (hol_match) {
            return function (version) {
                return Utils.compareVersions(hol_match[1], version) < 0
                    && Utils.compareVersions(hol_match[2], version) >= 0;
            }
        }

        return function (version) {
            return Utils.compareVersions(range, version) <= 0;
        }
    }
}
