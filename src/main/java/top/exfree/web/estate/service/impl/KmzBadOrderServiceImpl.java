package top.exfree.web.estate.service.impl;

import java.util.List;
import top.exfree.web.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.exfree.web.estate.mapper.KmzBadOrderMapper;
import top.exfree.web.estate.domain.KmzBadOrder;
import top.exfree.web.estate.service.IKmzBadOrderService;
import top.exfree.web.common.core.text.Convert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
/**
 * 故障单Service业务层处理
 * 
 * @author kmz
 * @date 2024-08-23
 */
@Service
public class KmzBadOrderServiceImpl extends ServiceImpl<KmzBadOrderMapper, KmzBadOrder>  implements IKmzBadOrderService
{
    @Autowired
    private KmzBadOrderMapper kmzBadOrderMapper;


    /**
     * 查询故障单列表
     * 
     * @param kmzBadOrder 故障单
     * @return 故障单
     */
    @Override
    public List<KmzBadOrder> selectKmzBadOrderList(KmzBadOrder kmzBadOrder)
    {
        return kmzBadOrderMapper.selectKmzBadOrderList(kmzBadOrder);
    }


}
