package com.javarush.task.task17.task1710;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        Solution solution = new Solution();

        if (args[0].equals("-c")) {
            solution.CreatePerson(args);
        }

        if (args[0].equals("-r")) {
            solution.ReadPerson(Integer.parseInt(args[1]));
        }

        if (args[0].equals("-u")) {
            solution.UpdatePerson(Integer.parseInt(args[1]), args[2], args[3], args[4]);

        }

        if (args[0].equals("-d")) {
            solution.DeletePerson(Integer.parseInt(args[1]));
        }
    }

    public void CreatePerson(String[] args) throws ParseException {
        String name = args[1];
        String dateString = args[3];
        Date birthDay;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        birthDay = simpleDateFormat.parse(dateString);

        if (args[2].equals("м")) {
            allPeople.add(Person.createMale(name, birthDay));

        } else if (args[2].equals("ж")) {
            allPeople.add(Person.createFemale(name, birthDay));
        }
        System.out.println(allPeople.size() - 1);
    }

    public void ReadPerson(int id) {

        Person test = allPeople.get(id);
        String pol = "noSex";

        if (test.getSex().toString().equals("MALE")) {
            pol = "м";
        } else if (test.getSex().toString().equals("FEMALE")) {
            pol = "ж";
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(test.getBirthDate());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        System.out.println(test.getName() + " " + pol + " " + simpleDateFormat.format(calendar.getTime()));
    }

    public void UpdatePerson(int id, String name, String sex, String date) throws ParseException {

        Person test = allPeople.get(id);
        test.setName(name);
        if (sex.equals("м")) {
            test.setSex(Sex.MALE);

        } else if (sex.equals("ж")) {
            test.setSex(Sex.FEMALE);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        test.setBirthDate(simpleDateFormat.parse(date));
    }


    public void DeletePerson(int id) {
        Person test = allPeople.get(id);
        test.setName(null);
        test.setSex(null);
        test.setBirthDate(null);
    }
}