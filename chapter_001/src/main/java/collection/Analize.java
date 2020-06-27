package collection;

import java.util.List;

public class Analize {
    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();

        if (previous.size() > current.size()) {
            info.deleted = previous.size() - current.size();
        } else {
            info.added = current.size() - previous.size();
        }

        for (User currnetUser : current) {
            for (User prev: previous) {
                if (currnetUser.equals(prev)
                    && !currnetUser.name.equals(prev.name)) {
                    info.changed++;
                }
            }
        }

        return info;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }


            User user = (User) o;

            return id == user.id;
        }

        @Override
        public int hashCode() {
            int result = id;
            result = 31 * result + name.hashCode();
            return result;
        }
    }

    public static class Info {
        int added = 0;
        int changed = 0;
        int deleted = 0;
    }
}
