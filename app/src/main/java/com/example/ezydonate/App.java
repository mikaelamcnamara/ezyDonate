package com.example.ezydonate;

import java.util.LinkedList;

public class App {

        private History history;
        private LinkedList<User> users;

        public App() {

            this.history = new History(this);

            //database of customers
            this.users.add(new User("Josh", "Hoopy"));

        }
        //R means res and layout refers to package under res

        public void addUser(String new_username, String new_password) {

            this.users.add(new User(new_username, new_password));
        }

        public User getUser(String username) {

            for (User p : this.users) {
                if (p.getUsername().equals(username)) return p;
            }

            return null;
        }

        public void removeUser(User user) {
            this.users.remove(user);
        }

    }


