package bfs;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class medium102 {
    public List<List<Integer>> levelOrder(tree.TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if(root==null){
            return results;
        }
        Queue<tree.TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i=0;i<size;i++){
                TreeNode node = queue.poll();
                list.add(node.val);
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
            }
            results.add(list);

        }
        return results;
    }
}
