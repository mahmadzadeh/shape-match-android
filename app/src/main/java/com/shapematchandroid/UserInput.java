package com.shapematchandroid;


public enum UserInput {

    Match("match"),
    Mismatch("mismatch");

    private String input;

    UserInput(String input) {
        this.input = input;
    }

    public static UserInput formValue(String in ) {
        for(UserInput userInput: UserInput.values()) {
            if(userInput.input.equalsIgnoreCase(in)) {
                return  userInput;
            }
        }
        throw new IllegalArgumentException("Invalid user input given " + in);
    }
}
