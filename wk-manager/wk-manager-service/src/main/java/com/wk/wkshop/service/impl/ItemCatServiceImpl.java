package com.wk.wkshop.service.impl;

import com.wk.wkshop.dao.TbItemCatMapper;
import com.wk.wkshop.pojo.po.TbItemCat;
import com.wk.wkshop.pojo.po.TbItemCatExample;
import com.wk.wkshop.service.ItemCatService;
import com.wk.wkshpo.common.dto.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<TreeNode> listItemCatsByPid(Long parentId) {
        List<TreeNode> treeNodes = null;
        try {
            //要序列化成JSON的列表对象
            treeNodes = new ArrayList<>();
            //创建查询模板
            TbItemCatExample example = new TbItemCatExample();
            TbItemCatExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(parentId);
            //执行查询
            List<TbItemCat> itemCats = tbItemCatMapper.selectByExample(example);
            //遍历原有列表生成新列表
            for (int i = 0; i < itemCats.size(); i++) {
                TbItemCat tbItemCat = itemCats.get(i);
                TreeNode treeNode = new TreeNode();
                treeNode.setId(tbItemCat.getId());
                treeNode.setText(tbItemCat.getName());
                // 数据库的is_parent字段是tinyint(1)，1表示true，0表示false，1表示父节点.是不打开的，0表示子节点.是打开的
                treeNode.setState(tbItemCat.getIsParent() ? "closed" : "open");
                treeNodes.add(treeNode);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return treeNodes;
    }
}
