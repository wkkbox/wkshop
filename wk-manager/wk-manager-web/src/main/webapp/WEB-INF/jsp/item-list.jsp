<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%--<script>
    //添加工具栏
    var toolbar = [{
        iconCls: 'icon-add',
        text: '新增',
        handler: function () {
            console.log('add');
        }
    }, {
        iconCls: 'icon-remove',
        text: '删除',
        handler: function () {
            //console.log('remove');
            var selections = $("#dg").datagrid('getSelections');
            console.log(selections);
            if (selections.length == 0) {
                //客户没有选择记录
                $.messager.alert('提示', '请至少选中一条记录！', 'warning');
                return;//结束函数
            }
            //至少选中了一条记录
            //确认框，第一个参数为标题，第二个参数确认框的提示内容，第三参数是一个确认函数
            //function(r) 如果用户点击的是"确认"，那么r=true，否则r=false
            $.messager.confirm('确认', '您确认想要删除记录吗？', function (r) {
                if (r) {
                    //为了存放id的集合
                    var ids = [];
                    //遍历选中的记录，将记录的id存放到js数组中
                    for (var i = 0; i < selections.length; i++) {
                        ids.push(selections[i].id);
                    }
                    //把ids异步提交到后台
                    $.post(
                        //url:请求后台的哪个地址来进行处理，相当于url,String类型
                        'items/removebatch',
                        //data:从前台提交哪些请求数据给后台处理，相当于data，Object类型
                        //注意这里要是ids[]，方括号不能少，后台接收请求参数时也有跟这一样的处理
                        //是为了把数组转换成list而做的操作
                        {'ids[]': ids},
                        //callback:后台处理成功的回调函数，相当于success，function类型
                        function (data) {
                            console.log(data);
                            $("#dg").datagrid('reload');//load和reload的区别是reload刷新后还在当前页，而load刷新后在第1页
                        },
                        //dataType:返回的数据类型，如：json，String类型
                        'json'
                    );
                }
            });
        }
    }, {
        iconCls: 'icon-edit',
        text: '编辑',
        handler: function () {
            console.log('edit');
        }
    }, {
        iconCls: 'icon-up',
        text: '上架',
        handler: function () {
            console.log('up');
            var selections = $("#dg").datagrid('getSelections');
            if (selections.length == 0) {
                //客户没有选择记录
                $.messager.alert('提示', '请至少选中一条记录！', 'warning');
                return;//结束函数
            }
            for (var i = 0; i < selections.length; i++) {
                if (selections[i].status == 1) {
                    //把商品上架，但客户选择了已经上架的记录
                    $.messager.alert('提示', '所选记录中存在已上架商品！', 'warning');
                    return;//结束函数
                }
            }
            //至少选中了一条记录
            //确认框，第一个参数为标题，第二个参数确认框的提示内容，第三参数是一个确认函数
            //function(r) 如果用户点击的是"确认"，那么r=true，否则r=false
            $.messager.confirm('确认', '您确认上架商品吗？', function (r) {
                if (r) {
                    //为了存放id的集合
                    var ids = [];
                    //遍历选中的记录，将记录的id存放到js数组中
                    for (var i = 0; i < selections.length; i++) {
                        ids.push(selections[i].id);
                    }
                    //把ids异步提交到后台
                    $.post(
                        //url:请求后台的哪个地址来进行处理，相当于url,String类型
                        'items/upbatch',
                        //data:从前台提交哪些请求数据给后台处理，相当于data，Object类型
                        //注意这里要是ids[]，方括号不能少，后台接收请求参数时也有跟这一样的处理
                        //是为了把数组转换成list而做的操作
                        {'ids[]': ids},
                        //callback:后台处理成功的回调函数，相当于success，function类型
                        function (data) {
                            console.log(data);
                            $("#dg").datagrid('reload');//load和reload的区别是reload刷新后还在当前页，而load刷新后在第1页
                        },
                        //dataType:返回的数据类型，如：json，String类型
                        'json'
                    );
                }
            });
        }
    }, {
        iconCls: 'icon-down',
        text: '下架',
        handler: function () {
            console.log('down');
            var selections = $("#dg").datagrid('getSelections');
            if (selections.length == 0) {
                //客户没有选择记录
                $.messager.alert('提示', '请至少选中一条记录！', 'warning');
                return;//结束函数
            }
            for (var i = 0; i < selections.length; i++) {
                if (selections[i].status == 2) {
                    //把商品下架，但客户选择了已经下架的记录
                    $.messager.alert('提示', '所选记录中存在已下架商品！', 'warning');
                    return;//结束函数
                }
            }
            //至少选中了一条记录
            //确认框，第一个参数为标题，第二个参数确认框的提示内容，第三参数是一个确认函数
            //function(r) 如果用户点击的是"确认"，那么r=true，否则r=false
            $.messager.confirm('确认', '您确认下架商品吗？', function (r) {
                if (r) {
                    //为了存放id的集合
                    var ids = [];
                    //遍历选中的记录，将记录的id存放到js数组中
                    for (var i = 0; i < selections.length; i++) {
                        ids.push(selections[i].id);
                    }
                    //把ids异步提交到后台
                    $.post(
                        //url:请求后台的哪个地址来进行处理，相当于url,String类型
                        'items/downbatch',
                        //data:从前台提交哪些请求数据给后台处理，相当于data，Object类型
                        //注意这里要是ids[]，方括号不能少，后台接收请求参数时也有跟这一样的处理
                        //是为了把数组转换成List<Long> ids而做的操作，方便mybatis反向生成的函数criteria.andIdIn(ids);
                        {'ids[]': ids},
                        //callback:后台处理成功的回调函数，相当于success，function类型
                        function (data) {
                            console.log(data);
                            $("#dg").datagrid('reload');//load和reload的区别是reload刷新后还在当前页，而load刷新后在第1页
                        },
                        //dataType:返回的数据类型，如：json，String类型
                        'json'
                    );
                }
            });
        }
    }];
