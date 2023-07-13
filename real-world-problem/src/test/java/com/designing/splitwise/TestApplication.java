package com.designing.splitwise;

import com.designing.splitwise.model.ExpenseType;
import com.designing.splitwise.model.User;
import com.designing.splitwise.model.expense.ExpenseData;
import com.designing.splitwise.model.split.EqualSplit;
import com.designing.splitwise.model.split.Split;
import com.designing.splitwise.repository.ExpenseRepository;
import com.designing.splitwise.service.SplitWiseService;
import com.designing.splitwise.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestApplication {

    private ExpenseRepository expenseRepository;
    private UserService userService;
    private SplitWiseService service;

    @BeforeEach
    public void addUsers() {
        User user1 = new User(1, "u1", "u1@gmail.com", "9890098900");
        User user2 = new User(2, "u2", "u2@gmail.com", "9999999999");
        User user3 = new User(3, "u3", "u3@gmail.com", "9898989899");
        User user4 = new User(4, "u4", "u4@gmail.com", "8976478292");

        expenseRepository = new ExpenseRepository();
        userService = new UserService(expenseRepository);
        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);
        userService.addUser(user4);
        service = new SplitWiseService(expenseRepository);
    }

    // EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL
    @Test
    public void addEqualExpense() {

        // adding first expense
        String paidByUser = "u1";
        double amountSpend = 1000;
        int totalMembers = 4;
        String expenseTypeString = "EQUAL";
        String[] userSpendList = new String[]{"u1", "u2", "u3", "u4"};
        ExpenseData expenseData = new ExpenseData("Electricity Bill");

        ExpenseType expenseType = ExpenseType.of(expenseTypeString);
        List<Split> splitList = new ArrayList<>();
        for (int i = 0; i < userSpendList.length; i++) {
            User user = userService.getUser(userSpendList[i]);
            splitList.add(new EqualSplit(user));
        }

        service.addExpense(expenseType, amountSpend, paidByUser, splitList, expenseData);


        // adding second expense
        paidByUser = "u4";
        amountSpend = 1200;
        totalMembers = 4;
        expenseTypeString = "EQUAL";
        userSpendList = new String[]{"u1", "u2", "u3", "u4"};
        expenseData = new ExpenseData("Party Bill");

        expenseType = ExpenseType.of(expenseTypeString);
        splitList.clear();
        for (int i = 0; i < userSpendList.length; i++) {
            User user = userService.getUser(userSpendList[i]);
            splitList.add(new EqualSplit(user));
        }
        service.addExpense(expenseType, amountSpend, paidByUser, splitList, expenseData);


        // adding third expense
        paidByUser = "u3";
        amountSpend = 800;
        totalMembers = 4;
        expenseTypeString = "EQUAL";
        userSpendList = new String[]{"u1", "u2", "u3", "u4"};
        expenseData = new ExpenseData("Trekking Bill");

        expenseType = ExpenseType.of(expenseTypeString);
        splitList.clear();
        for (int i = 0; i < userSpendList.length; i++) {
            User user = userService.getUser(userSpendList[i]);
            splitList.add(new EqualSplit(user));
        }
        service.addExpense(expenseType, amountSpend, paidByUser, splitList, expenseData);


    }

}
