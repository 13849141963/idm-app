var setting = {
	data: {
		simpleData: {
			enable: true,
			idKey: "functionId",
			pIdKey: "parentId",
			rootPId: -1
		},
		key: {
			url:"nourl"
		}
	},
	check:{
		enable:true,
		nocheckInherit:true
	}
};
var ztree;

//角色ID
var custId = T.p("custId");
//var custNo = T.p("custNo");
var custName = T.p("custName");
var baseId = T.p("baseId");
var vm = new Vue({
	el:'#rrapp',
	data:{
		role:{}
	},
	created: function() {
		//加载菜单树
		$.get("../base/dataCustomer/getBusiCustListByCustId?custId="+custId+"&custName="+custName+"&baseId="+baseId, function(r){
//			if(r.funcList.length==1){
//				alert('没有业务客户', function(index){
//					vm.back();
//				});
//			}
			ztree = $.fn.zTree.init($("#menuTree"), setting, r.funcList);
			//展开所有节点
//			ztree.expandAll(true);
//			if(roleId != null){
//				vm.getRole(roleId);
//			}
		});
    },
	methods: {
		getRole: function(roleId){
            $.get("../base/role/func/"+roleId, function(r){
            	vm.role = r.role;
                //勾选角色所拥有的菜单
    			var functionIds = vm.role.functionIdList;
    			for(var i=0; i<functionIds.length; i++) {
    				var node = ztree.getNodeByParam("functionId", functionIds[i]);
    				ztree.checkNode(node, true, false);
    			}
    		});
		},
		saveOrUpdate: function (event) {
			vm.role.baseId=baseId;
			vm.role.custId=custId;
			//获取选择的菜单
			var nodes = ztree.getCheckedNodes(true);
			var functionIdList = new Array();
			for(var i=0; i<nodes.length; i++) {
				person=new Object();
				person.functionId=nodes[i].functionId;
				person.parentId=nodes[i].parentId;
				person.name=nodes[i].name.split(",")[1];
				functionIdList.push(person);
			}
			vm.role.functionIdList = functionIdList;
			console.log(JSON.stringify(vm.role));
			$.ajax({
				type: "POST",
			    url: "../base/dataCustomer/saveTree",
			    data: JSON.stringify(vm.role),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.back();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		back: function (event) {
			history.go(-1);
		}
	}
});
