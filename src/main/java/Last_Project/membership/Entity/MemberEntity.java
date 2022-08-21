package Last_Project.membership.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class MemberEntity {

    private int member_num; //유저 숫자

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, length = 30, unique = true)
    private String member_username; //아이디

    @Column(nullable = false)
    private String member_nickname;

    @Column(nullable = false, length = 30)
    private String member_password;

    @Column(nullable = false,length = 50)
    private String member_email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

}
