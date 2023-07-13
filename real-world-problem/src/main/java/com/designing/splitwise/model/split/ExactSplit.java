package com.designing.splitwise.model.split;

import com.designing.splitwise.model.User;

public class ExactSplit extends Split {

    public ExactSplit(User user, double amount) {
        super(user);
        this.amount = amount;
    }
}