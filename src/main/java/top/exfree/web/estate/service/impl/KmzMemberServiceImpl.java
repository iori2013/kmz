package top.exfree.web.estate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.exfree.web.estate.domain.KmzMember;
import top.exfree.web.estate.mapper.KmzMemberMapper;
import top.exfree.web.estate.service.IKmzMemberService;

import java.util.List;

/**
 * 会员管理Service业务层处理
 * 
 * @author kmz
 * @date 2024-08-21
 */
@Service
public class KmzMemberServiceImpl extends ServiceImpl<KmzMemberMapper, KmzMember> implements IKmzMemberService
{
    @Autowired
    private KmzMemberMapper kmzMemberMapper;


    /**
     * 查询会员管理列表
     * 
     * @param kmzMember 会员管理
     * @return 会员管理
     */
    @Override
    public List<KmzMember> selectKmzMemberList(KmzMember kmzMember)
    {
        return kmzMemberMapper.selectKmzMemberList(kmzMember);
    }


}
