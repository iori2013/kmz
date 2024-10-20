package top.exfree.web.config.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.exfree.web.estate.domain.KmzMember;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KmzUser {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    private KmzMember kmzMember;

    public KmzUser(KmzMember kmzMember) {
        this.kmzMember = kmzMember;
        this.userId = kmzMember.getId();
    }




}
