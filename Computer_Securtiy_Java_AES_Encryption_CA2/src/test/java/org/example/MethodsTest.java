package org.example;

import static org.junit.jupiter.api.Assertions.*;

class MethodsTest {

    @org.junit.jupiter.api.Test
    void validateFileNameFalse()
    {
        Methods j = new Methods();
        String fileName = "bankai";
        String expected = "bankai.txt";
        String actual = j.validateFileName(fileName);
        assertEquals(expected, actual);


    }

    @org.junit.jupiter.api.Test
    void validateFileNameTrue()
    {
        Methods j = new Methods();
        String fileName = "bankai.txt";
        String expected = "bankai.txt";
        String actual = j.validateFileName(fileName);
        assertEquals(expected, actual);


    }
}