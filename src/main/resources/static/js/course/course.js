$(function () {
  $('#confirm').click(function () {
    let formData = new FormData($('#addForm')[0]);
    let url = contextPath + '/course/add';
    $.ajax({
      type: 'POST',
      url: url,
      data: JSON.stringify(Object.fromEntries(formData)),
      contentType: 'application/json;charset=utf-8;',
      success: function () {
        alert("課程建立成功");
      }
    });

  });

  $('#units').keypress(function (e) {
    if ((e.keyCode < 48 || e.keyCode > 57)) return false;
  });

});