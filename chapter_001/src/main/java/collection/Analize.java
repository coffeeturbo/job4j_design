package collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analize {
    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map<Integer, User> map = new HashMap<>();

        if (previous.size() < current.size()) {
            info.added = current.size() - previous.size();
        }

        previous.forEach(user ->  map.put(user.id, user));

        for (User curnt: current) {
            if (map.get(curnt.id) != null) {
                User prev = map.get(curnt.id);

                if (!prev.equals(curnt)) {
                    info.changed++;
                } else {
                    info.added++;
                }
            } else {
                info.deleted++;
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

            if (id != user.id) {
                return false;
            }
            return name.equals(user.name);
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
