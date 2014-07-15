$(document).ready(function() {
    var $this = $(this);
    $(".removeLink").on("click", function() {
        $.ajax({
            type: "DELETE",
            url: $(this).data("remove-uri") + "/" + $(this).data("remove-id"),
            beforeSend: function(xhr) {
                var header = $("meta[name='_csrf_header']").attr("content");
                var token = $("meta[name='_csrf']").attr("content");
                xhr.setRequestHeader(header, token);
            }
        }).done(function() {
            alert("Registro apagado com sucesso !");
            location.reload();
        });
    });
});
