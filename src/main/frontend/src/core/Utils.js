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
    }
}

