package com.fescotech.apps.idm.web.controller.base;
import com.fescotech.apps.idm.admin.domain.vo.R;
import com.fescotech.apps.idm.base.biz.service.impl.BaseDataService;
import com.fescotech.apps.idm.base.biz.service.impl.BaseUserDataService;
import com.fescotech.apps.idm.base.domain.BaseData;
import com.fescotech.apps.idm.base.domain.BaseUserData;
import com.fescotech.apps.idm.base.domain.vo.BaseUserDataVo;
import com.fescotech.apps.idm.util.Pager;
import com.fescotech.apps.idm.util.ResException;
import com.fescotech.apps.idm.web.dto.PageDto;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/base/baseUserData")
public class BaseUserDataController {

    @Autowired
    private BaseUserDataService baseUserDataService;

    @Autowired
    private BaseDataService baseDataService;

    //查询所有权限集
    @RequestMapping("/list")
    @RequiresPermissions("base:baseUserData:list")
    public R getSysDataAll(PageDto dto){
        Integer page=dto.getPage();
        Integer limit=dto.getLimit();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", (page - 1) * limit);
        map.put("end", page*limit);
        //分页查询数据
        List<BaseData> baseData = baseDataService.queryList(map);
        //查询总条数
        int total = baseDataService.queryTotal();

        if(total==0){
            return R.ok().put("page",null);
        }
        Pager pageUtil = new Pager(baseData, total, limit, page);
        return R.ok().put("page", pageUtil);
    }
    /**
     * 查询权限集
     */
    @RequestMapping("/userData")
    @RequiresPermissions("base:baseUserData:userData")//@RequestBody BaseUserData baseUserData,
    public R info(PageDto dto,@RequestParam(value="userId", required=false) Long userId){
        Integer page=dto.getPage();
        Integer limit=dto.getLimit();

        BaseUserData baseUserData = new BaseUserData();
        baseUserData.setUser_id(userId);


        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", (page - 1) * limit);
        map.put("end", page*limit);


        List<BaseUserDataVo> list = baseUserDataService.queryByData(baseUserData);
        int total = baseUserDataService.queryByDataCount(baseUserData);
        if(total==0){
            return R.ok().put("page",null);
        }
        Pager pageUtil = new Pager(list, total, limit, page);
        return R.ok().put("page", pageUtil);
    }




    /**
     * 删除权限集
     */
    @RequestMapping("/delete")
    @RequiresPermissions("base:baseUserData:delete")
    public R delete(@RequestBody Long[] user_base_ids){
        baseUserDataService.deleteBatch(user_base_ids);
        return R.ok();
    }

    /**
     * 添加
     */
    @RequestMapping("/save")
    @RequiresPermissions("base:baseUserData:save")
    public R save(Long[] dataIds,Long userId){
        BaseUserData baseUserData = new BaseUserData();
        for (Long dataId : dataIds) {
            baseUserData.setUser_id(userId);
            baseUserData.setBase_id(dataId);
            //首先判断用户是否已经添加该权限
            List<BaseUserDataVo> list = baseUserDataService.queryByData(baseUserData);
            if(list.size()!=0){
                return R.error("您已经存在该权限:"+list.get(0).getDataName()+"，请勿重复添加！");
            }
            //添加权限
            baseUserDataService.insert(baseUserData);
        }
        return R.ok();
    }


    //检验数据
    private void checkBaseUserData(BaseUserData baseUserData){
        if(baseUserData.getBase_id()==0){
            throw new ResException("权限集标识不能为空");
        }
        if(baseUserData.getUser_id()==0){
            throw new ResException("用户标识不能为空");
        }
    }
}
