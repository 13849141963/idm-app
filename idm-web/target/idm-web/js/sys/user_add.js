//用户ID
var userId = T.p("userId");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增管理员",
		selectRoleList:{},
		selectCropList:{},
		user:{
			status:1,
			sysIdList:[],
			roleIdList:[]
		}
	},
	created: function() {
		if(userId != null){
			this.title = "修改管理员";
			this.getUser(userId)
		}
		//获取角色信息
		this.getRoleList();
		this.getCropList();
    },
	methods: {
		getUser: function(userId){
			$.get("../sys/user/info/"+userId, function(r){
				vm.user = r.user;
			});
		},
		getRoleList: function(){
			$.get("../sys/role/select", function(r){
				vm.selectRoleList = r.list;
			});
		},
		getCropList: function(){
			$.get("../base/baseSys/selectSys", function(r){
				vm.selectCropList = r.list;
			});
		},
		saveOrUpdate: function (event) {
			var url = vm.user.userId == null ? "../sys/user/save" : "../sys/user/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.user),
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