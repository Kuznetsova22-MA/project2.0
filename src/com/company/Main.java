package com.company;

import java.util.*;

public class Main {
    public static <T> void showList(List<T> list) {
        for (T element : list) {
            System.out.print(element + "  ");
        }
        System.out.println();
    }

    public static <T> void showSet(Set<T> setlist) {
        for (T element : setlist) {
            System.out.print(element + "  ");
        }
        System.out.println();
    }

    public static <K, T> void showMap(Map<K, T> maplist) {
        K key;
        T collection;

        for (Map.Entry<K, T> entry : maplist.entrySet()) {
            key = entry.getKey();
            collection = entry.getValue();
            System.out.println("key = " + key + " value: " + collection);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("1. Создать лист из своих объектов (10-15 элементов в списке). Добавить, удалить и т.д.");
        //1. Создать лист из своих объектов (10-15 элементов в списке). Добавить, удалить и т.д.
        Person p1 = new Person("Ivan", 1);
        Person p2 = new Person("Stepan", 23);
        Person p3 = new Person("Vasya", 3);
        Person p4 = new Person("Petya", 45);
        Person p5 = new Person("Kolya", 5);
        Person p6 = new Person("Elena", 5);

        List<Person> personList = new LinkedList<Person>();
        personList.add(p1);
        personList.add(p2);
        personList.add(p3);
        personList.add(p4);
        personList.add(p5);
        personList.add(p6);

        showList(personList);

        personList.add(3, new Person("Maria", 16));
        showList(personList);

        personList.remove(3);
        personList.remove(p5);
        showList(personList);
/**2. Добавить дубли в список (
 * 1 - несколько раз один и тот же объект
 * 2 - дубль должен быть новым объектом с теми же параметрами, что уже имеет один из существующих в списке
 * )
 **/
        System.out.println("2. Добавить дубли в список (" + "\n" + "  1 - несколько раз один и тот же объект" + "\n" + "  2 - дубль должен быть новым объектом с теми же параметрами, что уже имеет один из существующих в списке)");
        personList.add(p5);
        personList.add(p5);
        personList.add(p5);
        personList.add(new Person("Kolya", 5));
        showList(personList);
// 4. Создать неповторяющееся упорядоченное множество с использованием компаратора и перенести значения из созданного листа.
        System.out.println("4. Создать неповторяющееся упорядоченное множество с использованием компаратора и перенести значения из созданного листа.");
        PersonComparator comparePerson = new PersonComparator();
        Set<Person> uniquePersonList = new TreeSet<>(comparePerson);
        uniquePersonList.addAll(personList);
        showSet(uniquePersonList);
/**
 *  5. Обход дерева с помощью forEach и iterator
 *  (подсчет
 *  или
 *  конкатинация из объектов коллекции используя условие, например" + "\n" + "«все начинаются с буквы», «больше какого-то значения»)");
 */
        System.out.println("\n" + "5. Обход дерева с помощью forEach и iterator (подсчет или конкатинация из объектов коллекции используя условие, например" + "\n" + "«все начинаются с буквы», «больше какого-то значения»)");

        int countPersons = 0; //подсчет количества либей старше трех лет + в имени должна быть буква E
        for (Person element : uniquePersonList) {
            if (element.getAge() > 3) {
                if (element.getName().indexOf('e') > 0)
                    countPersons++;
            }
        }
        System.out.println("подсчет количества людей старше трех лет + в имени должна быть буква E: " + countPersons);

        System.out.println("\n" + "Конкатинация людей одного возраста(имя1_имя2): ");
        uniquePersonList.add(new Person("Katy", 1));
        uniquePersonList.add(new Person("Vika", 5));

        System.out.println("Использование итератора: ");
        Iterator<Person> iterator = uniquePersonList.iterator();
        Person person, personFirst = iterator.next();
        int age = personFirst.getAge();
        while (iterator.hasNext()) {
            person = iterator.next();
            if (age == person.getAge()) {
                personFirst.setName(person.getName() + " + " + personFirst.getName());
                iterator.remove();
                person = personFirst;
            }
            age = person.getAge();
            personFirst = person;
        }
        showSet(uniquePersonList);
//6. Удалить третий элемент из множества.
        System.out.println("\n" + "6. Удалить третий элемент из множества.");
        iterator = uniquePersonList.iterator();
        int i = 1;
        while (iterator.hasNext() && i < 4) {
            iterator.next();
            if (i == 3)
                iterator.remove();
            i++;
        }
        showSet(uniquePersonList);
//7. Из существующей коллекции объектов создать ассоциативную карту, где ключ - объект, а значение - коллекция
        System.out.println("\n" + "7. Из существующей коллекции объектов создать ассоциативную карту, где ключ - объект, а значение - коллекция");

        Map<Person, Collection> mapPerson = new HashMap<>();
        for (Person p : uniquePersonList) {
            mapPerson.put(p, uniquePersonList);
        }
        showMap(mapPerson);

        /*8. Из существующей карты создать другую, в которой
         *ключ остается прежним,
         * а значение - вычисленное значение чего-либо из коллекции для ключа (по нескольким вариантам значений)
         */
        System.out.println("\n" + "8. Из существующей карты создать другую, " + "\n" + "в которой ключ остается прежним," + "\n" + "а значение - вычисленное значение чего-либо из коллекции для ключа (по нескольким вариантам значений)");
        Map<Person, Integer> newMapPerson = new HashMap<>();

        for (Map.Entry<Person, Collection> enter: mapPerson.entrySet()) {
           newMapPerson.put(enter.getKey(), enter.getValue().hashCode());
        }
        showMap(newMapPerson);
    }
}
