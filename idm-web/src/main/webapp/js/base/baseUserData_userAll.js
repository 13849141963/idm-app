var userId = T.p("userId");
var vm = new Vue({
    el:'#rrapp',
    data:{
        title:"新增应用",
        baseUserData:{
            state:1,
			userId:'',
        }
    },
    created: function() {

    },
    methods: {
        del: function (event) {
            var user_base_ids = getSelectedRows();
            if(user_base_ids == null){
                return ;
            }

            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: "../base/baseUserData/delete",
                    data: JSON.stringify(user_base_ids),
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
    }
});
$(function () {
    $("#jqGrid").jqGrid({
        url: '../base/baseUserData/userData?userId='+userId,
        datatype: "json",
        colModel: [
            { label: 'user_base_id', name: 'user_base_id', width: 45, key: true ,hidden :true},
            { label: '权限集名称', name: 'dataName', width: 75},
            { label: '权限集类型', name: 'dataType', width: 90}
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
        }
    });
});
