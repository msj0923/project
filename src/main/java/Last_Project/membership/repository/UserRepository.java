package Last_Project.membership.repository;

import Last_Project.membership.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//CRUD 함수를 JpaRepository가 들고 있음.
//@Repository라는 어노테이션이 없어도 IOC가 됌 이유는 JpaRepository를 상속했기 때매
public interface UserRepository extends JpaRepository<User, Integer> {

    //findBy규칙 -> username 문법
    //select * from user where username = 1?
    public User findByUsername(String username); //Jpa Query Method

}