</script>--%>
<div id="toolbar">
    <div style="padding: 5px; background-color: #fff;">
        <label>商品标题：</label>
        <input class="easyui-textbox" type="text" id="title">
        <label>商品状态：</label>
        <select id="status" class="easyui-combobox">
            <option value="0">全部</option>
            <option value="1">正常</option>
            <option value="2">下架</option>
        </select>
        <!--http://www.cnblogs.com/wisdomoon/p/3330856.html-->
        <!--注意：要加上type="button",默认行为是submit-->
        <button onclick="searchForm()" type="button" class="easyui-linkbutton">搜索</button>
    </div>
    <div>
        <button onclick="add()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</button>
        <button onclick="edit()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</button>
        <button onclick="remove()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</button>
        <button onclick="down()" class="easyui-linkbutton" data-options="iconCls:'icon-down',plain:true">下架</button>
        <button onclick="up()" class="easyui-linkbutton" data-options="iconCls:'icon-up',plain:true">上架</button>
    </div>
</div>
<table id="dg"></table>
<script>
    function add() {
        //alert('add');
        wkshop.addTab('新增商品','item-add');
    }

    function remove() {
        //console.log('remove');
        var selections = $("#dg").datagrid('getSelections');
        console.log(selections);
        if (selections.length == 0) {
            //客户没有选择记录
            $.messager.alert('提示', '请至少选中一条记录！', 'warning');
            return;//结束函数
        }
        //至少选中了一条记录
        //确认框，第一个参数为标题，第二个参数确认框的提示内容，第三参数是一个确认函数
        //function(r) 如果用户点击的是"确认"，那么r=true，否则r=false
        $.messager.confirm('确认', '您确认想要删除记录吗？', function (r) {
            if (r) {
                //为了存放id的集合
                var ids = [];
                //遍历选中的记录，将记录的id存放到js数组中
                for (var i = 0; i < selections.length; i++) {
                    ids.push(selections[i].id);
                }
                //把ids异步提交到后台
                $.post(
                    //url:请求后台的哪个地址来进行处理，相当于url,String类型
                    'items/removeItemBatch',
                    //data:从前台提交哪些请求数据给后台处理，相当于data，Object类型
                    //注意这里要是ids[]，方括号不能少，后台接收请求参数时也有跟这一样的处理
                    //是为了把数组转换成list而做的操作
                    {'ids[]': ids},
                    //callback:后台处理成功的回调函数，相当于success，function类型
                    function (data) {
                        console.log(data);
                        $("#dg").datagrid('reload');//load和reload的区别是reload刷新后还在当前页，而load刷新后在第1页
                    },
                    //dataType:返回的数据类型，如：json，String类型
                    'json'
                );
            }
        });
    }

    function edit() {
        alert('edit');
    }

    function down() {
        console.log('down');
        var selections = $("#dg").datagrid('getSelections');
        if (selections.length == 0) {
            //客户没有选择记录
            $.messager.alert('提示', '请至少选中一条记录！', 'warning');
            return;//结束函数
        }
        for (var i = 0; i < selections.length; i++) {
            if (selections[i].status == 2) {
                //把商品下架，但客户选择了已经下架的记录
                $.messager.alert('提示', '所选记录中存在已下架商品！', 'warning');
                return;//结束函数
            }
        }
        //至少选中了一条记录
        //确认框，第一个参数为标题，第二个参数确认框的提示内容，第三参数是一个确认函数
        //function(r) 如果用户点击的是"确认"，那么r=true，否则r=false
        $.messager.confirm('确认', '您确认下架商品吗？', function (r) {
            if (r) {
                //为了存放id的集合
                var ids = [];
                //遍历选中的记录，将记录的id存放到js数组中
                for (var i = 0; i < selections.length; i++) {
                    ids.push(selections[i].id);
                }
                //把ids异步提交到后台
                $.post(
                    //url:请求后台的哪个地址来进行处理，相当于url,String类型
                    'items/downItemBatch',
                    //data:从前台提交哪些请求数据给后台处理，相当于data，Object类型
                    //注意这里要是ids[]，方括号不能少，后台接收请求参数时也有跟这一样的处理
                    //是为了把数组转换成List<Long> ids而做的操作，方便mybatis反向生成的函数criteria.andIdIn(ids);
                    {'ids[]': ids},
                    //callback:后台处理成功的回调函数，相当于success，function类型
                    function (data) {
                        console.log(data);
                        $("#dg").datagrid('reload');//load和reload的区别是reload刷新后还在当前页，而load刷新后在第1页
                    },
                    //dataType:返回的数据类型，如：json，String类型
                    'json'
                );
            }
        });
    }

    function up() {
        console.log('up');
        var selections = $("#dg").datagrid('getSelections');
        if (selections.length == 0) {
            //客户没有选择记录
            $.messager.alert('提示', '请至少选中一条记录！', 'warning');
            return;//结束函数
        }
        for (var i = 0; i < selections.length; i++) {
            if (selections[i].status == 1) {
                //把商品上架，但客户选择了已经上架的记录
                $.messager.alert('提示', '所选记录中存在已上架商品！', 'warning');
                return;//结束函数
            }
        }
        //至少选中了一条记录
        //确认框，第一个参数为标题，第二个参数确认框的提示内容，第三参数是一个确认函数
        //function(r) 如果用户点击的是"确认"，那么r=true，否则r=false
        $.messager.confirm('确认', '您确认上架商品吗？', function (r) {
            if (r) {
                //为了存放id的集合
                var ids = [];
                //遍历选中的记录，将记录的id存放到js数组中
                for (var i = 0; i < selections.length; i++) {
                    ids.push(selections[i].id);
                }
                //把ids异步提交到后台
                $.post(
                    //url:请求后台的哪个地址来进行处理，相当于url,String类型
                    'items/upItemBatch',
                    //data:从前台提交哪些请求数据给后台处理，相当于data，Object类型
                    //注意这里要是ids[]，方括号不能少，后台接收请求参数时也有跟这一样的处理
                    //是为了把数组转换成list而做的操作
                    {'ids[]': ids},
                    //callback:后台处理成功的回调函数，相当于success，function类型
                    function (data) {
                        console.log(data);
                        $("#dg").datagrid('reload');//load和reload的区别是reload刷新后还在当前页，而load刷新后在第1页
                    },
                    //dataType:返回的数据类型，如：json，String类型
                    'json'
                );
            }
        });
    }

    function searchForm() {
        console.log($("#title").val().trim() + "..." + $("#status").combobox('getValue'));
        $('#dg').datagrid('load', {//load和reload的区别是reload刷新后还在当前页，而load刷新后在第1页
            title: $("#title").val().trim(),
            status: $("#status").combobox('getValue')
        });
    }
