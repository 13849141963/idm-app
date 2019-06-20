var userId = T.p("userId");
$(function () {
    $("#jqGrid").jqGrid({
        url: '../base/baseUserData/list',
        datatype: "json",
        colModel: [			
			{ label: 'dataId', name: 'dataId', width: 45, key: true,hidden :true},
			{ label: '权限集名字', name: 'dataName', width: 90 },
			{ label: '权限集类型', name: 'dataType', width: 75 ,hidden :true},
			{ label: '创建时间', name: 'dataCreateTime', width: 100 },
        ],
		viewrecords: true,
        height: 400,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 30, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order",
            sidx: "sidx"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        	jQuery("#jqGrid").setGridParam().hideCol("dataId").trigger("reloadGrid");
        	jQuery("#jqGrid").setGridParam().hideCol("operTime").trigger("reloadGrid");
        }
    });
});
var vm = new Vue({
	el:'#rrapp',
	data:{
		
	},
	methods: {
		update: function (event) {
			var dataId = getSelectedRow();
			if(dataId == null){
				return ;
			}
			
			location.href = "baseData_add.html?dataId="+dataId;
		},
		del: function (event) {
			var dataIds = getSelectedRows();
			if(dataIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../base/data/delete",
				    data: JSON.stringify(dataIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		//添加权限
        saveUserData:function (event) {
            var dataIds = getSelectedRows();
            if(dataIds == null){
                return ;
            }
            $.ajax({
                type: "GET",
                url: "../base/baseUserData/save?dataIds="+dataIds+"&userId="+userId,
                //data: {dataIds:dataIds,userId:userId},
                //data:JSON.stringify(userId,dataIds),
                datatype: "json",
                success: function(r){
                    if(r.code == 0){
                        alert('操作成功', function(index){
                            //返回主页面
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });

        },
		changeState: function (event) {
			var dataIds = getSelectedRows();
			if(dataIds == null){
				return ;
			}
			
			confirm('确定要改变选中的应用的状态？', function(){
				$.ajax({
					type: "POST",
					url: "../base/baseData/changeState",
					data: JSON.stringify(dataIds),
					success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		}
	}
});