
import java.util.ArrayList;
import java.util.List;

/**
 * 多叉树节点
 *
 */
public class ManyTreeNode
{
    /** 树节点*/
    private TreeNode data;
    /** 子树集合*/
    private List<ManyTreeNode> childList;

    /**
     * 构造函数
     *
     * @param data 树节点
     */
    public ManyTreeNode(TreeNode data)
    {
        this.data = data;
        this.childList = new ArrayList<ManyTreeNode>();
    }

    /**
     * 构造函数
     *
     * @param data 树节点
     * @param childList 子树集合
     */
    public ManyTreeNode(TreeNode data, List<ManyTreeNode> childList)
    {
        this.data = data;
        this.childList = childList;
    }

    public TreeNode getData() {
        return data;
    }

    public void setData(TreeNode data) {
        this.data = data;
    }

    public List<ManyTreeNode> getChildList() {
        return childList;
    }

    public void setChildList(List<ManyTreeNode> childList) {
        this.childList = childList;
    }

    public ManyTreeNode findTreeNodeById(String id) {
        if (this.getData().getNodeId() == id)
            return this;
        if (this.getChildList().isEmpty() || this.getChildList() == null) {
            return null;
        } else {
            int childNumber = this.getChildList().size();
            for (int i = 0; i < childNumber; i++) {
                ManyTreeNode child = this.getChildList().get(i);
                ManyTreeNode resultNode = child.findTreeNodeById(id);
                if (resultNode != null) {
                    return resultNode;
                }
            }
            return null;
        }
    }

}

