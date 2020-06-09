$(function () {
    $('#confirm').click(function () {
        let formData = new FormData($('#addForm')[0]);
        let url = '/course-table/add';
        $.ajax({
            type: 'POST',
            url: url,
            data: JSON.stringify(Object.fromEntries(formData)),
            contentType: 'application/json;charset=utf-8;',
            success: function () {
                alert("課表建立成功");
            }
        });

    });

});