package com.fescotech.apps.idm.web.controller.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fescotech.apps.idm.admin.domain.vo.R;
import com.fescotech.apps.idm.admin.domain.vo.SysUserVo;
import com.fescotech.apps.idm.base.biz.service.IBaseDictService;
import com.fescotech.apps.idm.base.biz.service.IBaseFunctionService;
import com.fescotech.apps.idm.base.biz.service.IBaseRoleService;
import com.fescotech.apps.idm.base.biz.service.IBaseSysService;
import com.fescotech.apps.idm.base.domain.BaseDict;
import com.fescotech.apps.idm.base.domain.BaseFunction;
import com.fescotech.apps.idm.base.domain.BaseFunctionType;
import com.fescotech.apps.idm.base.domain.BaseRole;
import com.fescotech.apps.idm.base.domain.BaseRoleFunction;
import com.fescotech.apps.idm.base.domain.BaseSys;
import com.fescotech.apps.idm.base.domain.vo.BaseFunctionVo;
import com.fescotech.apps.idm.base.domain.vo.BaseRoleVo;
import com.fescotech.apps.idm.util.Pager;
import com.fescotech.apps.idm.web.controller.AbstractController;
import com.fescotech.apps.idm.web.util.SysUserUtils;

/**
 * 系统菜单
 */
@RestController
@RequestMapping("/base/func")
public class BaseFunctionController extends AbstractController {

	@Autowired
	private IBaseFunctionService baseFunctionService;
	@Autowired
	private IBaseRoleService baseRoleService;
	@Autowired
	private IBaseSysService baseSysService;
	@Autowired
	private IBaseDictService baseDictService;
	
	/**
	 * 获取权限列表
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping("/list")
	@RequiresPermissions("base:func:list")
	public R list(Integer page, Integer limit,String order,String sidx, @RequestParam(value="functionId", required=false) String functionId){
		if (functionId != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			BaseFunction baseFunction = new BaseFunction();
			map.put("start", (page - 1) * limit);
			map.put("end", page*limit);
			String[] sysIdAndFunctionId  = functionId.split("_");
			String sysID = sysIdAndFunctionId[0];
			if (sidx != null && !sidx.equals("")) {
				if (sidx.equals("functionName")) {
					sidx = "FUNCTION_NAME";
				} else if (sidx.equals("parentName")) {
					sidx = "PARENT_ID";
				} else if (sidx.equals("sysName")) {
					sidx = "SYS_ID";
				} else if (sidx.equals("creatorName")) {
					sidx = "CREATOR_NAME";
				} else if (sidx.equals("createTime")) {
					sidx = "CREATE_TIME";
				}
				map.put("orderBy", sidx);
				map.put("orderFlag", order);
			}
			if (sysIdAndFunctionId.length > 1) {
				String id = sysIdAndFunctionId[1];
				map.put("functionId", id);
				map.put("parentId", id);
				map.put("isLeaf", 0);
				baseFunction.setFunctionId(Long.parseLong(id));
				baseFunction.setParentId(Long.parseLong(id));
				baseFunction.setIsLeaf(0);
			} else {
				map.put("sysId", sysID);
				baseFunction.setSysId(Integer.parseInt(sysID));
			}
			int total = baseFunctionService.queryBaseFunctionCount(baseFunction);
			if (total > 0) {
				List<BaseFunctionVo> list = baseFunctionService.queryPagelist(map);
				for (int i = 0; i < list.size(); i++) {
					BaseSys baseSys = baseSysService.queryObject(Long.valueOf(list.get(i).getSysId().toString()));
					if (baseSys != null && baseSys.getSysName() != null) 
						list.get(i).setSysName(baseSys.getSysName());
					if (list.get(i).getIsLeaf() == 0) {
						BaseFunctionVo baseFunctionVo = baseFunctionService.queryFunctionById(list.get(i).getParentId());
						if (baseFunctionVo != null && baseFunctionVo.getFunctionName() != null) 
							list.get(i).setParentName(baseFunctionVo.getFunctionName());
					}
					if (list.get(i).getFunctionType() != null) {
						BaseDict baseDict = baseDictService.queryDictName("FUNCTION_TYPE", list.get(i).getFunctionType().toString());
						list.get(i).setFunctionTypeName(baseDict.getDictName());
					}
				}
				Pager pageUtil = new Pager(list, total, limit, page);
				return R.ok().put("page", pageUtil);
			} else {
				return R.ok();
			}
		} else  {
			return R.ok();
		}
	}
	
	/**
	 * 权限功能菜单
	 */
	@RequestMapping("/perms/{roleId}")
	@RequiresPermissions("base:func:perms")
	public R perms(@PathVariable("roleId") Long roleId){
		//角色管理授权功能菜单
		BaseRole role = baseRoleService.loadBaseRole(roleId);
		if (role != null && role.getSysId() != null) {
			BaseFunction baseFunction = new BaseFunction();
			baseFunction.setSysId(role.getSysId());
			List<BaseFunctionVo> funcList = baseFunctionService.queryList(baseFunction);
			JSONArray nodes = new JSONArray();
			if (funcList != null && funcList.size() > 0) {
				for (int i = 0; i < funcList.size(); i++) {
					JSONObject node = new JSONObject();
					node.put("functionId", funcList.get(i).getSysId().toString() + "_" + funcList.get(i).getFunctionId());
					node.put("parentId", funcList.get(i).getSysId().toString() + "_" + funcList.get(i).getParentId());
					node.put("name",  funcList.get(i).getName());
					nodes.add(node);
				}
			}
			return R.ok().put("funcList", nodes);
		}
		return R.error();
	}
	
