package top.exfree.web.estate.service;

import java.util.List;
import top.exfree.web.estate.domain.KmzBike;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * 车辆列Service接口
 * 
 * @author kmz
 * @date 2024-08-23
 */
public interface IKmzBikeService   extends IService<KmzBike>
{

    /**
     * 查询车辆列列表
     * 
     * @param kmzBike 车辆列
     * @return 车辆列集合
     */
    public List<KmzBike> selectKmzBikeList(KmzBike kmzBike);


}
