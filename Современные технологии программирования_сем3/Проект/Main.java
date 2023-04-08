import java.util.Scanner;
import todoList.todoList;

public class Main {


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        todoList newAction = new todoList();

        // Приветствие
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("Добро поажловать в планировщик задач!");
        System.out.println("Напиши 'Начать' чтобы начать работу");
        System.out.println("--------------------------------------------------------------------------------------------");

        String start = input.nextLine();
        start = start.toLowerCase();
        boolean active;

        if(start.equals("начать")){
            active = true;
        }else{
            active = false;
        }

        while(active){
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println("Выбери действие...");
            System.out.println("(1) Отобразить список");
            System.out.println("(2) Добавить задачу в список");
            System.out.println("(3) Убрать задачу из списка");
            System.out.println("(4) Выполнить задачу");
            System.out.println("(5) Редактировать название");
            System.out.println("(6) Выход");

            int userInput = input.nextInt();
            input.nextLine();
            switch(userInput){
                case 1:
                    newAction.printList();
                    break;
                case 2:
                    newAction.addItem();
                    break;
                case 3:
                    newAction.removeItem();
                    break;
                case 4:
                    newAction.itemComplete();
                    break;
                case 5:
                    newAction.editItem();
                    break;
                case 6:
                    System.out.println("Уверен, что хочешь выйти? Если уйдешь, все данные будут потеряны! (Да/Нет)");
                    String check = input.nextLine();
                    check = check.toLowerCase();
                    if(check.equals("да")){
                        System.out.println("Спасибо, увидимся!");
                        System.exit(0);

                    }

            }
        }
    }
}