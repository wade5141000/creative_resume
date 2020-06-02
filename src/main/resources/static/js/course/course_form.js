$(function () {
    $('#confirm').click(function () {
        let formData = new FormData($('#courseForm')[0]);
        let url = '/course/add';
        $.ajax({
            type: 'POST',
            url: url,
            data: JSON.stringify(Object.fromEntries(formData)),
            contentType: 'application/json;charset=utf-8;',
            success: function () {
                alert("ajax 成功");
            }
        });

    });
});