
var SysLog;
if (!SysLog) {
    SysLog = {};
}

SysLog.Init = function () {

    $('#syslog_search_list').datagridEx({
        toolbar: '#toolbar',
        pagination: true,
        singleSelect: true,
        columns: [
            [
                { field: 'User', title: '工号', width: 80 },
                { field: 'LogInfo', title: '日志', width: 300 },
                { field: 'CreateTime', title: '时间', width: 100 }
            ]
        ]
    });
  


  
};
