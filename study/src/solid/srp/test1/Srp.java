package solid.srp.test1;
class User {
    private String username;
    private String password;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
}

class UserManager {
    public void createUser(User user) {
        // 사용자 생성 로직
        System.out.println("User created" + user.getUsername());
    }
}

class AuthenticationManager {
        // 사용자 인증 로직
        public boolean authenticateUser(User user) {
            if ("admin".equals(user.getUsername()) && "password".equals(user.getPassword())) {
                System.out.println("사용자 인증됨 : " + user.getUsername());
                return true;
            } else {
                System.out.println("사용자 인증에 실패했습니다. : " + user.getUsername());
                return false;
            }
    }
}

public class Srp {

    /**
     * Single Responsibility Principle(단일 책임 원칙) - SRP
     * 이 원칙은 클래스 또는 모듈이 하나의 책임만을 가져야 한다는 것이다.
     * 이것은 클래스의 변경이나 수정이 오직 하나의 이유로만 일어나야 함을 의미한다.
     * 어떤 모듈이 단 하나의 책임 만을 갖고 있다면, 특정 액터로부터 변경을 특정할 수 있으므로 해당 모듈을 변경해야
     * 하는 이유와 시점이 명확해 진다.
     *
     * 여기서 모듈이라 함은 클래스 혹은 클래스의 모음 등으로 해석할 수 있다.
     * */

    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        AuthenticationManager authManage = new AuthenticationManager();

        User newUser = new User();
        newUser.setUsername("admin");
        newUser.setPassword("123456");

        // 사용자 인증
        if (authManage.authenticateUser(newUser)) {
            System.out.println("User successfully authenticated.");
        } else {
            System.out.println("Authentication failed.");
        }
    }
}

/*
class UserManager {
    public void createUser(User user) {
        // 사용자 생성 로직
    }

    public void authenticateUser(User user) {
        // 사용자 인증 로직
    }
}
*/
// 이 코드는 UserManager 클래스가 사용자를 생성하고 인증하는 두 가지 기능을 모두 담당하고 있다.
// SRP를 위반한 것이다.
