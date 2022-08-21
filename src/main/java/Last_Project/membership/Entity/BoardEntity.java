package Last_Project.membership.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class BoardEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long board_id;

    @Column(length = 500, nullable = false)
    private String board_title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String board_content;

    @Column(nullable = false)
    private String board_writer;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int board_view;

}
