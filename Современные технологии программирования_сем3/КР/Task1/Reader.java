package Task1;

public class Reader {
    private String name;
    private int number;
    private String faculty;
    private String birthday;
    private String phone;

    public Reader(String name, int number, String faculty, String birthday, String phone) {
        this.name = name;
        this.number = number;
        this.faculty = faculty;
        this.birthday = birthday;
        this.phone = phone;
    }

    public void takeBook(int count) {
        System.out.println(this.name + " взял " + count + " книги");
    }

    public void takeBook(String... names) {
        System.out.printf("%s взял книги: ", this.name);
        for (int i=0; i < names.length-1; i++) {
            System.out.printf("%s, ", names[i]);
        }
        System.out.println(names[names.length-1] + '.');
    }

    public void takeBook(Book ... books) {
        System.out.printf("%s взял книги: ", this.name);
        for (int i=0; i < books.length-1; i++) {
            System.out.printf("'%s', ", books[i].getBookName());
        }
        System.out.printf("'%s'.\n", books[books.length - 1].getBookName());
    }

    public void returnBook(int count) {
        System.out.println(this.name + " вернул " + count + " книги");
    }

    public void returnBook(String ... names) {
        System.out.printf("%s вернул книги: ", this.name);
        for (int i=0; i < names.length-1; i++) {
            System.out.printf("%s, ", names[i]);
        }
        System.out.println(names[names.length-1] + '.');
    }

    public void returnBook(String name, Book ... books) {
        System.out.printf("%s вернул книги: ", this.name);
        for (int i=0; i < books.length-1; i++) {
            System.out.printf("'%s', ", books[i].getBookName());
        }
        System.out.printf("'%s'.\n", books[books.length - 1].getBookName());
    }
}