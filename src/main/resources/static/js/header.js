if(sessionStorage.getItem("save") == "on") {
    $("#save button img").attr("src","image/switch.png")
}
else if(sessionStorage.getItem("save") == "off") {
    $("#save button img").attr("src","image/switch3.png")
}

$(function() {
    $("#search input").on("click", function() {
        if($("#save button img").attr("src") === "image/switch.png")
            $("#search > div").show()
    });

    $("#save > img").on("click", function() {
        $("#search > div").hide()
    });

    $("#search input").on("keyup", function() {
        if($("#search input").val())
            $("#search > div").show()
        else
            $("#search > div").hide()
    });
});

function check() {
    if($("#save button img").attr("src") === "image/switch.png") {
        if(confirm("검색어 저장 기능을 중지하시겠습니까?")) {
            $("#search > div").hide()
            $("#save button img").attr("src","image/switch3.png")
            $("#search input[name=save]").attr("value","off")
            location.href="searchSave.jsp?save=off"
        }
    }
    else {
        if(confirm("검색어 저장 기능을 사용하시겠습니까?")) {
            $("#search > div").show()
            $("#save button img").attr("src","image/switch.png")
            $("#search input[name=save]").attr("value","on")
            location.href="searchSave.jsp?save=on"
        }
    }
}

function delCheck() {
    if(confirm("검색 목록을 모두 삭제하시겠습니까?"))
        location.href='delSearch.jsp?del=all'
}

if(document.location.href.indexOf("minor.jsp") != -1)
    $("#a_minor").css("color", "#ffed44")