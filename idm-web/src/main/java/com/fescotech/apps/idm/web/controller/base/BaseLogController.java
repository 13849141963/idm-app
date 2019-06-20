package com.fescotech.apps.idm.web.controller.base;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fescotech.apps.idm.admin.domain.vo.R;
import com.fescotech.apps.idm.base.biz.service.IBaseLogService;
import com.fescotech.apps.idm.base.domain.BaseLog;
import com.fescotech.apps.idm.base.domain.vo.BaseLogVo;
import com.fescotech.apps.idm.util.Pager;
import com.fescotech.apps.idm.web.controller.AbstractController;

/**
 * 查询日志逻辑处理类
 * @author:lzl
 * @time:2017年6月29日 下午6:26:54
 */
@RestController
@RequestMapping("/base/log")
public class BaseLogController extends AbstractController {

	@Autowired
	private IBaseLogService baseLogService;
	
	/**
	 * 获取日志列表
	 * @param page
	 * @param limit
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/list")
	@RequiresPermissions("base:log:list")
	public R list(Integer page, Integer limit,String order,String sidx, @RequestParam(value="userName", required=false) String userName, 
			@RequestParam(value="timeSlot", required=false) String timeSlot) throws UnsupportedEncodingException {
		Map<String, Object> map = new HashMap<String, Object>();
		BaseLog baseLof = new BaseLog();
		map.put("start", (page - 1) * limit);
		map.put("end", page*limit);
		if (sidx != null && !sidx.equals("")) {
			if (sidx.equals("userName")) {
				sidx = "USER_NAME";
			} else if (sidx.equals("beginTimeName")) {
				sidx = "OP_START_TIME";
			} else if (sidx.equals("opResult")) {
				sidx = "OP_RESULT";
			}
			map.put("orderBy", sidx);
			map.put("orderFlag", order);
		}
		if(userName != null && !userName.equals("")) {
			userName = URLDecoder.decode(userName,"utf-8");
			map.put("userName", userName);
			baseLof.setUserName(userName);
		}
		if(timeSlot != null && !timeSlot.equals("") && !timeSlot.equals("è¯·éæ©")) {
			String[] time = timeSlot.split(" - ");
			map.put("beginTime", time[0]);
			map.put("endTime", time[1]);
			baseLof.setBeginTime(time[0]);
			baseLof.setEndTime(time[1]);
		}
		int total = baseLogService.queryBaseLogCount(baseLof);
		if (total > 0) {
			List<BaseLogVo> basseLogList = baseLogService.queryBaseLogList(map);
			for (int i = 0; i < basseLogList.size(); i++) {
				SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss:SSS");  
				Date beginTime = basseLogList.get(i).getOpStartTime();
				Date endTime = basseLogList.get(i).getOpEndTime();
				basseLogList.get(i).setBeginTimeName(sdf.format(beginTime));
				long timeConsuming = endTime.getTime() - beginTime.getTime();
				basseLogList.get(i).setTimeConsuming(timeConsuming);
			}
			Pager pageUtil = new Pager(basseLogList, total, limit, page);
			return R.ok().put("page", pageUtil);
		} else {
			return R.ok();
		}
	}
	
	/**
	 * 角色列表
	 */
	@RequestMapping("/detailInfor")
	@RequiresPermissions("sys:role:select")
	public R select(@RequestBody Long logId){
		if (logId != null && !logId.equals("")) {
			BaseLogVo baseLogVo = baseLogService.queryLogMsg(logId);
			if (baseLogVo != null) {
				return R.ok().put("result", baseLogVo.getLogMsg());
			} else {
				return R.ok().put("result", "没有数据");
			}
		} else {
			return R.error();
		}
	}
	
}
