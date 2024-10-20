package top.exfree.web.estate.service;

import java.util.List;
import top.exfree.web.estate.domain.KmzMember;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * 会员管理Service接口
 * 
 * @author kmz
 * @date 2024-08-21
 */
public interface IKmzMemberService   extends IService<KmzMember>
{

    /**
     * 查询会员管理列表
     * 
     * @param kmzMember 会员管理
     * @return 会员管理集合
     */
    public List<KmzMember> selectKmzMemberList(KmzMember kmzMember);


}
