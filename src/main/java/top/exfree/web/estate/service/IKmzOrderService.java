package top.exfree.web.estate.service;

import java.util.List;
import top.exfree.web.estate.domain.KmzOrder;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * 车辆订单Service接口
 * 
 * @author kmz
 * @date 2024-08-23
 */
public interface IKmzOrderService   extends IService<KmzOrder>
{

    /**
     * 查询车辆订单列表
     * 
     * @param kmzOrder 车辆订单
     * @return 车辆订单集合
     */
    public List<KmzOrder> selectKmzOrderList(KmzOrder kmzOrder);


    void doNext();
}
