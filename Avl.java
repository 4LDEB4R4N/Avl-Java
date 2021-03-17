public class Avl {
    
    private Node root;
    
    public Avl()
    {
        this.root = null;
    }
    
    public void Insert(int info)
    {
        Node neo =  new Node(info);
        
        if(this.root == null) this.root = neo;
        
        else{
            Insert_2(this.root,neo);
            
            Balance_tree(neo);
        }
    }
    
    private void Insert_2(Node node,Node neo)
    { 
        if(neo.Get_info() > node.Get_info()){
            if (node.Get_r_son() == null){
                node.Set_r_son(neo);
                neo.Set_father(node);
            }
            else this.Insert_2(node.Get_r_son(), neo);
        }
        else if(neo.Get_info() < node.Get_info()){
            if(node.Get_l_son() == null){
                node.Set_l_son(neo);
                neo.Set_father(node);
            }
            else this.Insert_2(node.Get_l_son(), neo);
        }   
    }
    
    public void Delete(int info)
    {    
        Delete_1(Node_search(info));     
    }
    
    private void Delete_1(Node to_delete){
        Node aux;
        int info;
        
        if(to_delete ==  null)  return;
        
        if(to_delete.Is_leaf()) Delete_2(to_delete);
        
        else{
            if(to_delete.Get_l_son() == null){
                aux = to_delete.Min_of_max();
                info = aux.Get_info();
                Delete_1(aux);   
            }
            else{
                aux = to_delete.Max_of_min();
                info = aux.Get_info();
                Delete_1(aux);   
            }
            
            to_delete.Set_info(info);  
        }     
    }
    
    private void Delete_2(Node to_delete)
    {
        Node father = to_delete.Get_father();
        
        if (father == null){  
            root = null;
        }
        else{
            if(father.Get_info() > to_delete.Get_info())
                father.Set_l_son(null);

            else father.Set_r_son(null);
        }
        
        Balance_tree(father);   
    }
    
    private void Balance_tree(Node neo)
    {   
        if(neo == null) return;
        
        Node aux;
        
        switch(neo.Balance()){
            case -2:
                aux = neo.Get_r_son();
                
                if(aux.Balance() == -1) S_rotation(aux,1);
                
                else D_rotation(aux.Get_l_son(),1);
                
                break;
            case 2:
                aux = neo.Get_l_son();
                
                if(aux.Balance() == 1) S_rotation(aux,2);
                
                else D_rotation(aux.Get_r_son(),2);
                
                break;
            default:
                Balance_tree(neo.Get_father());
                
                break;
        } 
    }
    
    private Node Node_search(int info)
    {
        return Search(this.root, info);
    }
    
    private Node Search(Node node, int info)
    {
        if(node == null)return null;
        
        else{
            int i = node.Get_info();

            if(i == info) return node;
            else if(i > info) return Search(node.Get_l_son(), info);
            else return Search(node.Get_r_son(), info);
        }
    }

    private void S_rotation(Node k2,int type)
    {
        Node k1 = k2.Get_father();
        Node father = k1.Get_father();

        k2.Set_father(father);
        k1.Set_father(k2);
        
        if(father == null)this.root = k2;
        else if(k2.Get_info() > father.Get_info()) father.Set_r_son(k2);
        else father.Set_l_son(k2);
        
        switch(type){
            case 1:
                k1.Set_r_son(k2.Get_l_son());
                k2.Set_l_son(k1);
                
                if(k1.Get_r_son() != null)
                    k1.Get_r_son().Set_father(k1);
                
                break;
            case 2:
                k1.Set_l_son(k2.Get_r_son());
                k2.Set_r_son(k1);
                
                if(k1.Get_l_son() != null)
                    k1.Get_l_son().Set_father(k1);
                
                break;
        }   
    }
    
    private void D_rotation(Node k2,int type)
    {
        Node k1 = k2.Get_father();
        Node k3 = k1.Get_father();
        Node father = k3.Get_father();
        
        k2.Set_father(father);
        k1.Set_father(k2);
        k3.Set_father(k2);
        
        if(father == null)this.root = k2;
        else if(k2.Get_info() > father.Get_info()) father.Set_r_son(k2);
        else father.Set_l_son(k2);
        
        switch(type){
            case 1:
                k1.Set_l_son(k2.Get_r_son());
                k3.Set_r_son(k2.Get_l_son());
                k2.Set_r_son(k1);
                k2.Set_l_son(k3);
                
                
                if(k1.Get_l_son() != null)
                    k1.Get_l_son().Set_father(k1);
                
                if(k3.Get_r_son() != null)
                    k3.Get_r_son().Set_father(k3);
                
                break;
            case 2:
                k1.Set_r_son(k2.Get_r_son());
                k3.Set_l_son(k2.Get_l_son());
                k2.Set_l_son(k1);
                k2.Set_r_son(k3);
                
                if(k1.Get_r_son() != null)
                    k1.Get_r_son().Set_father(k1);
                
                if(k3.Get_l_son() != null)
                    k3.Get_l_son().Set_father(k3);

                break;
        }   
    }
    
    public void Print()
    {
        int h;      
        
        for(h = 0; h <= this.root.Height() ; h++){
            System.out.print("|");
            Print_avl(this.root, h);
            System.out.println();
        }
    }
    
    public void Print_t()
    {
        int h;
        double p;        
        
        for(h = 0; h <= this.root.Height() ; h++){
            p = (Math.pow(2,(this.root.Height() - h)) - 1);
            Print_avl(this.root, h,p);
            System.out.println();
        }
    }
    
    public void Print_b()
    {
        int h;
        double p;        
        
        for(h = 0; h <= this.root.Height() ; h++){
            p = (Math.pow(2,(this.root.Height() - h)) - 1);
            Print_avl_2(this.root, h,p);
            System.out.println();
        }
    }
    
    public void Print_avl(Node node, int height)
    {    
        if(node == null) System.out.print(" - |");
        
        else{ 
            if(height == 0){
                double p = Math.floor(Math.log10(node.Get_info()));
                for(;p < 2; p++)System.out.print(" ");
                System.out.print(node.Get_info());
                System.out.print("|");
            }
            
            else{
                height--;
                Print_avl(node.Get_l_son(),height);
                Print_avl(node.Get_r_son(),height);    
            }
        }
    }
    
    public void Print_avl(Node node, int height,double p)
    {    
        int i;
        if(node == null){
            if(height == 0){
                for(i = 0;i < p; i++)System.out.print(" ");
                System.out.print("x ");
                for(i = 0;i < p; i++)System.out.print(" ");
            }
            else System.out.print("x x ");
        }
        else{ 
            if(height == 0){
                for(i = 0;i < p; i++)System.out.print(" ");
                System.out.print("o ");
                for(i = 0;i < p; i++)System.out.print(" ");
            }
            else{
                height--;
                Print_avl(node.Get_l_son(),height,p);
                Print_avl(node.Get_r_son(),height,p);    
            }
        }
       
    }
    
    public void Print_avl_2(Node node, int height,double p)
    {    
        int i;
        if(node == null){
            if(height == 0){
                for(i = 0;i < p; i++)System.out.print(" ");
                System.out.print("x ");
                for(i = 0;i < p; i++)System.out.print(" ");
            }
            else System.out.print("x x ");
        }
        else{ 
            if(height == 0){
                if(node.Balance() >= 0 && p > 0)System.out.print(" ");
                for(i = 1;i < p; i++)System.out.print(" ");
                System.out.print(node.Balance()+" ");
                for(i = 0;i < p; i++)System.out.print(" ");
            }
            else{
                height--;
                Print_avl_2(node.Get_l_son(),height,p);
                Print_avl_2(node.Get_r_son(),height,p);    
            }
        }
       
    }
    
    public void Hard_balance()
    {
        Hard_balance(root);
    }
    
    private void Hard_balance(Node node){
        
        Node l = node.Get_l_son();
        Node r = node.Get_r_son();
        
        if(l != null && l.Height() > 0 && r != null && r.Height() > 0){
            
            Node neo = new Node(node.Get_info());
        
            
            int b = node.Balance();
            
            Delete_balance(node, b);
            Insert_balance(node, neo, b);
        }

    }
    
    
    private void Delete_balance(Node to_delete, int b){
        
        Node aux;
        int info;
        
        switch (b) {
            case -1:
                aux = to_delete.Min_of_max();
                info = aux.Get_info();   
                Delete_1(aux);
                Hard_balance(to_delete.Get_r_son());
                break;
            case 1:
                aux = to_delete.Max_of_min();
                info = aux.Get_info();
                Delete_1(aux);
                Hard_balance(to_delete.Get_l_son());
                break;
            default:
                return;
        }
        
        
        to_delete.Set_info(info);  
             
    }
    
    private void Insert_balance(Node root,Node neo, int b)
    { 
        Node aux;
        
        switch (b) {
            case -1:
                aux = root.Max_of_min();
                aux.Set_r_son(neo);
                neo.Set_father(aux);
                Balance_tree(aux);
                Hard_balance(root.Get_l_son());
                break;
            case 1:
                aux = root.Min_of_max();
                aux.Set_l_son(neo);
                neo.Set_father(aux);
                Balance_tree(aux);
                Hard_balance(root.Get_r_son());
                break;
            default:
                return;
        }
        
        
    }
    
    
}