	/**
	 * 权限管理功能树构建
	 */
	@RequestMapping("/getFuncTree")
	@RequiresPermissions("base:func:funcTree")
	public R getTree(@RequestParam(value="id", required=false) String id){
		if (id == null) {//权限管理功能菜单,初始化根目录系统
			BaseSys baseSys = new BaseSys();
			baseSys.setOpenFlag(1);
			SysUserVo sysUser = SysUserUtils.getDbSysUser();
			List<BaseSys> tempSysList = baseSysService.queryList(baseSys);
			List<BaseSys> baseSysList = new ArrayList<BaseSys>();
			String[] sysIds = sysUser.getSysId().split(",");
			for (int i = 0; i < sysIds.length; i++) {
				for (int j = 0; j < tempSysList.size(); j++) {
					if (sysIds[i].equals(tempSysList.get(j).getSysId().toString())) {
						baseSysList.add(tempSysList.get(j));
					}
				}
			}
			JSONArray rootNodes = new JSONArray();
			if (baseSysList != null && baseSysList.size() > 0) {
				for (int i = 0; i < baseSysList.size(); i++) {
					JSONObject rootNode = new JSONObject();
					rootNode.put("id", baseSysList.get(i).getSysId() + "_");
					rootNode.put("name", baseSysList.get(i).getSysName());
					rootNode.put("pId", "");
					rootNode.put("sysId", baseSysList.get(i).getSysId());
					rootNode.put("isParent", true);
					rootNodes.add(rootNode);
				}
			}
			return R.ok().put("funcList", rootNodes);
		} else {
			JSONArray childrenNodes = new JSONArray();
			BaseFunction baseFunction = new BaseFunction();
			String[] sysIdAndFunctionId  = id.split("_");
			String sysID = sysIdAndFunctionId[0];
			if (sysIdAndFunctionId.length > 1) {//正常的功能节点
				String functionId = sysIdAndFunctionId[1];
				baseFunction.setSysId(Integer.parseInt(sysID));
				baseFunction.setParentId(Long.parseLong(functionId));
				baseFunction.setIsLeaf(0);
			} else {//系统下的一级节点
				baseFunction.setSysId(Integer.parseInt(sysID));
				baseFunction.setParentId(Long.parseLong(sysID));
				baseFunction.setIsLeaf(1);
			}
			List<BaseFunctionVo> funcList = baseFunctionService.queryList(baseFunction);//子节点
			if (funcList != null && funcList.size() > 0) {
				for (int i = 0; i < funcList.size(); i++) {
					JSONObject childrenNode = new JSONObject();
					childrenNode.put("id", sysID + "_" + funcList.get(i).getFunctionId());
					childrenNode.put("name", funcList.get(i).getFunctionName());
					childrenNode.put("pId", id);
					childrenNode.put("sysId", funcList.get(i).getSysId());
					List<BaseFunctionVo> hasChildrenList = getChildrenList(funcList.get(i).getFunctionId(),funcList.get(i).getSysId());
					if (hasChildrenList == null || hasChildrenList.size() < 1) 
						childrenNode.put("isParent", false);
					else 
						childrenNode.put("isParent", true);
					childrenNodes.add(childrenNode);
				}
			}
			return R.ok().put("funcList", childrenNodes);
		}
	}
	
