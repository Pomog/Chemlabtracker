package lv.javaguru.java2.servify.detail_builder;

public class DetailBuilderUI {
     void printChoseVariantFromList() {
        System.out.println("Choose the variant from the menu, please.");
    }

    void printSideMenu() {
        System.out.println("""
                Enter detail side:\s
                1.Left
                2.Right""");
    }
    void printLocationMenu() {
        System.out.println("""
                Enter detail location:\s
                1.Front
                2.Rear""");
    }
    void printDetailMenu() {
        System.out.println("""
                1. Bonnet
                2. Boot
                3. Bumper
                4. Roof
                5. Door\s
                6. Wing\s
                7. Wing mirror""");
    }
    void printEnterDetailType(){
        System.out.println("Enter detail type:");
    }
}
