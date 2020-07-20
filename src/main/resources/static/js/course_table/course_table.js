$(function () {
    $('#confirm').click(function () {
        let formData = new FormData($('#addForm')[0]);
        let url = contextPath + '/course-table/add';
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

    $('#addCourseToCourseTable').click(function () {
        let courseId = $('#courseSelect').val();
        let courseTableId = $('#courseTableId').text();
        let url = '/course-table/abc?courseId=' + courseId + '&courseTableId=' + courseTableId;
        $.ajax({
            type: 'GET',
            url: url,
            success: function () {
                alert("新增成功");
            }
        });
    });


});