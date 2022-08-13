package Last_Project.membership.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

    @Entity
    @Data
    @NoArgsConstructor
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String username;
        private String password;
        private String repassword;
        private String p_h;
        private String birth;
        private String email;

        private String role; //ROLE_ADMIN
        private String provider;
        private String providerId;

        @CreationTimestamp
        private Timestamp createDate;


        @Builder
        public User(String username, String password, String repassword, String p_h, String birth, String email,
                    String role, String provider, String providerId, Timestamp createDate) {
            this.username = username;
            this.password = password;
            this.repassword = repassword;
            this.p_h = p_h;
            this.birth = birth;
            this.email = email;
            this.role = role;
            this.provider = provider;
            this.providerId = providerId;
            this.createDate = createDate;
        }
    }
