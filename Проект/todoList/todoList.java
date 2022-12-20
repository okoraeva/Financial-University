package todoList;

import java.util.ArrayList;
import java.util.Scanner;

public class todoList {

    Scanner input = new Scanner(System.in);
    ArrayList<String> todoList = new ArrayList<String>();
    ArrayList<String> todoStatus = new ArrayList<String>();
    ArrayList<String> dueDate = new ArrayList<String>();
    ArrayList<String> todoDesc = new ArrayList<String>();

    // Отображение списка
    public void printList(){
        if(todoList.isEmpty()){
            System.out.println("Список задач пуст");
        }else{
            for(int i = 0; i < todoList.size(); i++){
                System.out.println((i+1) + "." + " " + todoList.get(i) + " // Закончить к: " + dueDate.get(i) + " // Статус: " + todoStatus.get(i));
                System.out.println("Описание: " + todoDesc.get(i));
            }
        }


    }

    // Добавление задачи в планировщик
    public void addItem(){
        System.out.println("Укажи название задачи.");
        todoList.add(input.nextLine());
        todoStatus.add("Не выполнено");
        System.out.println("Укажи дату окончания задачи (ММ/ДД/ГГГГ)");
        dueDate.add(input.nextLine());
        System.out.println("Добавь описание");
        todoDesc.add(input.nextLine());
    }

    // Удаление задачи
    public void removeItem(){
        System.out.println("Укажи номер задачи, которую надо удалить");

        int removeIndex = input.nextInt();
        removeIndex = removeIndex - 1;

        todoList.remove(removeIndex);
        todoStatus.remove(removeIndex);
        dueDate.remove(removeIndex);
    }

    // Изменение статуса на "Выполнено"
    public void itemComplete(){
        System.out.println("Укажи номер задачи, которую надо выполнить");


        int statusIndex = input.nextInt();
        statusIndex = statusIndex - 1;

        todoStatus.set(statusIndex, "Выполнено");
    }

    // Редактирование задачи
    public void editItem(){
        System.out.println("Укажи номер задачи, которую надо отредактировать");
        int editIndex = input.nextInt();
        input.nextLine();
        editIndex = editIndex -1;
        System.out.println("Какое будет новое название?");
        todoList.set(editIndex, input.nextLine());

    }

}
