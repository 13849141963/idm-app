package com.fescotech.apps.idm.web.controller.base;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fescotech.apps.idm.base.domain.BaseOrganization;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fescotech.apps.idm.admin.domain.vo.R;
import com.fescotech.apps.idm.base.biz.service.IBaseCorpinfoService;
import com.fescotech.apps.idm.base.biz.service.IBaseOrganizationService;
import com.fescotech.apps.idm.base.domain.BaseCorpinfo;
import com.fescotech.apps.idm.base.domain.vo.BaseCorpinfoVo;
import com.fescotech.apps.idm.util.Pager;
import com.fescotech.apps.idm.util.ResException;
import com.fescotech.apps.idm.web.controller.AbstractController;
import com.fescotech.apps.idm.web.dto.PageDto;

/**
 * 机构
 */
@RestController
@RequestMapping("/base/corp")
public class BaseCorpinfoController extends AbstractController {
	@Autowired
	private IBaseCorpinfoService baseCorpinfoService;
	@Autowired
	private IBaseOrganizationService baseOrganizationService;
	
	/**
	 * 所有机构列表
	 */	
	@RequestMapping("/list")
	@RequiresPermissions("base:corp:list")
	public R list(PageDto dto, @RequestParam(value="corpId", required=false) Long corpId){
		Integer page=dto.getPage();
		Integer limit=dto.getLimit();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", (page - 1) * limit + 1);
		map.put("end", page*limit);
		map.put("orderBy", dto.getSidx());
		map.put("orderFlag", dto.getOrder());
		map.put("corpId", corpId);
		//查询列表数据
		List<BaseCorpinfo> corpList = baseCorpinfoService.queryList(map);
		int total = baseCorpinfoService.queryTotal(map);
		
		Pager pageUtil = new Pager(corpList, total, limit, page);
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 机构树
	 */
	@RequestMapping("/select")
	@RequiresPermissions("base:corp:select")
	public R select(){
		//查询列表数据
		List<BaseCorpinfoVo> corpList = baseCorpinfoService.queryTreeList();		
		return R.ok().put("corpList", corpList);
	}	
	
	/**
	 * 机构信息
	 */
	@RequestMapping("/info/{corpId}")
	@RequiresPermissions("base:corp:info")
	public R info(@PathVariable("corpId") Long corpId){
		BaseCorpinfo  corp = baseCorpinfoService.queryObject(corpId);
		return R.ok().put("corp", corp);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("base:corp:save")
	public R save(@RequestBody BaseCorpinfo corp){
		//数据校验
		verifyForm(corp);
		//添加公司
		baseCorpinfoService.save(corp);
		//添加公司对应的机构
		BaseOrganization org = new BaseOrganization();
		org.setAddress(corp.getOfficeAddr());
		org.setOrgName(corp.getCompanyName());
		org.setDescription(corp.getDescription());
		org.setShortName(corp.getShortName());
		baseOrganizationService.save(org);

		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("base:corp:update")
	public R update(@RequestBody BaseCorpinfo corp){
		//数据校验
		verifyForm(corp);
				
		baseCorpinfoService.update(corp);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("base:corp:delete")
	public R delete(@RequestBody Long[] corpIds){
		
		for(Long corpId:corpIds){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("corpId", corpId);
			int total = baseCorpinfoService.queryTotal(map);
			if(total>1){
				return R.error("有子节点的父节点不能删除!");
			}
		}
		for(Long corpId:corpIds){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("corpId", corpId);
			int total = baseOrganizationService.queryTotal(map);
			if(total>0){
				return R.error("有下属机构的公司不能删除!");
			}
		}
		
		//删除机构中的公司
		for (Long corpId : corpIds) {
            BaseCorpinfo baseCorpinfo = baseCorpinfoService.queryObject(corpId);

            BaseOrganization org = new BaseOrganization();
            org.setOrgName(baseCorpinfo.getCompanyName());
            List<BaseOrganization> baseOrganizations = baseOrganizationService.queryAllOrg(org);
            if(!baseOrganizations.isEmpty()) {
                Long[] orgId = {baseOrganizations.get(0).getOrgId()};
                //删除公司
                baseOrganizationService.deleteBatch(orgId);
            }
        }

        //删除公司
        baseCorpinfoService.deleteBatch(corpIds);
		return R.ok();
	}


	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(BaseCorpinfo corp){
		
		if(StringUtils.isBlank(corp.getCompanyName())){
			throw new ResException("机构名称不能为空");
		}
		
		if(corp.getParentId() == null){
			throw new ResException("上级公司不能为空");
		}			
	}
}
