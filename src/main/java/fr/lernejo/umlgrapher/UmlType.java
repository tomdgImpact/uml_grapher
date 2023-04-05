package fr.lernejo.umlgrapher;

public class UmlType {
    private final String Name;
    private final String NamePackage;

    private final Class classname;

    public UmlType(Class giveclass){
        this.Name = giveclass.getSimpleName();
        this.NamePackage = giveclass.getPackageName();
        this.classname = giveclass;

    }


    public String name() {
        return this.Name;
    }
    public String packageName(){
        return this.NamePackage;
    }

    public Class classname(){return this.classname;}


}
