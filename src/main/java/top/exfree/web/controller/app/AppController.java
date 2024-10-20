package top.exfree.web.controller.app;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.exfree.web.common.core.domain.AjaxResult;
import top.exfree.web.common.utils.DateUtils;
import top.exfree.web.common.utils.PageUtils;
import top.exfree.web.config.auth.AuthInfoUtil;
import top.exfree.web.estate.absolute.TokenService;
import top.exfree.web.estate.domain.*;
import top.exfree.web.estate.service.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 通用请求处理
 * 
 * @author kmz
 */
@RestController
@RequestMapping("/app")
@Api(tags = "鉴权访问")
public class AppController
{


    @Autowired
    private IKmzMemberService kmzMemberService;

    @Autowired
    private IKmzBadOrderService kmzBadOrderService;

    @Autowired
    private IKmzOrderService kmzOrderService;

    @Autowired
    private IKmzBikeService kmzBikeService;

    @Autowired
    private IKmzAreaService kmzAreaService;

    @Autowired
    private IKmzPriceService kmzPriceService;



    @GetMapping()
    public AjaxResult KmzArea()
    {
        KmzMember kmzMember = AuthInfoUtil.getGlobeData().getUser().getKmzMember();

        return AjaxResult.success(kmzMember);

    }

    @PostMapping("/bikeList")
    public AjaxResult bikeList(@RequestParam(value = "pageNum",defaultValue = "1",required = false) Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "20",required = false) Integer pageSize) {

        PageUtils.startPage(pageNum, pageSize);
        List<KmzBike> bikeList = kmzBikeService.list();
        return AjaxResult.success(bikeList);
    }

    @PostMapping("/bikeInfo")
    public AjaxResult bikeInfo(@RequestParam(value = "no") String no) {

        KmzBike bike = kmzBikeService.getOne(new QueryWrapper<KmzBike>().eq("no", no),false);
        return AjaxResult.success(bike);
    }

    @PostMapping("/order/do")
    public AjaxResult createOrder(@RequestParam(value = "bikeId") Long bikeId,@RequestParam(value = "address",required = false,defaultValue = "出发点1") String address) {

        KmzOrder kmzOrder = new KmzOrder();

        //车辆信息
        KmzBike kmzBike = kmzBikeService.getById(bikeId);

        if(kmzBike.getState() > 0){
            return AjaxResult.error("车辆状态异常");
        }

        kmzOrder.setBikeNo(kmzBike.getBikeNo());
        kmzOrder.setOrderSn(DateUtils.dateTimeNow()+ new Random(3).nextInt(1000));
        kmzOrder.setBikeType(kmzBike.getType());
        kmzOrder.setAreaId(kmzBike.getAreaId());
        kmzOrder.setAreaName(kmzBike.getAreaName());

        kmzBike.setState(1);
        kmzBikeService.updateById(kmzBike);

        //会员信息
        KmzMember kmzMember = AuthInfoUtil.getGlobeData().getUser().getKmzMember();
        kmzOrder.setMemberId(kmzMember.getId());
        kmzOrder.setMemberName(kmzMember.getUsername());
        kmzOrder.setMemberType(kmzMember.getType());
        kmzOrder.setMemberMobile(kmzMember.getMobile());

        //价格设置
        KmzPrice kmzPrice = kmzPriceService.getById(kmzBike.getPriceId());
        kmzOrder.setOrderPrice(kmzPrice.getPartPrice1());
        kmzOrder.setPriceId(kmzBike.getPriceId());
        kmzOrder.setPriceRemark(kmzPrice.getRemark());
        kmzOrder.setStartPlace(address);

        kmzOrderService.save(kmzOrder);

        return AjaxResult.success(kmzOrder);
    }


    @PostMapping("/order/list")
    public AjaxResult orderList(@RequestParam(value = "pageNum",defaultValue = "1",required = false) Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "20",required = false) Integer pageSize) {

        KmzMember kmzMember = getCurrentUser();

        kmzOrderService.doNext();
        PageUtils.startPage(pageNum, pageSize);
        List<KmzOrder> orderList = kmzOrderService.list(new QueryWrapper<KmzOrder>().eq("member_id",kmzMember.getId()));
        return AjaxResult.success(orderList);
    }

    @PostMapping("/order/info")
    public AjaxResult orderInfo(@RequestParam(value = "sn") String sn) {
        KmzMember kmzMember = getCurrentUser();

        kmzOrderService.doNext();

        KmzOrder kmzOrder = kmzOrderService.getOne(new QueryWrapper<KmzOrder>().eq("order_sn",sn));
        if (!kmzMember.getId().equals(kmzOrder.getMemberId())) {
            return AjaxResult.error("无权限");
        }
        return AjaxResult.success(kmzOrder);
    }

    @PostMapping("/order/compute")
    public AjaxResult orderCompute(@RequestParam(value = "sn") String sn) {
        KmzMember kmzMember = getCurrentUser();

        kmzOrderService.doNext();

        KmzOrder kmzOrder = kmzOrderService.getOne(new QueryWrapper<KmzOrder>().eq("order_sn",sn));
        if (!kmzMember.getId().equals(kmzOrder.getMemberId())) {
            return AjaxResult.error("无权限");
        }
        kmzOrder.setState(1);
        kmzOrderService.updateById(kmzOrder);
        return AjaxResult.success(kmzOrder);
    }

    @PostMapping("/order/pay")
    public AjaxResult orderPay(@RequestParam(value = "sn") String sn) {
        KmzMember kmzMember = getCurrentUser();

        kmzOrderService.doNext();

        KmzOrder kmzOrder = kmzOrderService.getOne(new QueryWrapper<KmzOrder>().eq("order_sn",sn));
        if (!kmzMember.getId().equals(kmzOrder.getMemberId())) {
            return AjaxResult.error("无权限");
        }

        if (1 == kmzOrder.getState()) {
            return AjaxResult.error("没有还车");
        }
        kmzOrder.setState(2);
        kmzOrderService.updateById(kmzOrder);
        return AjaxResult.success(kmzOrder);
    }

    private KmzMember getCurrentUser() {
        return AuthInfoUtil.getGlobeData().getUser().getKmzMember();

    }


    @PostMapping("/badOrder/do")
    public AjaxResult createBadOrder(@RequestParam(value = "bikeId") Long bikeId,@RequestParam(value = "address",required = false,defaultValue = "地点1") String address,@RequestParam(value = "remark",required = false,defaultValue = "轮胎没气了") String remark) {

        KmzBadOrder kmzBadOrder = new KmzBadOrder();

        //车辆信息
        KmzBike kmzBike = kmzBikeService.getById(bikeId);

        kmzBadOrder.setBikeNo(kmzBike.getBikeNo());
        kmzBadOrder.setOrderSn("B"+DateUtils.dateTimeNow()+ new Random(3).nextInt(1000));
        kmzBadOrder.setBikeType(kmzBike.getType());
        kmzBadOrder.setAreaId(kmzBike.getAreaId());
        kmzBadOrder.setAreaName(kmzBike.getAreaName());
        kmzBike.setState(2);
        kmzBikeService.updateById(kmzBike);

        //会员信息
        KmzMember kmzMember = AuthInfoUtil.getGlobeData().getUser().getKmzMember();
        kmzBadOrder.setMemberId(kmzMember.getId());
        kmzBadOrder.setMemberName(kmzMember.getUsername());
        kmzBadOrder.setMemberType(kmzMember.getType());
        kmzBadOrder.setMemberMobile(kmzMember.getMobile());

        kmzBadOrder.setAddress(address);
        kmzBadOrder.setRemark(remark);


        kmzBadOrderService.save(kmzBadOrder);

        return AjaxResult.success(kmzBadOrder);
    }

    @PostMapping("/badOrder/list")
    public AjaxResult badOrderList(@RequestParam(value = "pageNum",defaultValue = "1",required = false) Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "20",required = false) Integer pageSize) {

        KmzMember kmzMember = getCurrentUser();

        PageUtils.startPage(pageNum, pageSize);
        List<KmzBadOrder> orderList = kmzBadOrderService.list(new QueryWrapper<KmzBadOrder>().eq("member_id",kmzMember.getId()));
        return AjaxResult.success(orderList);
    }

    @PostMapping("/badOrder/info")
    public AjaxResult badOrderInfo(@RequestParam(value = "sn") String sn) {
        KmzMember kmzMember = getCurrentUser();


        KmzBadOrder kmzOrder = kmzBadOrderService.getOne(new QueryWrapper<KmzBadOrder>().eq("order_sn",sn));
        if (!kmzMember.getId().equals(kmzOrder.getMemberId())) {
            return AjaxResult.error("无权限");
        }
        return AjaxResult.success(kmzOrder);
    }

}
