layui.use(['element','layer'], function () {
    var $ = layui.jquery;
    var layer = layui.layer;
    /* AJAX请求默认选项，处理连接超时问题 */
    $.ajaxSetup({
        beforeSend: function (request) {
            layer.load(0);
        },
        complete: function (xhr) {
        	debugger;
            layer.closeAll('loading');
            if (xhr.status === 401) {
                layer.confirm('session连接超时，是否重新登录？', {
                    btn: ['是', '否']
                }, function () {
                    if (window.parent.window !== window) {
                        window.top.location = window.location.pathname + '/login';
                    }
                });
            }else if(xhr.status === 500) {
            	
            }else if(xhr.status === 403) {
            	layer.msg('用户存在没有权限数据请求，请联系管理员进行授权');
            }
        }
    });
    
    //单击行勾选checkbox事件
    $(document).on("click", ".layui-table-body table.layui-table tbody tr", function () {
        var index = $(this).attr('data-index');
        var tableBox = $(this).parents('.layui-table-box');
        //存在固定列
        if (tableBox.find(".layui-table-fixed.layui-table-fixed-l").length > 0) {
            tableDiv = tableBox.find(".layui-table-fixed.layui-table-fixed-l");
        } else {
            tableDiv = tableBox.find(".layui-table-body.layui-table-main");
        }
        var CheckLength = tableDiv.find("tr[data-index=" + index + "]").find(
            "td div.layui-form-checked").length;
      
        var checkCell = tableDiv.find("tr[data-index=" + index + "]").find(
            "td div.laytable-cell-checkbox div.layui-form-checkbox I");
        if (checkCell.length > 0) {
            checkCell.click();
        }
    });
    $(document).on("click", "td div.laytable-cell-checkbox div.layui-form-checkbox", function (e) {
        e.stopPropagation();
    });
    
   
    $.fn.openIframeWin = function(url,title,area,callback){
    	 parent.layer.open({
  	        type: 2
  	        ,title: title //不显示标题栏
  	        ,closeBtn: 2
  	        ,area: area
  	        ,shade: 0.3
  	        ,btnAlign: 'c'
  	        ,moveType: 1 //拖拽模式，0或者1
  	        ,content: url
  	        ,end: callback
  	     });
     };
    
    
});