$(function () {
    $("#jqGrid").jqGrid({
        url: '../base/role/list',
        datatype: "json",
        colModel: [		
			{ label: '角色ID', name: 'roleId', width: 35, key: true,hidden : true,sortable: false },
			{ label: '角色名称', name: 'roleName', width: 75 },
			{ label: '角色编码', name: 'roleCode', width: 35 },
			/*
			{ label: '公司ID', name: 'corpId', width: 35 },
			{ label: '角色分类', name: 'roleCate', width: 35 },
			{ label: '角色类型', name: 'roleType', width: 35 },
			{ label: '有效状态', name: 'userStatus', width: 45 },
			*/
			{ label: '系统ID', name: 'sysId', width: 45,hidden : true,sortable: false },
			{ label: '所属系统', name: 'roleSysName', width: 65,sortable: false },
			{ label: '角色状态', name: 'roleStatusName', width: 35,sortable: false, formatter: function(value, options, row){
				return value == '关闭' ? 
						'<span class="label label-danger">关闭</span>' : 
						'<span class="label label-success">开启</span>';
				}},
			{ label: '角色状态', name: 'roleStatus', width: 45,hidden : true,sortable: false },
//			{ label: '描述', name: 'roleDesc', width: 100 },
			{ label: '创建人', name: 'creatorName', width: 45,sortable: false },
//			{ label: '操作时间', name: 'operTime', width: 80 },
			/*
			{ label: '排序', name: 'roleOrder', width: 25 },
			*/
			{ label: '创建时间', name: 'buildTime', width: 80}                   
        ],
		viewrecords: true,
        height: 400,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
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
        }
    });
});

/*
function addCellAttr(rowId, val, rawObject, cm, rdata) {
	if (val == '关闭') {
		return "style='background-color: #5cb85c'";
	} else {
		return "style='color:blue'";
	}
}
*/

function reloadGrid(){
	var parm = $("#roleName").val();
	parm = encodeURI(parm);
	var sys = vm.selected1;
	if(parm != "undefined" || sys != "undefined")
		$("#jqGrid").jqGrid('setGridParam', {postData: {roleName:parm,sysId:sys}}).trigger("reloadGrid");
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		baseRoleSysList:{},
		selected1:'-1'
	},
	created: function() {
		this.getDict();
    },
	methods: {
		queryByName: function (event) {
			reloadGrid();
		},
		getDict: function(){
            $.get("../base/role/dict", function(r){
            	vm.baseRoleSysList = r.role.baseRoleSysList;
    		});
		},
		update: function (event) {
			var roleId = getSelectedRow();
			if(roleId == null){
				return ;
			}
			
			location.href = "role_add.html?roleId="+roleId;
		},
		auth: function (event) {
			var roleId = getSelectedRow();
			if(roleId == null){
				return ;
			}
//			if(parent.$("#tabTitle").text() == ""){
//				parent.$("#nav_title").append("<li class='active' id='tabTitle'><a href='http://127.0.0.1:8080/idm-web/base/role_auth.html?roleId="+roleId+"'>角色授权</a></li>");
//			}
			window.location.href = "role_auth.html?roleId="+roleId;
		},
		del: function (event) {
			var roleIds = getSelectedRows();
			if(roleIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../base/role/delete",
				    data: JSON.stringify(roleIds),
				    success: function(r){
						if(r.code == 0){
							if(r.deleteMsg != null && r.deleteMsg != "" && r.deleteMsg != "undefined"){
								alert(r.deleteMsg, function(index){
									$("#jqGrid").trigger("reloadGrid");
								});
							} else {
								alert('操作成功', function(index){
									$("#jqGrid").trigger("reloadGrid");
								});
							}
						}else{
							alert(r.msg);
						}
					}
				});
			});
		}
	}
});