
import jQuery from "jquery";
import {Bloodhound} from "typeahead.js-browserify"
import Utils from "./Utils";

export default  {
    starters: function () {
        return new Bloodhound({
            datumTokenizer: Bloodhound.tokenizers.obj.nonword('name', 'description', 'keywords', 'group'),
            queryTokenizer: Bloodhound.tokenizers.nonword,
            identify: function (obj) {
                return obj.id;
            },
            sorter: function (a, b) {
                return b.weight - a.weight;
            },
            cache: false
        })
    },
    initializeSearchEngine: function (engine, bootVersion) {
        jQuery.getJSON("/ui/dependencies?version=" + bootVersion, function (data) {
            engine.clear();
            jQuery.each(data.dependencies, function (idx, item) {
                if (item.weight === undefined) {
                    item.weight = 0;
                }
            });
            engine.add(data.dependencies);
        });
    },

}


var addTag = function (id, name) {
    if (jQuery("#starters div[data-id='" + id + "']").length == 0) {
        jQuery("#starters").append("<div class='tag' data-id='" + id + "'>" + name +
            "<button type='button' class='close' aria-label='Close'><span aria-hidden='true'>&times;</span></button></div>");
    }
};
var removeTag = function (id) {
    jQuery("#starters div[data-id='" + id + "']").remove();
};
//
//
// jQuery('#autocomplete').typeahead(
//     {
//         minLength: 2,
//         autoSelect: true
//     }, {
//         name: 'starters',
//         display: 'name',
//         source: Utils.starters,
//         templates: {
//             suggestion: function (data) {
//                 return "<div><strong>" + data.name + "</strong><br/><small>" + data.description + "</small></div>";
//             }
//         }
//     });
jQuery('#autocomplete').bind('typeahead:select', function (ev, suggestion) {
    var alreadySelected = jQuery("#dependencies input[value='" + suggestion.id + "']").prop('checked');
    if(alreadySelected) {
        removeTag(suggestion.id);
        jQuery("#dependencies input[value='" + suggestion.id + "']").prop('checked', false);
    }
    else {
        addTag(suggestion.id, suggestion.name);
        jQuery("#dependencies input[value='" + suggestion.id + "']").prop('checked', true);
    }
    jQuery('#autocomplete').typeahead('val', '');
});
jQuery("#starters").on("click", "button", function () {
    var id = jQuery(this).parent().attr("data-id");
    jQuery("#dependencies input[value='" + id + "']").prop('checked', false);
    removeTag(id);
});
jQuery("#groupId").on("change", function() {
    jQuery("#packageName").val(jQuery(this).val());
});
jQuery("#artifactId").on("change", function() {
    jQuery("#name").val(jQuery(this).val());
});
jQuery("#dependencies input").bind("change", function () {
    var value = jQuery(this).val();
    if (jQuery(this).prop('checked')) {
        var results = Utils.starters.get(value);
        addTag(results[0].id, results[0].name);
    } else {
        removeTag(value);
    }
});

Mousetrap.bind(['command+enter', 'alt+enter'], function (e) {
    jQuery("#form").submit();
    return false;
});
var autocompleteTrap = new Mousetrap(jQuery("#autocomplete").get(0));
autocompleteTrap.bind(['command+enter', 'alt+enter'], function (e) {
    jQuery("#form").submit();
    return false;
});
autocompleteTrap.bind("enter", function(e) {
    if (e.preventDefault) {
        e.preventDefault();
    } else {
        e.returnValue = false;
    }
});

if (navigator.appVersion.indexOf("Mac") != -1) {
    jQuery(".btn-primary").append("<kbd>&#8984; + &#9166;</kbd>");
}
else {
    jQuery(".btn-primary").append("<kbd>alt + &#9166;</kbd>");
}

jQuery(".tofullversion a").on("click", function() {
    jQuery(".full").removeClass("hidden");
    jQuery(".tofullversion").addClass("hidden");
    jQuery(".tosimpleversion").removeClass("hidden");
    jQuery("body").scrollTop(0);
    return false;
});
jQuery(".tosimpleversion a").on("click", function() {
    jQuery(".full").addClass("hidden");
    jQuery(".tofullversion").removeClass("hidden");
    jQuery(".tosimpleversion").addClass("hidden");
    applyParams();
    jQuery("body").scrollTop(0);
    return false;
});

