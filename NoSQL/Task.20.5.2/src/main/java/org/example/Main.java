package org.example;

public class Main {
    public static void main(String[] args) {
        RedisStorage rs = new RedisStorage();
        rs.init();
        for(int i = 1; i <= 20; i++) {
            rs.addUser(i);
        }
        while(true) {
            rs.printUsers();
        }
        rs.shutdown();
    }

}