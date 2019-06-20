package com.fescotech.apps.idm.web.controller.base;
import com.fescotech.apps.idm.admin.domain.vo.R;
import com.fescotech.apps.idm.base.biz.service.impl.BaseDataService;
import com.fescotech.apps.idm.base.domain.BaseData;
import com.fescotech.apps.idm.util.Pager;
import com.fescotech.apps.idm.util.ResException;
import com.fescotech.apps.idm.web.dto.PageDto;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/****
 *
 * 数据权限及管理
 */
@RestController
@RequestMapping("/base/data")
public class BaseDataController {


    @Autowired
    private BaseDataService baseDataService;

    @RequiresPermissions("base:baseData:list")
    @RequestMapping("/list")
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
     * 添加
     */
    @RequiresPermissions("base:baseData:save")
    @RequestMapping("/save")
    public R save(@RequestBody BaseData baseData){
        //校验参数
        checkBaseData(baseData);
        baseDataService.insert(baseData);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequiresPermissions("base:baseData:update")
    @RequestMapping("/update")
    public R update(@RequestBody BaseData baseData){
        //数据校验
        checkBaseData(baseData);
        baseDataService.update(baseData);
        return R.ok();
    }

    /**
     * 查询单个数据权限集的信息
     */
    @RequiresPermissions("base:baseData:info")
    @RequestMapping("/info/{dataId}")
    public R info(@PathVariable("dataId") Long dataId){
        BaseData baseData = baseDataService.queryByKey(dataId);

        return R.ok().put("baseData", baseData);
    }
    /**
     * 模糊查询权限集
     */
    @RequestMapping("/fuzzy")
    @RequiresPermissions("base:baseData:fuzzy")
    public R info(@RequestBody BaseData baseData,Integer limit,Integer page){

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", (page - 1) * limit);
        map.put("end", page*limit);
        List<BaseData> list = baseDataService.queryByDataName(baseData);
        int total = baseDataService.queryFuzzyTotal(baseData);
        if(total==0){
            return R.ok().put("page",null);
        }
        Pager pageUtil = new Pager(list, total, limit, page);
        return R.ok().put("page", pageUtil);
    }




    /**
     * 删除
     */
    @RequiresPermissions("base:baseData:delete")
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] dataIds){
        baseDataService.deleteBatch(dataIds);
        return R.ok();
    }


    //检验数据
    private void checkBaseData(BaseData baseData){
        if(StringUtils.isBlank(baseData.getDataName())){
            throw new ResException("权限集名称不能为空");
        }
    }








}
