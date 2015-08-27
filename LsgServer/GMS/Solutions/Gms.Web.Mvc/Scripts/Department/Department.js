
var Department;
if (!Department) {
    Department = {};
}

Department.Init = function () {
 
    $('#department_search_list').treegrid({
        toolbar: '#toolbar',
        idField: 'Id',
        treeField: 'Name',
        columns: [[
        { title: '名称', field: 'Name', width: 100 },
        { title: '备注', field: 'Note', width: 200 }
        ]]
    });
 
    $("#departmentform").validate({
        onkeyup: false,
        onfocusout: false,
        rules: {
            CodeNo: "required",
            psw: "required",
            RealName: "required",
            //Department.Name: "required",
            Mobile: "required"
        },
        messages: {
            LoginName: "请输入工号",
            psw: "请输入密码",
            RealName: "请输入真实姓名",
            //"Department.Name": "请选择班组",
            Mobile: "请输入手机号码"
        }
        , showErrors: function (errorMap, errorList) {
            var message = '请完善以下内容后，再提交。\n';
            var errors = "";
            if (errorList.length > 0) {
                for (x = 0; x < errorList.length; x++) {
                    errors += "<br/>\u25CF " + errorList[x].message;
                }

                $.messager.alert("提示", message + errors);
            }

        }

    });

    //添加
    $('#btnAdd').click(function () {
        var gid = $(this).attr('forgid');
        var grid = $('#' + gid);
        var gform = $('#' + gid.substr(0, gid.length - 4) + 'edit');
        var dlgTitle = "添加" + (gform.attr('dlgtitle') || '');


        var pvalue = $(this).attr("parentid");
   
        var url = grid.attr('edit');

        $.post(url, { id: 0, parentid: pvalue }, function (data) {
            gform.gridform({ data: data.data, title: dlgTitle, forgid: grid,success: function() {
                grid.treegrid('reload');
            } });
        }, 'json');

        return false;

    });

  

    //编辑
    $('#btnEdit').live('click', function () {
        var gid = $(this).attr('forgid');
        var grid = $('#' + gid);
        var goptions = grid.treegrid('options');
        var gform = $('#' + gid.substr(0, gid.length - 4) + 'edit');

        //获取选中的数据
        var rec = grid.treegrid("getSelected");
        if (rec == null) {
            return false;
        }

        if (goptions.onEdit) {
            rec = goptions.onEdit(grid, gform, rec) || rec;
        }
        var title = "编辑" + (gform.attr('dlgtitle') || '');

        var url = grid.attr('edit');
        $.post(url, { id: rec.Id }, function (data) {
            gform.gridform({ data: data.data, title: title, forgid: grid ,success: function() {
                grid.treegrid("reload");
            } });
        }, 'json');

        return false;
    });


    //删除
    $('#btnDel').live('click', function () {

        var grid = $('#' + $(this).attr('forgid'));
        var rec = grid.treegrid("getSelected");
        if (rec == null) {
            return false;
        }

        $.messager.confirm('提示', '确定要删除选中的数据吗？', function (b) {
            if (b) {
                var url = grid.attr('del');
                $.post(url, { id: rec.Id }, function (data) {
                    if (data.success) {

                        $.messager.alert('提示', '删除成功！', 'info', function () {
                            grid.treegrid('reload');
                        });
                    } else {
                        $.messager.alert('错误', '删除失败：' + data.data, 'error');
                    }
                }, 'json');
            }
        });
    });


    //----------------------------------------- 选择上级科室 -------------------------------------//
    $("#selectDept").live("click", function () {

        $("#selcet_dept_dlg_content").attr("src", "/Department/Select");

        var opt = {
            title: '选择上级科室',
            width: 300,
            height: 200,
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: function () {
                    var deptContents = $("#selcet_dept_dlg_content").contents();

                    $("#Parent_Id").val(deptContents.find("#dept_selected_Id").val());
                    $("#Parent_Name").val(deptContents.find("#dept_selected_Name").val());

                    $("#selcet_dept_dlg").dialog('close');
                }
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $("#selcet_dept_dlg").dialog('close');
                }
            }]
        };

        $("#selcet_dept_dlg").show();
        $("#selcet_dept_dlg").dialog(opt);

        return false;

    });
};

Department.Select = function () {

    $('#department_search_list').treegrid({
        idField: 'Id',
        treeField: 'Name',
        columns: [[
        { title: '名称', field: 'Name', width: 100 },
        { title: '备注', field: 'Note', width: 80 }
        ]],
        onClickRow: function (row) {
            if (row != null) {
                $('#dept_selected_Id').val(row.Id);
                $('#dept_selected_Name').val(row.Name);
            }
        }
    });
};


