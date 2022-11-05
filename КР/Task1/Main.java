package Task1;

public class Main {
    public static void main(String[] args) {
        Reader reader = new Reader("Петров Василий Вячеславович", 1, "ИТиАБД", "01.05.1995", "+79113512323");
        reader.takeBook(3);
        reader.takeBook("Приключения", "Словарь", "Энциклопедия");
        reader.takeBook(new Book("Библия"), new Book("Палата №6"));

        reader.returnBook("Приключения");
        reader.returnBook(2);
    }
}
