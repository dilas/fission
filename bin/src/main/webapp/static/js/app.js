$(document).ready(function() {
    var $this = $(this);
    $(".removeLink").on("click", function() {
        $.ajax({
            type: "DELETE",
            url: $(this).data("remove-uri") + "/" + $(this).data("remove-id")
        }).done(function() {
            alert("Registro apagado com sucesso !");
            location.reload();
        });
    });
});
