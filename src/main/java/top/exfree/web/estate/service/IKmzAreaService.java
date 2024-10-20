package top.exfree.web.estate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.exfree.web.estate.domain.KmzArea;

import java.util.List;


/**
 * 投放地区Service接口
 * 
 * @author kmz
 * @date 2024-08-21
 */
public interface IKmzAreaService   extends IService<KmzArea>
{

    /**
     * 查询投放地区列表
     * 
     * @param kmzArea 投放地区
     * @return 投放地区集合
     */
    public List<KmzArea> selectKmzAreaList(KmzArea kmzArea);


    int add(KmzArea kmzArea);
}
