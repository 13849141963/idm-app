package com.fescotech.apps.idm.web.controller.base;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fesco.pafa.exceptions.MessageException;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fescotech.apps.idm.admin.domain.vo.R;
import com.fescotech.apps.idm.base.biz.service.IBaseOrganizationService;
import com.fescotech.apps.idm.base.domain.BaseOrganization;
import com.fescotech.apps.idm.base.domain.vo.BaseOrganizationVo;
import com.fescotech.apps.idm.util.Pager;
import com.fescotech.apps.idm.util.ResException;
import com.fescotech.apps.idm.web.controller.AbstractController;
import com.fescotech.apps.idm.web.dto.PageDto;

/**
 * 机构
 */
@RestController
@RequestMapping("/base/org")
public class BaseOrganizationController extends AbstractController {
	@Autowired
	private IBaseOrganizationService baseOrganizationService;
	
	/**
	 * 所有机构列表
	 */	
	@RequestMapping("/list")
	@RequiresPermissions("base:org:list")
	public R list(PageDto dto,@RequestParam(value="orgName", required=false) String orgName, @RequestParam(value="corpId", required=false) String corpId, @RequestParam(value="orgId", required=false) Long orgId){
		Integer page=dto.getPage();
		Integer limit=dto.getLimit();
		/*if(orgName!=null) {
			try {
				orgName = new String(orgName.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new MessageException(e.getMessage());
			}

		}*/
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", (page - 1) * limit + 1);
		map.put("end", page*limit);
		map.put("orderBy", dto.getSidx());
		map.put("orderFlag", dto.getOrder());
		map.put("orgId", orgId);
		map.put("corpId", corpId);
		map.put("orgName",orgName);
		//查询列表数据
		List<BaseOrganization> orgList = baseOrganizationService.queryList(map);
		int total = baseOrganizationService.queryTotal(map);
		
		Pager pageUtil = new Pager(orgList, total, limit, page);
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 机构树
	 */
	@RequestMapping("/select")
	@RequiresPermissions("base:org:select")
	public R select(){
		//查询列表数据
		List<BaseOrganizationVo> orgList = baseOrganizationService.queryTreeList();
		return R.ok().put("orgList", orgList);
	}	
	
	/**
	 * 机构信息
	 */
	@RequestMapping("/info/{orgId}")
	@RequiresPermissions("base:org:info")
	public R info(@PathVariable("orgId") Long orgId){
		BaseOrganization  org = baseOrganizationService.queryObject(orgId);
		return R.ok().put("org", org);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("base:org:save")
	public R save(@RequestBody BaseOrganization org){
		//数据校验
		verifyForm(org);
		
		baseOrganizationService.save(org);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("base:org:update")
	public R update(@RequestBody BaseOrganization org){
		//数据校验
		verifyForm(org);
				
		baseOrganizationService.update(org);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("base:org:delete")
	public R delete(@RequestBody Long[] orgIds){
		
		for(Long orgId:orgIds){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orgId", orgId);
			int total = baseOrganizationService.queryTotal(map);
			if(total>1){
				return R.error("有子节点的父节点不能删除!");
			}
		}
		
		baseOrganizationService.deleteBatch(orgIds);
		
		return R.ok();
	}


	/*//查询单个机构信息
	@RequestMapping("/queryByOrgName")
	@RequiresPermissions("base:org:queryByOrgName")
	public R queryByOrgName(PageDto dto,  @RequestParam(value="orgName", required=false) String orgName){
		Integer page=dto.getPage();
		Integer limit=dto.getLimit();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", (page - 1) * limit + 1);
		map.put("end", page*limit);
		map.put("orgName",orgName);
		//查询列表数据
		List<BaseOrganization> orgList = baseOrganizationService.queryList(map);
		int total = baseOrganizationService.queryTotal(map);

		Pager pageUtil = new Pager(orgList, total, limit, page);
		return R.ok().put("page", pageUtil);
	}*/




	
	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(BaseOrganization org){
		
		if(StringUtils.isBlank(org.getOrgName())){
			throw new ResException("机构名称不能为空");
		}
		
		if(org.getParentId() == null){
			throw new ResException("上级机构不能为空");
		}			
	}
	
	/**
	 * 机构列表
	 */
	@RequestMapping("/selectOrgSel")
	public R selectOrgSel() {
		// 查询列表数据
		List<BaseOrganization> list = baseOrganizationService.queryOrgList(new HashMap<String, Object>());
		return R.ok().put("list", list);
	}
}