	/**
	 * 校验是否为叶子节点           
	 * @param parentId
	 * @return
	 */
	private List<BaseFunctionVo> getChildrenList(Long parentId, Integer sysID) {
		BaseFunction baseFunction = new BaseFunction();
		baseFunction.setParentId(parentId);
		baseFunction.setSysId(sysID);
		baseFunction.setIsLeaf(0);
		List<BaseFunctionVo> funcList = baseFunctionService.queryList(baseFunction);
		return funcList;
	}
	
	/**
	 * 角色授权（先删除已有权限，再添加选择的权限）
	 */
	@RequestMapping("/auth")
	@RequiresPermissions("base:func:auth")
	public R auth(@RequestBody BaseRoleVo role){
		if (role.getRoleId() == null) {
			return R.error("角色ID不能为空");
		}
		baseFunctionService.deleteRoleFunction(role.getRoleId());
		if (role.getFunctionIdList() != null && role.getFunctionIdList().size() > 0) {
			for (int i = 0; i < role.getFunctionIdList().size(); i++) {
				BaseRoleFunction baseRoleFunction = new BaseRoleFunction();
				baseRoleFunction.setRoleId(role.getRoleId());
				Long functionId = Long.valueOf(role.getFunctionIdList().get(i).split("_")[1]);
				baseRoleFunction.setFunctionId(functionId);
				baseFunctionService.insertRoleFunction(baseRoleFunction);
			}
		} 
		return R.ok();
	}
	
	/**
	 * 查询功能信息（新增、修改操作）
	 */
	@RequestMapping("/info/{functionId}")
	@RequiresPermissions("base:func:info")
	public R info(@PathVariable("functionId") String functionId){
		List<BaseDict> baseDictList = baseDictService.queryBaseDictItemList("FUNCTION_TYPE");
		List<BaseFunctionType> baseFunctionTypeList = new ArrayList<BaseFunctionType>();
		if (baseDictList != null && baseDictList.size() > 0) {
			for (int i = 0; i < baseDictList.size(); i++) {
				BaseFunctionType baseFunctionType = new BaseFunctionType();
				baseFunctionType.setFunctionTypeCode(baseDictList.get(i).getDictCode());
				baseFunctionType.setFunctionTypeName(baseDictList.get(i).getDictName());
				baseFunctionTypeList.add(baseFunctionType);
			}
		}
		if (functionId.contains("_")) {//新增
			String[] sysIdAndFunctionId  = functionId.split("_");
			String sysID = sysIdAndFunctionId[0];
			if (sysIdAndFunctionId.length > 1) {
				String id = sysIdAndFunctionId[1];
				BaseFunctionVo baseFunctionVo = baseFunctionService.queryFunctionById(Long.parseLong(id));
				BaseFunctionVo cBaseFunctionVo = new BaseFunctionVo();
				if (baseFunctionVo != null) {
					cBaseFunctionVo.setParentId(baseFunctionVo.getFunctionId());
					cBaseFunctionVo.setParentName(baseFunctionVo.getFunctionName());
					cBaseFunctionVo.setSysId(baseFunctionVo.getSysId());
				}
				BaseSys baseSys = baseSysService.queryObject(Long.valueOf(sysID));
				if (baseSys != null) {
					cBaseFunctionVo.setSysName(baseSys.getSysName());
				}
				cBaseFunctionVo.setBaseFunctionTypeList(baseFunctionTypeList);
				return R.ok().put("func", cBaseFunctionVo);
			} else {
				BaseSys baseSys = baseSysService.queryObject(Long.valueOf(sysID));
				BaseFunctionVo baseFunctionVo = new BaseFunctionVo();
				if (baseSys != null) {
					baseFunctionVo.setSysId(Integer.parseInt(sysID));
					baseFunctionVo.setSysName(baseSys.getSysName());
				}
				baseFunctionVo.setBaseFunctionTypeList(baseFunctionTypeList);
				return R.ok().put("func", baseFunctionVo);
			}
		} else {//修改
			BaseFunctionVo baseFunctionVo = baseFunctionService.queryFunctionById(Long.parseLong(functionId));
			BaseSys baseSys = baseSysService.queryObject(Long.valueOf(baseFunctionVo.getSysId().toString()));
			if (baseFunctionVo.getIsLeaf() == 0) {
				BaseFunctionVo pBaseFunctionVo = baseFunctionService.queryFunctionById(baseFunctionVo.getParentId());
				if (pBaseFunctionVo != null)
					baseFunctionVo.setParentName(pBaseFunctionVo.getFunctionName());
				if (baseSys != null) 
					baseFunctionVo.setSysName(baseSys.getSysName());
			} else {
				if (baseSys != null) 
					baseFunctionVo.setSysName(baseSys.getSysName());
			}
			baseFunctionVo.setBaseFunctionTypeList(baseFunctionTypeList);
			return R.ok().put("func", baseFunctionVo);
		}
	}
	
