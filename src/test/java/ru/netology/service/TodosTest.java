package ru.netology.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import javax.management.Query;

public class TodosTest {

    @Test
    public void shouldAddDifferentTasks() {
        SimpleTask simpleTask = new SimpleTask(5, "Сделать домашку");
        String[] subtasks = {"Чай", "Мед", "Колбаска"};
        Epic epic = new Epic(7, subtasks);
        Meeting meeting = new Meeting(18,
                "разборный вебинар: Исключения, Интерфейсы, Generics и Collections Framework",
                "Java для QA",
                "18 марта");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);
        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldMatchesQueryMeeting1() {
        Meeting meeting = new Meeting(2, "hghjg", "Java", "3 марта");

        Boolean expected = true;
        Boolean actual = meeting.matches("hghjg");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldMatchesQueryMeeting2() {
        Meeting meeting = new Meeting(2, "hghjg", "Java", "3 марта");

        Boolean expected = false;
        Boolean actual = meeting.matches("2 февраля");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldMatchesQueryMeeting3() {
        Meeting meeting = new Meeting(2, "hghjg", "Java", "3 марта");

        Boolean expected = true;
        Boolean actual = meeting.matches("Java");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldMatchesQuerySimpleTask1() {
        SimpleTask simpleTask = new SimpleTask(2, "Сделать домашку");

        Boolean expected = false;
        Boolean actual = simpleTask.matches("Погулять");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldMatchesQuerySimpleTask2() {
        SimpleTask simpleTask = new SimpleTask(2, "Погулять");

        Boolean expected = true;
        Boolean actual = simpleTask.matches("Погулять");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldMatchesQueryEpic1() {
        String[] subtasks = {"Чай", "Мед", "Колбаска"};
        Epic epic = new Epic(7, subtasks);
        Boolean expected = true;
        Boolean actual = epic.matches("Чай");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldMatchesQueryEpic2() {
        String[] subtasks = {"Чай", "Мед", "Колбаска"};
        Epic epic = new Epic(7, subtasks);
        Boolean expected = false;
        Boolean actual = epic.matches("Хлеб");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSearchTasks1() {
        SimpleTask simpleTask = new SimpleTask(5, "Погулять");
        String[] subtasks = {"Чай", "Мед", "Колбаска"};
        Epic epic = new Epic(6, subtasks);
        Meeting meeting = new Meeting(18,
                "разборный вебинар: Исключения, Интерфейсы, Generics и Collections Framework",
                "Java для QA",
                "18 марта");
        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);
        Task[] expected = {epic};
        Task[] actual = todos.search("Чай");
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldSearchTasks2() {
        SimpleTask simpleTask = new SimpleTask(5, "Погулять");
        String[] subtasks = {"Чай", "Мед", "Колбаска"};
        Epic epic = new Epic(6, subtasks);
        Meeting meeting = new Meeting(18,
                "разборный вебинар: Исключения, Интерфейсы, Generics и Collections Framework",
                "Java для QA",
                "18 марта");
        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);
        Task[] expected = {};
        Task[] actual = todos.search("Сделать домашку");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchTasks3() {
        SimpleTask simpleTask = new SimpleTask(5, "посмотреть разборный вебинар");
        String[] subtasks = {"Чай", "Мед", "Молоко"};
        Epic epic = new Epic(6, subtasks);
        Meeting meeting = new Meeting(18,
                "разборный вебинар",
                "Java для QA",
                "18 марта");
        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);
        Task[] expected = {simpleTask, meeting};
        Task[] actual = todos.search("вебинар");
        Assertions.assertArrayEquals(expected, actual);
    }

}
