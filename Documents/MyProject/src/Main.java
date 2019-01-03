import java.util.*;

public class Main {

    public static boolean flag = true;
    public static List<List<TreeNode>> myList = new ArrayList<List<TreeNode>>();
    public static ManyTreeNode root;
    public static boolean isChanged = false;
    public static void main(String[] args)
    {

        List<TreeNode> treeNodes;
        ManyNodeTree tree = new ManyNodeTree();

        treeNodes = new ArrayList<TreeNode>();
        treeNodes.add(new TreeNode("ROOT", "root",1));
        treeNodes.add(new TreeNode("A", "ROOT",0));
        treeNodes.add(new TreeNode("F", "ROOT",1));
        treeNodes.add(new TreeNode("B", "A",0));
        treeNodes.add(new TreeNode("C", "A",0));
        treeNodes.add(new TreeNode("D", "A",0));
        treeNodes.add(new TreeNode("E", "A",0));
        treeNodes.add(new TreeNode("O", "F",1));
        treeNodes.add(new TreeNode("P", "F",0));
        treeNodes.add(new TreeNode("G", "B",0));
        treeNodes.add(new TreeNode("H", "B",1));
        treeNodes.add(new TreeNode("T", "C",0));
        treeNodes.add(new TreeNode("R", "D",1));
        treeNodes.add(new TreeNode("S", "D",0));
        treeNodes.add(new TreeNode("Q", "E",0));

//        treeNodes.add(new TreeNode("I", "G",0));
//        treeNodes.add(new TreeNode("J", "G",0));
//        treeNodes.add(new TreeNode("K", "G",0));
//        treeNodes.add(new TreeNode("L", "T",0));
//        treeNodes.add(new TreeNode("M", "R",0));
//        treeNodes.add(new TreeNode("N", "S",0));
//        treeNodes.add(new TreeNode("U", "Q",1));
//        treeNodes.add(new TreeNode("V", "Q",1));
//        treeNodes.add(new TreeNode("X", "L",0));
//        treeNodes.add(new TreeNode("W", "N",0));
//        treeNodes.add(new TreeNode("Y", "X",0));
//        treeNodes.add(new TreeNode("Z", "X",1));


        root = tree.createTree(treeNodes).getRoot();
        List<List<ManyTreeNode>> list = tree.levelOrder(root);
        List<TreeNode> tmpList = new ArrayList<TreeNode>();
        for (int i=0;i<list.size();i++){              //过滤初始的根节点
            for (int j=0;j<list.get(i).size();j++){
                List<ManyTreeNode> treeNodeList = list.get(i).get(j).getChildList();
                for (int k=0;k<treeNodeList.size();k++){
                    tmpList.add(treeNodeList.get(k).getData());
                }
                myList.add(tmpList);
                tmpList = new ArrayList<TreeNode>();
            }

        }



        String NodeId = "T";
        String res = "";
        String ans = "";
        List<String> resList = new ArrayList<String>();
        while (flag){
            isChanged = false;
            String tmp = NodeId;
            if(!flag) break;
            res = leftPath(NodeId);
            System.out.println(res);
            if(!flag) break;
            res = rightPath(NodeId);
            System.out.println(res);
            if(!flag) break;
            res = downPath(NodeId);
            System.out.println(res);
            if(!flag) break;
            ManyTreeNode manyTreeNode = root.findTreeNodeById(NodeId);
            res = upPath(manyTreeNode.getData().getParentId(),NodeId);
            System.out.println(res);
            if(!flag) resList.add(res);
            NodeId = updateLeft(NodeId);
            if(tmp!=NodeId){
                ans+=tmp+"-->";
                isChanged = true;
            }
         }
        System.out.println();
        System.out.println(ans+res);


        System.out.println();
        //display(resList);

    }


    /**
     * 获取TreeNode所在List
     * @param NodeId
     * @return
     */
    public static List<TreeNode> findList(String NodeId){
        if(myList.size()==0) return null;
        for (int i=0;i<myList.size();i++){
            for (int j=0;j<myList.get(i).size();j++){
                if(myList.get(i).get(j).getNodeId() == NodeId) return myList.get(i);
            }
        }
        return null;
    }

