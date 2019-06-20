package com.fescotech.apps.idm.web.controller.base;

import java.io.UnsupportedEncodingException;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fescotech.apps.idm.admin.domain.vo.R;
import com.fescotech.apps.idm.base.biz.service.IBaseDataCustomerService;
import com.fescotech.apps.idm.base.domain.BaseDataCustomer;
import com.fescotech.apps.idm.util.Pager;
import com.fescotech.apps.idm.util.ResException;
import com.fescotech.apps.idm.web.dto.PageDto;
import com.fescotech.apps.idm.web.util.WebServerUtils;

/****
 *
 * 数据权限及管理
 */
@RestController
@RequestMapping("/base/dataCustomer")
public class BaseDataCustomerController {

	@Autowired
	private IBaseDataCustomerService baseDataCustomerService;

	@RequestMapping("/list")
	@RequiresPermissions("base:dataCustomer:list")
	public R getSysDataAll(@RequestParam Map<String,String> dto) {
		Integer page = Integer.parseInt(dto.get("page").toString());
		Integer limit = Integer.parseInt(dto.get("limit").toString());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", (page - 1) * limit);
		map.put("end", page * limit);
		map.put("baseId", dto.get("baseId").toString());
		map.put("customerParentId", dto.get("customerParentId").toString());
		// 分页查询数据
		List<BaseDataCustomer> baseData = baseDataCustomerService.queryList(map);
		// 查询总条数
		int total = baseDataCustomerService.queryTotal();

		if (total == 0) {
			return R.ok().put("page", null);
		}
		Pager pageUtil = new Pager(baseData, total, limit, page);
		return R.ok().put("page", pageUtil);

	}

