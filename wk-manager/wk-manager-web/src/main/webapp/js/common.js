var wkshop = {

    //点击左侧导航树上的结点，添加一个选项卡
    //点击导航树上的动作
    registerMenuEvent: function () {
        var _this = this;
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
                _this.addTab(text, href);
            }
        });
    },
    //添加选项卡
    addTab: function (title, href) {
        if ($('#tab').tabs('exists', title)) {
            $('#tab').tabs('select', title);
        } else {
            $('#tab').tabs('add', {
                title: title,//选项卡面板的标题文本
                href: href,//从URL加载远程数据内容填充到选项卡面板
                closable: true//可以关闭
            });
        }
    },
    //关闭选项卡
    closeTab: function (title) {
        $('#tab').tabs('close', title);
    }
};



