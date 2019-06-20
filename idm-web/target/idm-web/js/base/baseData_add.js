var dataId = T.p("dataId");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增应用",
		baseData:{
			state:1,
		}
	},
	created: function() {
		if(dataId != null){
			this.title = "编辑应用";
			this.getSys(dataId)
		}
    },
	methods: {
		getSys: function(dataId){
			$.get("../base/data/info/"+dataId, function(r){
				vm.baseData = r.baseData;
			});
		},

		saveOrUpdate: function (event) {
			var url = vm.baseData.dataId == null ? "../base/data/save" : "../base/data/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.baseData),
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