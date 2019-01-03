import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 多叉树生成、遍历工具
 *
 */
public class ManyNodeTree
{
    /** 树根*/
    private ManyTreeNode root;

    /**
     * 构造函数
     */
    public ManyNodeTree()
    {
        root = new ManyTreeNode(new TreeNode("root"));
    }

    /**
     * 生成一颗多叉树，根节点为root
     *
     * @param treeNodes 生成多叉树的节点集合
     * @return ManyNodeTree
     */
    public ManyNodeTree createTree(List<TreeNode> treeNodes)
    {
        if(treeNodes == null || treeNodes.size() < 0)
            return null;

        ManyNodeTree manyNodeTree =  new ManyNodeTree();

        //将所有节点添加到多叉树中
        for(TreeNode treeNode : treeNodes)
        {
            if(treeNode.getParentId().equals("root"))
            {
                //向根添加一个节点
                manyNodeTree.getRoot().getChildList().add(new ManyTreeNode(treeNode));
               // manyNodeTree.setRoot((new ManyTreeNode(treeNode)));
            }
            else
            {
                addChild(manyNodeTree.getRoot(), treeNode);
            }
        }

        return manyNodeTree;
    }

    /**
     * 向指定多叉树节点添加子节点
     *
     * @param manyTreeNode 多叉树节点
     * @param child 节点
     */
    public void addChild(ManyTreeNode manyTreeNode, TreeNode child)
    {
        for(ManyTreeNode item : manyTreeNode.getChildList())
        {
            if(item.getData().getNodeId().equals(child.getParentId()))
            {
                //找到对应的父亲
                item.getChildList().add(new ManyTreeNode(child));
                break;
            }
            else
            {
                if(item.getChildList() != null && item.getChildList().size() > 0)
                {
                    addChild(item, child);
                }
            }
        }
    }






    public List<List<ManyTreeNode>> levelOrder(ManyTreeNode root) {
        List<List<ManyTreeNode>> list = new ArrayList<List<ManyTreeNode>>();
        if (root == null)
            return list;

        levelOrder(root, 0, list);
        return list;
    }

    public void levelOrder(ManyTreeNode root, int level, List<List<ManyTreeNode>> list) {
        if (root == null)
            return;

        if (list.size() < level +1)
            list.add(new ArrayList<ManyTreeNode>());

        list.get(level).add(root);

        if (root.getChildList() != null)
            for (ManyTreeNode node : root.getChildList())
                levelOrder(node, level + 1, list);
    }



    public ManyTreeNode getRoot() {
        return root;
    }

    public void setRoot(ManyTreeNode root) {
        this.root = root;
    }



}

