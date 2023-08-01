public class Stocks {
    public static void main(String[] args){
    if(args.length>0){
        switch(args[0].toLowerCase()){
            case "1":
               Task1 t1 = new Task1();
               t1.task1();
               break;
            case "2":
                Task2 t2 = new Task2();
                t2.task2();
                break;
            case "3a":
                Task3A t3a=new Task3A();
                t3a.task3a();
                break;
            case "3b":
                Task3B t3b=new Task3B();
                t3b.task3b();
                break;
            case "4":
                Task4 t4=new Task4();
                t4.task4();
                break;
            case "5":
                Task5 t5=new Task5();
                t5.task5();
                break;
            case "6a":
                Task6A t6a=new Task6A();
                t6a.task6a();
                break;
            case "6b":
                Task6B t6b=new Task6B();
                t6b.Task6b();
                break;
        }
    }
    }
    
}
