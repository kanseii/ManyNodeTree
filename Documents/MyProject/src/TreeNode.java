
/**
 * 树节点
 *
 */
public class TreeNode
{
    /** 节点Id*/
    private String nodeId;
    /** 父节点Id*/
    private String parentId;
    /** 节点值*/
    private int value;


    private boolean isChecked;

    /**
     * 构造函数
     *
     * @param nodeId 节点Id
     */
    public TreeNode(String nodeId)
    {
        this.nodeId = nodeId;
    }

    /**
     * 构造函数
     *
     * @param nodeId 节点Id
     * @param parentId 父节点Id
     */
    public TreeNode(String nodeId, String parentId,int value)
    {
        this.nodeId = nodeId;
        this.parentId = parentId;
        this.value = value;
        this.isChecked = false;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getParentId() {
        return parentId;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value+"";
    }
}

