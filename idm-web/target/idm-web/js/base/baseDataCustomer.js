var baseId = T.p("baseId");
$(function () {
    $("#jqGrid").jqGrid({
        url: '../base/dataCustomer/list',
        datatype: "json",
        postData:{"baseId" : baseId,"customerParentId":"-1"},
        colModel: [			
			{ label: '数据集权限与客户关联id', name: 'dataCustomerId',hidden:true},
			{ label: '客户id', name: 'customerId',key: true},
/*			{ label: '数据集权限id', name: 'baseId'},*/
			{ label: '客户名称', name: 'customerName'}
/*			{ label: '客户父id', name: 'customerParentId'}*/
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
//		update: function (event) {
//			var dataCustomerId = getSelectedRow();
//			if(dataCustomerId == null){
//				return ;
//			}
//			location.href = "baseDataCustomer_add.html?dataCustomerId="+dataCustomerId;
//		},
		update: function (event) {
			var custId = getSelectedRow();
			if(custId == null){
				return ;
			}
			var rowObj = $("#jqGrid").jqGrid('getRowData',custId);
			location.href = "baseDataCustomer_tianjiatree.html?custId="+custId+"&custName="+escape(rowObj.customerName)+"&baseId="+baseId;
		},
		del: function (event) {
			var dataCustomerId = getSelectedRows();
			if(dataCustomerId == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../base/dataCustomer/deleteTree",
				    data: JSON.stringify({"baseId":baseId,"custId":dataCustomerId[0]}),
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
		back: function (event) {
			history.go(-1);
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