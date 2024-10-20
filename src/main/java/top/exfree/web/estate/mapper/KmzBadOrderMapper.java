package top.exfree.web.estate.mapper;

import java.util.List;
import top.exfree.web.estate.domain.KmzBadOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
 * 故障单Mapper接口
 * 
 * @author kmz
 * @date 2024-08-23
 */
public interface KmzBadOrderMapper  extends BaseMapper<KmzBadOrder>
{

    /**
     * 查询故障单列表
     * 
     * @param kmzBadOrder 故障单
     * @return 故障单集合
     */
    public List<KmzBadOrder> selectKmzBadOrderList(KmzBadOrder kmzBadOrder);


}
