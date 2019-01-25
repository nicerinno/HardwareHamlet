
var prevScrollpos = window.pageYOffset;
window.onscroll = function() {
    var currentScrollPos = window.pageYOffset;
    if (prevScrollpos > currentScrollPos) {
        document.getElementById("header").style.top = "0";
    } else {
        document.getElementById("header").style.top = "-200px";
    }
    prevScrollpos = currentScrollPos;
};


$( document ).ready(function(){
    $.post(
        "get_components.php",
        {
            id : 0
        },
        function(data, status){
            if (status == "success")
            {
                var objData = JSON.parse(data);
                for (item in objData) {

                    $("tbody#componentTable").append(
                        "<tr>" +
                            "<td><a  href='components_details.php?id=" + objData[item].id + "'>" + objData[item].name + " </a></td>" +
                            "<td>" + objData[item].price + "€" + "</td>" +
                            "<td>" + "<img src='" + objData[item].icon_url + "' alt='componente' height='100'></td>" +
                        "</tr>"
                    );
                }
            }
        }
    )
});


$("input[name=optradio]").change(function(){
    var id = $( "input[name=optradio]:checked" ).val();

    <!-- limpa lista -->
    $( "tbody#componentTable" ).html("");


    $.post(
        "get_components.php",
        {
            id : id
        },

        function(data, status) {
            if (status == "success") {
                var objData = JSON.parse(data);
                for (item in objData) {

                    $("tbody#componentTable").append(
                        "<tr>" +
                            "<td><a href='components_details.php?id=" + objData[item].id + "'>" + objData[item].name + "</a></td>" +
                            "<td>" + objData[item].price + "€" + "</td>" +
                            "<td>" + "<img src='" + objData[item].icon_url + "' alt='componente' height='100'></td>" +
                        "</tr>"
                    );
                }
            }
          }
        )
});




