package org.example;

import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.Date;

public class RedisStorage {
    private RedissonClient redisson;
    private RKeys rKeys;
    private RScoredSortedSet<String> onlineUsers;
    private final static String KEY = "ONLINE_USERS";

    void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            System.out.println("Не удалось подключиться к Redis");
            System.out.println(Exc.getMessage());
        }
        rKeys = redisson.getKeys();
        onlineUsers = redisson.getScoredSortedSet(KEY);
        rKeys.delete(KEY);
    }

    void shutdown() {
        redisson.shutdown();
    }

    void addUser(int user_id) {
        onlineUsers.add(new Date().getTime(), String.valueOf(user_id));
    }

    void printUsers() {
        int count = 1;
        for (String user : onlineUsers) {
            if(count==10) {
                count = 1;
                printRandomUser();
            }
            System.out.println(user.toString());
            count++;
        }
    }

    private void printRandomUser() throws InterruptedException {
        int randomId = (int) (20 * Math.random());
        onlineUsers.remove(String.valueOf(randomId));
        addUser(randomId);
        Thread.sleep(1000);
    }
}
