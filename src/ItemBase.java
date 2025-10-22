public class ItemBase implements Interactable{
    public ItemBase(){

    }
    public String getName(){
        return "ItemBase";
    }

    public String getDescription() {
        return "This is the base item. You should not be seeing this. Oops.";
    }

    public boolean interact(Interactable other){
        return false;
    }
}
