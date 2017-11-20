<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>首页</title>
    <!-- 导入easyui的样式表 -->
    <link rel="stylesheet" href="js/jquery-easyui-1.5/themes/bootstrap/easyui.css">
    <link rel="stylesheet" href="js/jquery-easyui-1.5/themes/icon.css">
    //图标
    <link rel="stylesheet" href="css/common.css">
    //图标
</head>
<body class="easyui-layout">
<div data-options="region:'north'" style="height:70px;padding-left:10px;">
    <h2>天天小商城后台管理系统</h2>
</div>
<div data-options="region:'south'" style="padding:5px;background:#eee;">
    系统版本：V2.0
</div>
<div data-options="region:'west'" style="width:200px;">
    <div id="menu" class="easyui-accordion">
        <div title="商品管理" data-options="selected:true,iconCls:'icon-tip'"
             style="padding:10px 0;"><%--selected默认被选中，icon-tip是那个灯泡图--%>
            <%--树--%>
            <ul class="easyui-tree">
                <%--树里有节点--%>
                <li data-options="attributes:{'href':'item-add'}">新增商品</li>
                <li data-options="attributes:{'href':'item-list'}">查询商品</li>
                <li data-options="attributes:{'href':'item-param-list'}">规格参数</li>
            </ul>
        </div>
        <div title="网站内容管理" data-options="iconCls:'icon-tip'" style="padding:10px 0;">
            <ul class="easyui-tree">
                <li data-options="attributes:{'href':'content-category'}">内容分类管理</li>
                <li data-options="attributes:{'href':'content'}">内容管理</li>
            </ul>
        </div>
        <div title="索引库管理" data-options="iconCls:'icon-tip'" style="padding:10px 0;">
            <ul class="easyui-tree">
                <li data-options="attributes:{'href':'index-item'}">solr索引库维护</li>
            </ul>
        </div>
    </div>
</div>
<div data-options="region:'center'" style="background:#eee;">
    <%--选项卡--%>
    <div id="tab" class="easyui-tabs" data-options="fit:true">
        <div title="欢迎页面" style="padding:20px;">qingfeng欢迎你</div>
    </div>
</div>
<!-- jquery -->
<script src="js/jquery-easyui-1.5/jquery.min.js"></script>
<!-- jquery easyui -->
<script src="js/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script src="js/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>

<!--引入日期处理库-->
<script src="js/moment/moment-with-locales.js"></script>
<script>
    moment.locale('zh-cn');
</script>

<!-- ueditor配置文件 -->
<script type="text/javascript" src="js/ueditor/ueditor.config.js"></script>
<!-- ueditor编辑器源码文件 -->
<script type="text/javascript" src="js/ueditor/ueditor.all.js"></script>

<!-- 自定义脚本 -->
<script src="js/common.js"></script>
<!-- 自定义js -->
<script>
    wkshop.registerMenuEvent();
    /*$(function () {
        //约定大于配置：jquery对象前面加上$,如果是DOM对象不需要加$
        var $tree = $('#menu .easyui-tree');
        //将当前树打印到控制台
        console.log($tree);
//        给3个树中每个节点添加事件或其他东西
        $tree.tree({
//            树中节点的点击事件
            onClick: function (node) {
                var href = node.attributes.href;//item-add
                var text = node.text;//节点的内容，例如新增商品
//                debugger;
//                添加一个选项卡
                $('#tab').tabs('add', {
                    title: text,
                    href: href, //从URL加载远程数据内容填充到选项卡面板
                    closable: true //可以关闭
                });
            }
        });
    });*/
</script>
<%--每次上传都是在这里执行了--%>
<script>
    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
    UE.Editor.prototype.getActionUrl = function (action) {
        if (action == 'uploadimage') {
            return 'http://localhost:8080/wkshop/file/uploadimage';
        } else if (action == 'uploadvideo') {
            return 'http://localhost:8080/wkshop/file/uploadvideo';
        } else {
            return this._bkGetActionUrl.call(this, action);
        }
    }
</script>
</body>
</html>