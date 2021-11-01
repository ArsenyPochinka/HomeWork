package lesson_1.lesson5;

public class Staff {
    private String name;
    private String post;
    private String email;
    private String phone;
    private int salary;
    private int age;
    public Staff(String name,String post,String email,String phone,int salary,int age) {
        this.name = name;
        this.post = post;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public static void main(String[] args) {
        Staff[] staff = new Staff[5];
        staff[0] = new Staff("Boris", "janitor", "aaa@mail.ru", "+7(999)999-99-99", 20000,20);
        staff[1] = new Staff("Alex", "programmer", "aaa@mail.ru", "+7(999)999-99-99", 200000,27);
        staff[2] = new Staff("Sergy", "doctor", "aaa@mail.ru", "+7(999)999-99-99", 60000,47);
        staff[3] = new Staff("Hleb", "manager", "aaa@mail.ru", "+7(999)999-99-99", 50000,41);
        staff[4] = new Staff("Steve", "teacher", "aaa@mail.ru", "+7(999)999-99-99", 45000,26);
        for(Staff i: staff) {
            if(i.age>40) {
                i.info();
            }
        }
        }

    public void info() {
        System.out.println("Name: "+ name
                           +"\nPost: " + post
                           +"\nEmail: " + email
                           +"\nPhone: " + phone
                           +"\nSalary: " + salary
                           +"\nAge: " + age
                           +"\n");
    }
}
