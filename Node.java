public class Node {

    private int info;
    
    private Node father;
    private Node left;
    private Node right;
    
    public Node(int info)
    {
        this.info = info;
        this.father = null;
        this.left = null;
        this.right = null;
    }
    
    public int Get_info()
    {
        return this.info;
    }
    
    public Node Get_father()
    {
        return this.father;
    }
    
    public Node Get_l_son()
    {
        return left;
    }
    
    public Node Get_r_son()
    {
        return right;
    }
    
    public void Set_info(int new_info)
    {
        this.info = new_info;
    }
    
    public void Set_father(Node new_father)
    {
        this.father = new_father;
    }
    public void Set_l_son(Node new_left)
    {
        this.left = new_left;
    }
    
    public void Set_r_son(Node new_right)
    {
        this.right = new_right;
    }
    
    public Node Min_of_max()
    {
       return this.right.Min();
    }
    
    public Node Max_of_min()
    {
        return this.left.Max();
    }
    
    private Node Max()
    {
       if(this.right == null) return this;
       else return this.right.Max();
    }
    
    public boolean Is_leaf()
    {
        return this.left == null && this.right == null;
    }
    private Node Min()
    {
       if(this.left == null) return this;
       else return this.left.Min();
    }
    
    public int Height()
    {
        if(this.Is_leaf()) return 0;
        
        else if(this.left == null) return this.right.Height() + 1;
        else if(this.right == null) return this.left.Height() + 1;
        
        else{
            int l, r;
            
            l = this.left.Height();
            r = this.right.Height();
            
            if(l >= r) return l + 1;
            else return r + 1; 
        }
    }
    
    public int Balance()
    {
        if(this.Is_leaf()) return 0;
        else if(this.left == null)return -this.Height();
        else if(this.right == null)return this.Height();
        
        else return this.left.Height() - this.right.Height();
    }
}
