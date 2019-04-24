import com.alibaba.fastjson.JSON;

public class Demo {
    static class Person {
        public String name;
        public int age;

        public Person(){}

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    public static void main(String[] args) {
        // the serialize operation
        Person person = new Person("xf0rk", 18);
        String personStr = JSON.toJSONString(person);
        System.out.println(personStr);

        // the deserialize operation
        Person personObj = JSON.parseObject(personStr, Person.class);
        System.out.println(personObj.name);
        System.out.println(personObj.age);
    }
}
