package com.atguigu.Algorithm.dijkstra;

import java.util.Arrays;

public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;// 表示不可以连接
        matrix[0]=new int[]{N,5,7,N,N,N,2};
        matrix[1]=new int[]{5,N,N,9,N,N,3};
        matrix[2]=new int[]{7,N,N,N,8,N,N};
        matrix[3]=new int[]{N,9,N,N,N,4,N};
        matrix[4]=new int[]{N,N,8,N,N,5,4};
        matrix[5]=new int[]{N,N,N,4,5,N,6};
        matrix[6]=new int[]{2,3,N,N,4,6,N};
        //创建 Graph对象
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
        graph.djs(6);
        graph.showDijkstra();

    }


}






class VisitedVertex{
    // 记录各个顶点是否访问过 1表示访问过,0未访问,会动态更新
    public int[] already_arr;
    // 每个下标对应的值为前一个顶点下标, 会动态更新
    public int[] pre_visited;
    // 记录出发顶点到其他所有顶点的距离,比如G为出发顶点，就会记录G到其它顶点的距离，会动态更新，求的最短距离就会存放到dis
    public int[] dis;



    public VisitedVertex(int length,int index) {
        this.already_arr = new int[length];
        this.dis = new int[length];
        this.pre_visited = new int[length];

        Arrays.fill(dis,65535);
        this.already_arr[index] = 1; //设置出发顶点被访问过
        this.dis[index]=0;
    }
    /**
     * 功能: 判断index顶点是否被访问过
     * @param index
     * @return 如果访问过，就返回true, 否则访问false
     */
    public boolean in(int index){
        return already_arr[index]==1;
    }
    /**
     * 功能: 更新出发顶点到index顶点的距离
     * @param index
     * @param len
     */
    public void updateDis(int index,int len){
        dis[index]=len;
    }
    /**
     * 功能: 更新pre这个顶点的前驱顶点为index顶点
     * @param pre
     * @param index
     */
    public void updatePre(int pre,int index){
        pre_visited[pre]=index;
    }

    /**
     * 功能:返回出发顶点到index顶点的距离
     * @param index
     */
    public int getDis(int index) {
        return dis[index];
    }
    /**
     * 继续选择并返回新的访问顶点， 比如这里的G 完后，就是 A点作为新的访问顶点(注意不是出发顶点)
     * @return
     */
    public int updateArr() {
        int min = 65535, index = 0;
        for(int i = 0; i < already_arr.length; i++) {
            if(already_arr[i] == 0 && dis[i] < min ) {
                min = dis[i];
                index = i;
            }
        }
        //更新 index 顶点被访问过
        already_arr[index] = 1;
        return index;
    }

    //显示最后的结果
    //即将三个数组的情况输出
    public void show() {

        System.out.println("==========================");
        //输出already_arr
        for(int i : already_arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        //输出pre_visited
        for(int i : pre_visited) {
            System.out.print(i + " ");
        }
        System.out.println();
        //输出dis
        for(int i : dis) {
            System.out.print(i + " ");
        }
        System.out.println();
        //为了好看最后的最短距离，我们处理
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int count = 0;
        for (int i : dis) {
            if (i != 65535) {
                System.out.print(vertex[count] + "("+i+") ");
            } else {
                System.out.println("N ");
            }
            count++;
        }
        System.out.println();

    }

}






class Graph{
    private char[] vertex;
    private int[][] matrix;
    public VisitedVertex vv;
    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }
    public void showGraph(){
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }
    public void djs(int index){
        vv = new VisitedVertex(vertex.length,index);
        update(index);
        for(int j = 1; j <vertex.length; j++) {
            index = vv.updateArr();// 选择并返回新的访问顶点
            update(index); // 更新index顶点到周围顶点的距离和前驱顶点
        }
    }
    public void update(int index){
        int len = 0;
        for (int j=0;j<matrix[index].length;j++){
            len=vv.getDis(index)+matrix[index][j];
            if (!vv.in(j)&&len<vv.getDis(j)){
                vv.updatePre(j,index);
                vv.updateDis(j,len);
            }
        }
    }
    //显示结果
    public void showDijkstra() {
        vv.show();
    }
}