	/**
	 * 添加
	 */
	@RequestMapping("/save")
	@RequiresPermissions("base:dataCustomer:save")
	public R save(@RequestBody BaseDataCustomer baseDataCustomer) {
		// 校验参数
		checkBaseData(baseDataCustomer);
		baseDataCustomerService.insert(baseDataCustomer);
		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	// @RequiresPermissions("base:baseData:update")
	public R update(@RequestBody BaseDataCustomer baseDataCustomer) {
		// 数据校验
		checkBaseData(baseDataCustomer);
		baseDataCustomerService.update(baseDataCustomer);
		return R.ok();
	}

	/**
	 * 查询单个数据权限集的信息
	 */
	@RequestMapping("/info/{dataCustomerId}")
	@RequiresPermissions("base:dataCustomer:info")
	public R info(@PathVariable("dataCustomerId") Long dataCustomerId) {
		BaseDataCustomer baseDataCustomer = baseDataCustomerService.queryByKey(dataCustomerId);
		return R.ok().put("baseDataCustomer", baseDataCustomer);
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("base:dataCustomer:delete")
	public R delete(@RequestBody Long[] dataIds) {
		baseDataCustomerService.deleteBatch(dataIds);
		return R.ok();
	}

	/**
	 * 权限集添加
	 */
	@RequestMapping("/getCustInfoByCompanyName")
	@RequiresPermissions("base:dataCustomer:getCustInfoByCompanyName")
	public R getCustInfoByCompanyName(@RequestParam Map<String,String> map) {
       // String str=WebServerUtils.getCustInfoByCompanyName(1, 99999, map.get("companyName").toString());
		String page = map.get("page").toString();
		String limit = map.get("limit").toString();
		Pager pageUtil=null;
		if(map.containsKey("companyName")){
			String companyName = map.get("companyName").toString();
			String str=WebServerUtils.getCustInfoByCompanyName(Integer.parseInt(page), Integer.parseInt(limit),companyName);
			String total=JSON.parseObject(JSON.parseObject(str).get("successResult").toString()).get("totalCount").toString();
			List<Map> baseData  = JSON.parseArray(JSON.parseObject(JSON.parseObject(str).get("successResult").toString()).get("pageRecords").toString(),Map.class);
			pageUtil = new Pager(baseData, Integer.parseInt(total), Integer.parseInt(limit), Integer.parseInt(page));

		}
		return R.ok().put("page", pageUtil);
	}
	/**
	 * 
	 */
	@RequestMapping("/getBusiCustListByCustId")
	@RequiresPermissions("base:dataCustomer:getBusiCustListByCustId")
	public R getBusiCustListByCustId(@RequestParam Map<String, String> reqMap) {


		Map queryTreeNodesMap=new HashMap<>();
		queryTreeNodesMap.put("baseId", reqMap.get("baseId").toString());
		queryTreeNodesMap.put("customerId", reqMap.get("custId").toString());
		List<BaseDataCustomer> queryTreeNodesList=baseDataCustomerService.queryTreeNodes(queryTreeNodesMap);
		
		String custId = reqMap.get("custId").toString();
		String custName = reqMap.get("custName").toString();
		
		JSONArray nodes = new JSONArray();

		JSONObject node = new JSONObject();
		node.put("functionId", custId);
		node.put("parentId", "0");
		node.put("name", custId+","+custName);
		node.put("checked", getNodesCheckedTrueFalse(queryTreeNodesList,custId));
		nodes.add(node);

		String jsonStr = WebServerUtils.getBusiCustListByCustId(JSON.toJSONString(reqMap));

		List<Map> list = JSON.parseArray(JSON.parseObject(jsonStr).get("successResult").toString(), Map.class);
		
		for (int i = 0; i < list.size(); i++) {
			JSONObject nodeNew = new JSONObject();
			nodeNew.put("functionId",list.get(i).get("busiCustId") );
			nodeNew.put("parentId", custId);
			//nodeNew.put("parentId", list.get(i).get("busiCustId"));
			nodeNew.put("name",  list.get(i).get("busiCustId")+","+list.get(i).get("busiCustName"));
			nodeNew.put("checked", getNodesCheckedTrueFalse(queryTreeNodesList,list.get(i).get("busiCustId").toString()));
			nodes.add(nodeNew);
		}
		//System.out.println(map.toString() + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++==");
		//System.out.println(jsonStr);
		return R.ok().put("funcList", nodes);
	}
	/**
	 * 
	 */
	@RequestMapping("/saveTree")
	@RequiresPermissions("base:dataCustomer:saveTree")
	public R saveTree(@RequestBody Map<String, String> reqMap) {
		Map param=new HashMap<>();
		param.put("baseId", reqMap.get("baseId"));
		param.put("customerId", reqMap.get("custId"));
		baseDataCustomerService.deleteTree(param);
		
		
		List<Map> list=JSON.parseArray(reqMap.get("functionIdList").toString(), Map.class);
		for (Map eachMap : list) {
			BaseDataCustomer baseDataCustomer=new BaseDataCustomer();
			baseDataCustomer.setBaseId(Long.parseLong(reqMap.get("baseId")));
			
			baseDataCustomer.setCustomerId(Long.parseLong(eachMap.get("functionId").toString()));
			baseDataCustomer.setCustomerName(eachMap.get("name").toString());
			baseDataCustomer.setCustomerParentId(Long.parseLong(eachMap.get("parentId").toString()));
			baseDataCustomerService.insert(baseDataCustomer);
		}
		return R.ok();
	}	
	
	@RequestMapping("/deleteTree")
	@RequiresPermissions("base:dataCustomer:deleteTree")
	public R deleteTree(@RequestBody Map<String, String> map) {
		Map param=new HashMap<>();
		param.put("baseId", map.get("baseId"));
		param.put("customerId", map.get("custId").toString());
		baseDataCustomerService.deleteTree(param);
		return R.ok();
	}
	// 检验数据
	private void checkBaseData(BaseDataCustomer baseDataCustomer) {
		if (baseDataCustomer.getBaseId()==null) {
			throw new ResException("数据集权限id不能为空");
		}
		if (baseDataCustomer.getCustomerId()==null) {
			throw new ResException("客户id不能为空");
		}
		if (baseDataCustomer.getCustomerName()==null) {
			throw new ResException("客户名称不能为空");
		}
		if (baseDataCustomer.getCustomerParentId()==null) {
			throw new ResException("客户父id不能为空");
		}
//		if (baseDataCustomer.getDataCustomerId()==null) {
//			throw new ResException("数据集权限与客户关联id不能为空");
//		}
	}

	private String getNodesCheckedTrueFalse(List<BaseDataCustomer> list, String customerId) {
		for (BaseDataCustomer baseDataCustomer : list) {
			if (baseDataCustomer.getCustomerId().toString().equals(customerId)) {
				return "true";
			}
			if (baseDataCustomer.getCustomerParentId().toString().equals(customerId)) {
				return "true";
			}
		}
		return "false";
	}
}