	/**
	 * 保存功能
	 * @throws ParseException 
	 */
	@RequestMapping("/save")
	@RequiresPermissions("base:func:save")
	public R save(@RequestBody BaseFunctionVo baseFunctionVo) throws ParseException{
		if(StringUtils.isBlank(baseFunctionVo.getName())){
			return R.error("功能名称不能为空");
		}
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		baseFunctionVo.setOperTime(sdf.parse(sdf.format(now)));
		baseFunctionVo.setCreateTime(sdf.parse(sdf.format(now)));
		SysUserVo sysUser = SysUserUtils.getDbSysUser();
		baseFunctionVo.setCreator(sysUser.getUserId());
		baseFunctionVo.setCreatorName(sysUser.getUsername());
		if (baseFunctionVo.getParentId() == null) {
			baseFunctionVo.setParentId(Long.valueOf(baseFunctionVo.getSysId().toString()));
			baseFunctionVo.setIsLeaf(1);
		} else {
			baseFunctionVo.setIsLeaf(0);
		}
		baseFunctionService.insertFunction(baseFunctionVo);
		return R.ok();
	}
	
	/**
	 * 修改功能
	 * @throws ParseException 
	 */
	@RequestMapping("/update")
	@RequiresPermissions("base:func:update")
	public R update(@RequestBody BaseFunctionVo baseFunctionVo) throws ParseException{
		if(StringUtils.isBlank(baseFunctionVo.getName())){
			return R.error("功能名称不能为空");
		}
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		baseFunctionVo.setOperTime(sdf.parse(sdf.format(now)));
		baseFunctionService.updateFunction(baseFunctionVo);
		return R.ok();
	}
	
	/**
	 * 删除功能
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("base:func:delete")
	public R delete(@RequestBody Long[] functionIds){
		for (int i = 0; i < functionIds.length; i++) {
			BaseFunctionVo baseFunctionVo = baseFunctionService.queryFunctionById(functionIds[i]);
			BaseFunction baseFunction = new BaseFunction();
			baseFunction.setParentId(functionIds[i]);
			List<BaseFunctionVo> funcList = baseFunctionService.queryList(baseFunction);
			if (funcList != null && funcList.size() > 0) {
				return R.ok().put("isRootNode", baseFunctionVo.getFunctionName()+"为根节点不允许删除");
			}
		}
		baseFunctionService.deleteBatch(functionIds);
		return R.ok();
	}
	
}
