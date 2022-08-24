public class ItemsList {
    
    Integer qtd_items = 0;

    boolean ocupado = false;

    public synchronized void addItem(){
        qtd_items++;
    }

    public synchronized void removeItem(){
        qtd_items--;
    }

    public synchronized Integer getQtdItens(){
        return qtd_items;
    }
    
}