    public static int findIndex(List<TreeNode> list,String NodeId){
        for (int i=0;i<list.size();i++){
            if(list.get(i).getNodeId() == NodeId) return i;
        }
        return -1;
    }

    public static String updateLeft(String NodeId){


        List<TreeNode> list = findList(NodeId);
        int index = findIndex(list,NodeId);
        if(index>0 && !list.get(index-1).isChecked()){
            list.get(index-1).setChecked(true);
            return list.get(index-1).getNodeId();
        }
        return updateRight(NodeId);

    }

    public static String updateRight(String NodeId){
        List<TreeNode> list = findList(NodeId);
        int index = findIndex(list,NodeId);

        //if(list.get(index).isChecked())  return updateDown(NodeId);

        if(index<list.size()-1  && !list.get(index+1).isChecked() ){
            list.get(index+1).setChecked(true);
            return list.get(index+1).getNodeId();
        }
        return updateDown(NodeId);
    }

    public static String updateDown(String NodeId){
        ManyTreeNode manyTreeNode = root.findTreeNodeById(NodeId);

        if(manyTreeNode.getData().isChecked()) return updateUp(NodeId);

        if (manyTreeNode.getChildList().size()!=0 &&manyTreeNode.getChildList()!=null){
            return manyTreeNode.getChildList().get(0).getData().getNodeId();
        }else{
            return updateUp(NodeId);
        }
    }



    public static String updateUp(String NodeId){
        ManyTreeNode manyTreeNode = root.findTreeNodeById(NodeId);
        return  manyTreeNode.getData().getParentId();
    }


    public static String leftPath(String NodeId){
        List<TreeNode> list = findList(NodeId);
        int index = findIndex(list,NodeId);
        String res = "";
        for (int i = index;i>=0;i--){
            if(list.get(i).getValue()==0){
                res += list.get(i).getNodeId() +"-->";
            }else {
                res += list.get(i).getNodeId();
                flag = false;
                break;
            }
        }
        return res;
    }


    public static String rightPath(String NodeId){
        List<TreeNode> list = findList(NodeId);
        int index = findIndex(list,NodeId);
        String res = "";
        for (int i = index;i<list.size();i++){
            if(list.get(i).getValue()==0){
                res += list.get(i).getNodeId() +"-->";
            }else {
                res += list.get(i).getNodeId();
                flag = false;
                break;
            }
        }
       return res;
    }


    public static String downPath(String NodeId){
        List<ManyTreeNode> manyTreeNodes = root.findTreeNodeById(NodeId).getChildList();
        if(manyTreeNodes.isEmpty()) return NodeId+"-->";
        String res = NodeId+"-->";
        res+=rightPath(manyTreeNodes.get(0).getData().getNodeId());
        return res;
    }

    public static String upPath(String NodeId,String beforeNodeId){

        //todo 在此处控制向上遍历终止条件
        //if(flagCnt>0) return "";
        String res = beforeNodeId+"-->";
        ManyTreeNode manyTreeNode = root.findTreeNodeById(NodeId);
        if(manyTreeNode.getData().getValue() == 1){
            flag = false;
            return res+NodeId;
        }


        //res += downPath(manyTreeNode.getData().getNodeId());
        //什么条件终止？？
        if(isChanged){
            res += leftPath(manyTreeNode.getData().getNodeId());
            if(!flag) return res;   //防止最后结果重复
            res += rightPath(manyTreeNode.getData().getNodeId());
        }
        return res;
    }





    public static ManyTreeNode findManyTreeNode(ManyTreeNode root,String k){
        Queue<ManyTreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (queue.size()!=0) {
            ManyTreeNode tempNode = queue.poll();
            if (tempNode.getData().getNodeId()==k) {//判断当前节点
                return tempNode;
            }
            if (tempNode.getChildList()!=null) {
                List<ManyTreeNode> list = new ArrayList<>();
                list = tempNode.getChildList();
                for (int i = 0; i < list.size(); i++) {
                    queue.add(list.get(i));
                }
                list.clear();
            }

        }
        return null;
    }


    public static void display(List<String> list){
        for (int i=0;i<list.size();i++){
            System.out.print(list.get(i)+" ");
            System.out.println();
        }

    }
}
