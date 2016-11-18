$(function () {
    if (navigator.appVersion.indexOf("Mac") != -1) {
        $(".btn-primary").append("<kbd>&#8984; + &#9166;</kbd>");
    }
    else {
        $(".btn-primary").append("<kbd>alt + &#9166;</kbd>");
    }

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
        applyParams();
        $("body").scrollTop(0);
        return false;
    });

});