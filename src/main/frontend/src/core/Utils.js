import $ from "jquery";

export default  {

    addTag : function (id, name) {
        if ($("#starters div[data-id='" + id + "']").length == 0) {
            $("#starters").append("<div class='tag' data-id='" + id + "'>" + name +
                "<button type='button' class='close' aria-label='Close'><span aria-hidden='true'>&times;</span></button></div>");
        }
    },
    removeTag : function (id) {
        $("#starters div[data-id='" + id + "']").remove();
    },

    parseQualifier(version) {
        var qualifiers = ['M', 'RC', 'BUILD-SNAPSHOT', 'RELEASE'];
        var qual = version.replace(/\d+/g, "");
        return qualifiers.indexOf(qual) != -1 ? qual : "RELEASE";
    },

    compareVersions(a, b) {
        var qualifiers = ['M', 'RC', 'BUILD-SNAPSHOT', 'RELEASE'];
        var result;

        var versionA = a.split(".");
        var versionB = b.split(".");
        for (var i = 0; i < 3; i++) {
            result = parseInt(versionA[i], 10) - parseInt(versionB[i], 10);
            if (result != 0) {
                return result;
            }
        }
        var aqual = this.parseQualifier(versionA[3]);
        var bqual = this.parseQualifier(versionB[3]);
        result = qualifiers.indexOf(aqual) - qualifiers.indexOf(bqual);
        if (result != 0) {
            return result;
        }
        return versionA[3].localeCompare(versionB[3]);
    }
}