</script>
<script>
    //初始化数据表格
    $('#dg').datagrid({
        multiSort: true,//定义是否允许多列排序
        fitColumns: true,//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动
        url: 'items', //一个URL从远程站点请求数据,返回的是json数据，用于列属性展示数据，http://localhost:8080/wkshop/items
        toolbar: '#toolbar',//将工具栏添加到数据表格中
        striped: true, //是否显示斑马线效果,隔行变色
        pagination: true,//添加分页工具栏
        rownumbers: true,//每行的前面显示行号
        fit: true,//使得数据表格自适应填充父容器
        //method: 'get',//该方法类型请求远程数据，默认post
        pageNumber: 1,//在设置分页属性的时候初始化页码,默认第1页
        pageSize: 20,//在设置分页属性的时候初始化页面大小,默认显示10条，这样的话就显示20条
        //分页列表
        pageList: [20, 50, 100],//在设置分页属性的时候 初始化页面大小选择列表
        //列属性
        columns: [[   //DataGrid列配置对象，根据json数据来编写
            //field title width列属性
            {field: 'ck', checkbox: true},
            {field: 'id', title: '商品编号', width: 100},//列属性field,title等
            {field: 'title', title: '商品名称', width: 100, sortable: true},
            {field: 'sellPoint', title: '卖点', width: 100},
            /*{field: 'status', title: '状态', width: 100}*/
            {
                field: 'status', title: '状态', width: 100, formatter: function (value, row, index) {
                /*console.group();
                console.log(value);
                console.log(row);
                console.log(index);
                console.groupEnd();*/
                switch (value) {
                    case 1 :
                        return "正常";
                        break;
                    case 2:
                        return "下架";
                        break;
                    case 3:
                        return "删除";
                        break;
                    default:
                        return "未知";
                        break;
                }
            }
            },
            {field: 'catName', title: '分类名称', width: 100},
            /*{
                field: 'price', title: '价格', width: 100, formatter: function (value, row, index) {
                return value / 100 + '元';
            }
            },*/
            /*{field: 'price', title: '价格', width: 100},*/
            {field: 'priceView', title: '价格', width: 100},
            {
                field: 'created', title: '创建时间', width: 100, sortable: true, formatter: function (value, row, index) {
                return moment(value).format('LLL');
            }
            },
            {
                field: 'updated', title: '更新时间', width: 100, formatter: function (value, row, index) {
                return moment(value).format('LLL');
            }
            }
        ]]
    })
    ;
</script>
