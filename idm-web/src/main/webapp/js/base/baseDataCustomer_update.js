var dataCustomerId = T.p("dataCustomerId");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增应用",
		baseDataCustomer:{
		}
	},
	created: function() {
		if(dataCustomerId != null){
			this.title = "编辑应用";
			this.getDataCustomerId(dataCustomerId)
		}
    },
	methods: {
		getDataCustomerId: function(dataCustomerId){
			$.get("../base/dataCustomer/info/"+dataCustomerId, function(r){
				vm.baseDataCustomer = r.baseDataCustomer;
			});
		},

		saveOrUpdate: function (event) {
			var url = vm.baseDataCustomer.dataCustomerId == null ? "../base/dataCustomer/save" : "../base/dataCustomer/update";
			console.log(vm.baseDataCustomer.dataCustomerId);
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.baseDataCustomer),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.back();
						});
					}else{
						alert(r.message);
					}
				}
			});
		},
		back: function (event) {
			history.go(-1);
		}
	}
});