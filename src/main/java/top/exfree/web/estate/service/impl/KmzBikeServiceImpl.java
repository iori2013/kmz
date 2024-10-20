package top.exfree.web.estate.service.impl;

import java.util.List;
import top.exfree.web.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.exfree.web.estate.mapper.KmzBikeMapper;
import top.exfree.web.estate.domain.KmzBike;
import top.exfree.web.estate.service.IKmzBikeService;
import top.exfree.web.common.core.text.Convert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
/**
 * 车辆列Service业务层处理
 * 
 * @author kmz
 * @date 2024-08-23
 */
@Service
public class KmzBikeServiceImpl extends ServiceImpl<KmzBikeMapper, KmzBike>  implements IKmzBikeService
{
    @Autowired
    private KmzBikeMapper kmzBikeMapper;


    /**
     * 查询车辆列列表
     * 
     * @param kmzBike 车辆列
     * @return 车辆列
     */
    @Override
    public List<KmzBike> selectKmzBikeList(KmzBike kmzBike)
    {
        return kmzBikeMapper.selectKmzBikeList(kmzBike);
    }


}
