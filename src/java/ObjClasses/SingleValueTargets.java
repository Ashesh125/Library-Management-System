/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ObjClasses;

/**
 *
 * @author WorldEdit
 */
public class SingleValueTargets {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public boolean validateName(){
        return !(name.equals("") || name == null);
    }
    
    public boolean validateId(){
        return !(id.equals("0") || id == null);
    }
      public boolean valid(String condition){
        boolean val1 = validateName();
        boolean val2 = validateId();
        if(condition.equals("insert")){
            if(val1 == true && val2 == false){return true;}else{return false;}
        }else if(condition.equals("update")){
            if(val1 == true && val2 == true){return true;}else{return false;}    
        }else if(condition.equals("delete")){
            if(val1 == false && val2 == true){return true;}else{return false;}
        }
        return false;
    }
    
}
