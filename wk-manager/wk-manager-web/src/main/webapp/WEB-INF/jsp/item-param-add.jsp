<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="easyui-panel" title="商品规格参数模板详情" data-options="fit:true">
    <form class="form" id="itemParamAddForm" name="itemParamAddForm" method="post">
        <table style="width:100%;">
            <tr>
                <td class="label">商品类目：</td>
                <td>
                    <input id="cid" name="cid" style="width:200px;">
                </td>
            </tr>
            <tr>
                <td class="label">规格参数：</td>
                <td>
                    <button class="easyui-linkbutton" onclick="addGroup()" type="button"
                            data-options="iconCls:'icon-add'">添加分组
                    </button>
                    <ul id="item-param-group">

                    </ul>


                    <ul id="item-param-group-template" style="display:none;">

                        <li>
                            <input name="group">
                            <button title="添加参数" class="easyui-linkbutton" onclick="addParam(this)" type="button"
                                    data-options="iconCls:'icon-add'"></button>
                            <button title="删除分组" class="easyui-linkbutton" onclick="delGroup(this)" type="button"
                                    data-options="iconCls:'icon-cancel'"></button>
                            <ul class="item-param">
                                <li>
                                    <input name="param">
                                    <button title="删除参数" class="easyui-linkbutton" onclick="delParam(this)"
                                            type="button" data-options="iconCls:'icon-cancel'"></button>
                                </li>
                            </ul>
                        </li>

                    </ul>


                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <button class="easyui-linkbutton" onclick="submitForm()" type="button"
                            data-options="iconCls:'icon-ok'">保存
                    </button>
                    <button class="easyui-linkbutton" onclick="clearForm()" type="button"
                            data-options="iconCls:'icon-undo'">重置
                    </button>
                </td>
            </tr>
        </table>
    </form>
</div>
<script>

    //加载商品类目的树形下拉框
    $("#cid").combotree({
        url: 'itemCats?parentId=0', /*在数据库中0没有父节点了，是顶级的*/
        required: true,
        onBeforeExpand: function (node) {
            //获取当前被点击的tree
            var $currentTree = $('#cid').combotree('tree');
            //调用easyui tree组件的options方法
            var option = $currentTree.tree('options');
            //修改option的url属性
            option.url = 'itemCats?parentId=' + node.id;
        },
        onBeforeSelect: function (node) {
            var isLeaf = $('#cid').tree('isLeaf', node.target);//判断指定的节点是否是叶子节点，target参数是一个节点DOM对象
            if (!isLeaf) {
                $.messager.alert('警告', '请选择最终类目', 'warning');
                return false;
            }
        }
    });

    //添加分组
    function addGroup() {
        //这里一定要用克隆，否则把隐藏域的li放到ul里去了，位置移动了
        var $templateLi = $("#item-param-group-template li").eq(0).clone();
        $("#item-param-group").append($templateLi);
    }

    //删除分组
    function delGroup(ele) {
        $(ele).parent().remove();
    }

    //添加参数
    function addParam(ele) {
        var $paramLi = $("#item-param-group-template .item-param li").eq(0).clone();
        //注意find的用法
        $(ele).parent().find(".item-param").append($paramLi);
    }

    //删除参数
    function delParam(ele) {
        $(ele).parent().remove();
    }

    //清空表单
    function clearForm() {
        $("#itemParamAddForm").form('reset');
    }

    //保存
    function submitForm() {
        var groupValues = [];
        //遍历分组
        var $groups = $('#item-param-group input[name=group]');
        $groups.each(function (index, item) {
            //debugger;
            //遍历分组项
            var groupName = $(item).val();//group名
            var paramValues = [];
            var $params = $(item).parent().find('.item-param input[name=param]')
            $params.each(function (_index, _item) {
                var paramName = $(_item).val();//param名
                if ($.trim(paramName).length > 0) {
                    paramValues.push(paramName);
                }
            });
            var o = {};
            o.group = groupName;
            o.params = paramValues;
            if ($.trim(groupName).length > 0 && paramValues.length > 0) {
                groupValues.push(o);
            }
        });

        //得到规格参数模板json串
        console.log(groupValues);
        var cid = $('#cid').combotree('getValue');
        var jsonStr = JSON.stringify(groupValues);
        $.post(
            "itemPram",
            {paramData: jsonStr, cid: cid},
            function (data) {
                alert(data);
                if (data > 0) {
                    $.messager.alert('消息', '添加成功！', 'info');
                    wkshop.closeTab('新增商品规格模板');
                    wkshop.addTab('规格参数', 'item-param-list');
                } else {
                    $.messager.alert('消息', '添加失败！', 'warning');
                }
            }
        );
        //var url = 'item/param/save/' + cid;
    }

</script>