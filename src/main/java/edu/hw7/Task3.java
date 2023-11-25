package edu.hw7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Task3 implements PersonDatabase {
    private final Map<Integer, Person> personMap = new HashMap<>();
    private final Map<String, List<Integer>> nameIndex = new HashMap<>();
    private final Map<String, List<Integer>> addressIndex = new HashMap<>();
    private final Map<String, List<Integer>> phoneIndex = new HashMap<>();

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Override
    public synchronized void add(Person person) {
        var writeLock = readWriteLock.writeLock();
        writeLock.lock();
        try  {
            personMap.put(person.id(), person);

            var id = person.id();
            var name = person.name();
            var address = person.address();
            var phoneNumber = person.phoneNumber();

            if (isNotEmpty(name) && isNotEmpty(address) && isNotEmpty(phoneNumber)) {
                nameIndex.computeIfAbsent(name, (item) -> new ArrayList<>()).add(id);
                addressIndex.computeIfAbsent(address, (item) -> new ArrayList<>()).add(id);
                phoneIndex.computeIfAbsent(phoneNumber, (item) -> new ArrayList<>()).add(id);
            }
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public synchronized void delete(int id) {
        var writeLock = readWriteLock.writeLock();
        writeLock.lock();
        try {
            Person person = personMap.remove(id);
            if (person != null) {
                removeFromIndex(nameIndex, person.name(), id);
                removeFromIndex(addressIndex, person.address(), id);
                removeFromIndex(phoneIndex, person.phoneNumber(), id);
            }
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return findByIndex(nameIndex, name);
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return findByIndex(addressIndex, address);
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        return findByIndex(phoneIndex, phone);
    }

    private void removeFromIndex(Map<String, List<Integer>> index, String key, int id) {
        List<Integer> ids = index.get(key);
        if (ids != null) {
            ids.remove(Integer.valueOf(id));
            if (ids.isEmpty()) {
                index.remove(key);
            }
        }
    }

    private List<Person> findByIndex(Map<String, List<Integer>> index, String key) {
        var readLock = readWriteLock.readLock();
        readLock.lock();
        try {
            List<Integer> ids = index.get(key);
            if (ids != null) {
                List<Person> result = new ArrayList<>();
                for (int id : ids) {
                    Person person = personMap.get(id);
                    if (person != null) {
                        result.add(person);
                    }
                }
                return result;
            }
            return List.of();
        } finally {
            readLock.unlock();
        }
    }

    private static boolean isNotEmpty(String s) {
        return s != null && !s.isEmpty();
    }
}
