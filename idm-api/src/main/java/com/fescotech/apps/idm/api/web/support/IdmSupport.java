package com.fescotech.apps.idm.api.web.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.fesco.pafa.exceptions.MessageException;
import com.fescotech.apps.idm.api.dto.*;
import com.fescotech.apps.idm.api.util.CheckUtil;
import com.fescotech.apps.idm.base.biz.service.*;
import com.fescotech.apps.idm.base.domain.*;
import com.fescotech.apps.idm.base.domain.vo.ApiLogVo;
import com.fescotech.apps.idm.base.domain.vo.BaseFunctionVo;
import com.fescotech.apps.idm.base.domain.vo.BaseUserVo;
import com.fescotech.apps.idm.bootstrap.ServiceManager;
import org.springframework.beans.factory.annotation.Autowired;

public class IdmSupport {
	private static IBaseUserRoleService baseUserRoleService = ServiceManager.getBaseUserRoleService();
	private static IBaseRoleFunctionService baseRoleFunctionService = ServiceManager.getBaseRoleFunctionService();
	private static IBaseFunctionService baseFunctionService = ServiceManager.getBaseFunctionService();
	private static IBaseUserService baseUserService = ServiceManager.getBaseUserService();
	private static IBaseRoleService baseRoleService = ServiceManager.getBaseRoleService();
	private static IBaseCorpinfoService baseCorpinfoService = ServiceManager.getBaseCorpinfoService();

	/**
	 * 用户权限查询接口
	 *
	 * @param bs
	 * @param baseUser
	 * @return
	 */
	public static List<ResultFunctionDto> queryUserFunction(String idmKey, BaseSys bs, String loginName,
															String timeStamp, String sign) {
		BaseUser baseUser = baseUserService.queryUserByLoginName(loginName);
		if (baseUser == null)
			throw new MessageException("查无此帐号");
		String checkSignNoPwd = CheckUtil.getSignNoPwd(idmKey, loginName, timeStamp);// 校验唯一标识合法性
		if (!checkSignNoPwd.equals(sign)) {
			String password = baseUser.getUserPwd();
			String checkSign = CheckUtil.getSign(idmKey, loginName, timeStamp, password);// 校验唯一标识合法性
			if (!checkSign.equals(sign)) {
				throw new MessageException("唯一标识错误");
			}
		}
		ApiLogVo apiLogVo = ServiceManager.getApiLogService().queryLogMsg(sign);// 保证每次访问的请求都是重新生成的唯一标识，验证合法性
		if (apiLogVo != null)
			throw new MessageException("登录超时");
		List<ResultFunctionDto> resultFunctionDtolist = new ArrayList<ResultFunctionDto>();
		BaseUserRole baseUserRole = new BaseUserRole();
		baseUserRole.setUserId(baseUser.getUserId());
		List<BaseUserRole> baseUserRoleList = baseUserRoleService.getUserRoleList(baseUserRole);
		if (baseUserRoleList == null || baseUserRoleList.isEmpty()) {
			return null;
		}
		List<Long> functionIdLists = new ArrayList<Long>();
		for (int i = 0; i < baseUserRoleList.size(); i++) {
			List<Long> functionIdList = baseRoleFunctionService.queryFuncIdList(baseUserRoleList.get(i).getRoleId());
			if (functionIdList != null && functionIdList.size() > 0) {
				for (int j = 0; j < functionIdList.size(); j++) {
					functionIdLists.add(functionIdList.get(j));
				}
			}
		}

		if (functionIdLists != null && functionIdLists.size() > 0) {
			for (int i = 0; i < functionIdLists.size(); i++) {
				BaseFunction baseFunction = new BaseFunction();
				baseFunction.setFunctionId(functionIdLists.get(i));
				baseFunction.setSysId(Integer.valueOf(bs.getSysId().toString()));
				List<BaseFunctionVo> list = baseFunctionService.queryList(baseFunction);
				if (list != null && list.size() > 0) {
					ResultFunctionDto resultFunctionDto = buildFunctionDto(list);
					resultFunctionDtolist.add(resultFunctionDto);
				}
			}
		}
		return resultFunctionDtolist;

	}

	private static ResultFunctionDto buildFunctionDto(List<BaseFunctionVo> list) {
		ResultFunctionDto resultFunctionDto = new ResultFunctionDto();
		resultFunctionDto.setFunctionId(list.get(0).getFunctionId());
		resultFunctionDto.setFunctionName(list.get(0).getFunctionName());
		resultFunctionDto.setFunctionType(list.get(0).getFunctionType());
		resultFunctionDto.setFunState(list.get(0).getFunState());
		resultFunctionDto.setPerm(list.get(0).getPerm());
		resultFunctionDto.setUrl(list.get(0).getUrl());
		return resultFunctionDto;
	}

	/**
	 * 用户登录验证
	 *
	 * @param userName
	 *            用户名
	 * @param userPwd
	 *            口令
	 * @param sysId
	 *            登录系统标识
	 * @return
	 */
	public static ResultUserDto checkLogin(String loginName, String sign, String idmKey, String timeStamp, Long sysId) {
		BaseUserVo buv = baseUserService.checkLogin(loginName);
		if (buv == null)
			throw new MessageException("查无此帐号");
		String checkSignNoPwd = CheckUtil.getSignNoPwd(idmKey, loginName, timeStamp);// 校验唯一标识合法性
		if (!checkSignNoPwd.equals(sign)) {
			String password = buv.getUserPwd();
			String checkSign = CheckUtil.getSign(idmKey, loginName, timeStamp, password);// 校验唯一标识合法性
			if (!checkSign.equals(sign)) {
				throw new MessageException("唯一标识错误");
			}
		}
		ApiLogVo apiLogVo = ServiceManager.getApiLogService().queryLogMsg(sign);// 保证每次访问的请求都是重新生成的唯一标识，验证合法性
		if (apiLogVo != null)
			throw new MessageException("登录超时");
		ResultUserDto resultUserDto = new ResultUserDto();
		resultUserDto.setAddress(buv.getAddress());
		resultUserDto.setEmail(buv.getEmail());
		resultUserDto.setLoginName(buv.getLoginName());
		resultUserDto.setMobile(buv.getMobile());
		resultUserDto.setPhone(buv.getPhone());
		resultUserDto.setPostCode(buv.getPostCode());
		resultUserDto.setSex(buv.getSex());
		resultUserDto.setUserId(buv.getUserId());
		resultUserDto.setUserName(buv.getUserName());
		resultUserDto.setUserNumber(buv.getUserNumber());
		resultUserDto.setUserStatus(buv.getUserStatus());
		resultUserDto.setUserType(buv.getUserType());
		resultUserDto.setCropId(buv.getCorpId());
		// 将用户对应的角色的权限存到 uservo里(userId和sysID)
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userId", buv.getUserId());
		map.put("sysId", sysId);
		List<String> permsList = baseUserService.queryAllPerms(map);
		List<String> newList = new ArrayList<String>(new HashSet<String>(permsList)); // 去除重复权限
		resultUserDto.setPerms(newList);
		return resultUserDto;
	}

	/**
	 * 修改用户名
	 *
	 * @param loginName
	 * @param password
	 * @param newPassword
	 * @param sign
	 */
	public static BaseUser changeUserPassword(String loginName, String newPassword, String sign, String idmKey,
											  String timeStamp) {
		BaseUser user = baseUserService.queryUserByLoginName(loginName);
		if (user == null)
			throw new MessageException("查无此帐号");
		String checkSignNoPwd = CheckUtil.getSignNoPwd(idmKey, loginName, timeStamp);// 校验唯一标识合法性
		if (!checkSignNoPwd.equals(sign)) {
			String password = user.getUserPwd();
			String checkSign = CheckUtil.getSign(idmKey, loginName, timeStamp, password);// 校验唯一标识合法性
			if (!checkSign.equals(sign)) {
				throw new MessageException("唯一标识错误");
			}
		}
		ApiLogVo apiLogVo = ServiceManager.getApiLogService().queryLogMsg(sign);// 保证每次访问的请求都是重新生成的唯一标识，验证合法性
		if (apiLogVo != null)
			throw new MessageException("登录超时");
		Long userId = user.getUserId();
		// 更新密码
		int count = baseUserService.updatePassword(userId, user.getUserPwd(), newPassword);
		if (count == 0)
			throw new MessageException("原密码不正确");
		user.setUserPwd(null);
		return user;
	}

	/**
	 * 用户信息查询接口
	 */
	public static ResultUserDto queryUserInfor(String loginName, String sign, String idmKey, String timeStamp) {
		BaseUser bUser = new BaseUser();
		bUser.setLoginName(loginName);
		BaseUser fUser = baseUserService.queryUserByBaseUser(bUser);
		if (fUser == null)
			throw new MessageException("查无此用户");
		String checkSignNoPwd = CheckUtil.getSignNoPwd(idmKey, loginName, timeStamp);// 校验唯一标识合法性
		if (!checkSignNoPwd.equals(sign)) {
			String password = fUser.getUserPwd();
			String checkSign = CheckUtil.getSign(idmKey, loginName, timeStamp, password);// 校验唯一标识合法性
			if (!checkSign.equals(sign)) {
				throw new MessageException("唯一标识错误");
			}
		}
		ApiLogVo apiLogVo = ServiceManager.getApiLogService().queryLogMsg(sign);// 保证每次访问的请求都是重新生成的唯一标识，验证合法性
		if (apiLogVo != null)
			throw new MessageException("登录超时");
		fUser.setUserPwd(null);
		ResultUserDto resultUserDto = new ResultUserDto();
		resultUserDto.setAddress(fUser.getAddress());
		resultUserDto.setEmail(fUser.getEmail());
		resultUserDto.setLoginName(fUser.getLoginName());
		resultUserDto.setMobile(fUser.getMobile());
		resultUserDto.setPhone(fUser.getPhone());
		resultUserDto.setPostCode(fUser.getPostCode());
		resultUserDto.setSex(fUser.getSex());
		resultUserDto.setUserId(fUser.getUserId());
		resultUserDto.setUserName(fUser.getUserName());
		resultUserDto.setUserNumber(fUser.getUserNumber());
		resultUserDto.setUserStatus(fUser.getUserStatus());
		resultUserDto.setUserType(fUser.getUserType());
		resultUserDto.setOrgId(fUser.getOrgId());
		resultUserDto.setCropId(fUser.getCorpId());
		// 根据orgId获取orgName
		resultUserDto
				.setOrgName(ServiceManager.getBaseOrganizationService().queryObject(fUser.getOrgId()).getOrgName());
		return resultUserDto;
	}

	/**
	 * 根据角色ID返回用户数据集
	 *
	 * @param roleId
	 * @param sign
	 * @param idmKey
	 * @param timeStamp
	 * @return
	 */
	public static List<ResultUserByRoleIdDto> queryUserInforByRoleId(String roleId, String sign, String idmKey,
																	 String timeStamp) {
		String checkSign = CheckUtil.getSignNoPwd(idmKey, roleId, timeStamp);// 校验唯一标识合法性
		if (!checkSign.equals(sign))
			throw new MessageException("唯一标识错误");
		ApiLogVo apiLogVo = ServiceManager.getApiLogService().queryLogMsg(sign);// 保证每次访问的请求都是重新生成的唯一标识，验证合法性
		if (apiLogVo != null)
			throw new MessageException("登录超时");
		List<ResultUserByRoleIdDto> resultList = new ArrayList<ResultUserByRoleIdDto>();
		List<BaseUserRole> userRoleList = baseUserRoleService.queryUserRoleListByRoleId(Long.valueOf(roleId));
		if (userRoleList != null && userRoleList.size() > 0) {
			for (BaseUserRole baseUserRole : userRoleList) {
				ResultUserByRoleIdDto resultUserByRoleIdDto = new ResultUserByRoleIdDto();
				BaseUser baseUser = baseUserService.queryUserById(baseUserRole.getUserId());
				if (baseUser != null) {
					resultUserByRoleIdDto.setUserId(baseUserRole.getUserId());
					resultUserByRoleIdDto.setLoginName(baseUser.getLoginName());
					resultUserByRoleIdDto.setUserName(baseUser.getUserName());
					resultList.add(resultUserByRoleIdDto);
				}
			}
		}
		return resultList;
	}

	/**
	 * 根据用户ID返回角色数据集
	 *
	 * @param userId
	 * @param sign
	 * @param idmKey
	 * @param timeStamp
	 * @return
	 */
	public static List<ResultRoleByUserIdDto> queryRoleInforByUserId(String userId, String sign, String idmKey,
																	 String timeStamp, BaseSys bs) {
		String checkSign = CheckUtil.getSignNoPwd(idmKey, userId, timeStamp);// 校验唯一标识合法性
		if (!checkSign.equals(sign))
			throw new MessageException("唯一标识错误");
		ApiLogVo apiLogVo = ServiceManager.getApiLogService().queryLogMsg(sign);// 保证每次访问的请求都是重新生成的唯一标识，验证合法性
		if (apiLogVo != null)
			throw new MessageException("登录超时");
		List<ResultRoleByUserIdDto> resultList = new ArrayList<ResultRoleByUserIdDto>();
		List<BaseUserRole> userRoleList = baseUserRoleService.queryUserRoleListByUserId(Long.valueOf(userId));
		if (userRoleList != null && userRoleList.size() > 0) {
			for (BaseUserRole baseUserRole : userRoleList) {
				ResultRoleFunctionsByUserIdDto resultRoleByUserIdDto = new ResultRoleFunctionsByUserIdDto();
				BaseRole baseRole = new BaseRole();
				baseRole.setRoleId(baseUserRole.getRoleId());
				List<BaseRole> roleList = baseRoleService.queryBaseRole(baseRole);
				if (roleList != null && roleList.size() > 0) {
					resultRoleByUserIdDto.setRoleId(baseUserRole.getRoleId());
					resultRoleByUserIdDto.setRoleName(roleList.get(0).getRoleName());
					resultList.add(resultRoleByUserIdDto);
				}
				List<Long> functionIdList = baseRoleFunctionService.queryFuncIdList(baseUserRole.getRoleId());
				for (int i = 0; i < functionIdList.size(); i++) {
					BaseFunction baseFunction = new BaseFunction();
					baseFunction.setFunctionId(functionIdList.get(i));
					baseFunction.setSysId(Integer.valueOf(bs.getSysId().toString()));
					List<BaseFunctionVo> list = baseFunctionService.queryList(baseFunction);
					if (list != null && list.size() > 0) {
						ResultFunctionDto resultFunctionDto = buildFunctionDto(list);
						resultRoleByUserIdDto.getFunctions().add(resultFunctionDto);
					}
				}
			}
		}
		return resultList;
	}

	/**
	 * 根据用户ID返回角色数据集
	 *
	 * @param userId
	 * @param sign
	 * @param idmKey
	 * @param timeStamp
	 * @return
	 */
	public static List<ResultRoleByUserIdDto> queryRoleInforByUserId(String userId, String sign, String idmKey,
																	 String timeStamp) {
		String checkSign = CheckUtil.getSignNoPwd(idmKey, userId, timeStamp);// 校验唯一标识合法性
		if (!checkSign.equals(sign))
			throw new MessageException("唯一标识错误");
		ApiLogVo apiLogVo = ServiceManager.getApiLogService().queryLogMsg(sign);// 保证每次访问的请求都是重新生成的唯一标识，验证合法性
		if (apiLogVo != null)
			throw new MessageException("登录超时");
		List<ResultRoleByUserIdDto> resultList = new ArrayList<ResultRoleByUserIdDto>();
		List<BaseUserRole> userRoleList = baseUserRoleService.queryUserRoleListByUserId(Long.valueOf(userId));
		if (userRoleList != null && userRoleList.size() > 0) {
			for (BaseUserRole baseUserRole : userRoleList) {
				ResultRoleByUserIdDto resultRoleByUserIdDto = new ResultRoleByUserIdDto();
				BaseRole baseRole = new BaseRole();
				baseRole.setRoleId(baseUserRole.getRoleId());
				List<BaseRole> roleList = baseRoleService.queryBaseRole(baseRole);
				if (roleList != null && roleList.size() > 0) {
					resultRoleByUserIdDto.setRoleId(baseUserRole.getRoleId());
					resultRoleByUserIdDto.setRoleName(roleList.get(0).getRoleName());
					resultList.add(resultRoleByUserIdDto);
				}
			}
		}
		return resultList;
	}



	/**
	 * 添加公司信息
	 *
	 * @param loginName
	 * @param sign
	 * @param idmKey
	 * @param timeStamp
	 * @return
	 */
	public static void saveCropInfo(String idmKey, String sign, String loginName,
							   String timeStamp,CorpInfoDto dto){
		String checkSign = CheckUtil.getSignNoPwd(idmKey, loginName, timeStamp);// 校验唯一标识合法性
		if (!checkSign.equals(sign))
			throw new MessageException("唯一标识错误");
		ApiLogVo apiLogVo = ServiceManager.getApiLogService().queryLogMsg(sign);// 保证每次访问的请求都是重新生成的唯一标识，验证合法性
		if (apiLogVo != null)
			throw new MessageException("登录超时");

		if(dto == null){
			throw new MessageException("公司信息不能为空");
		}
		BaseCorpinfo baseCorpinfo = new BaseCorpinfo();
		if (dto.getCompanyName() != null && !"".equals(dto.getCompanyName())){
			baseCorpinfo.setCompanyName(dto.getCompanyName());
		}
		if (dto.getShortName() != null && !"".equals(dto.getShortName())){
			baseCorpinfo.setShortName(dto.getShortName());
		}
		if (dto.getDescription()!= null && !"".equals(dto.getDescription())){
			baseCorpinfo.setDescription(dto.getDescription());
		}
		if (dto.getCorpArea() != null && !"".equals(dto.getCorpArea())){
			baseCorpinfo.setCorpArea(dto.getCorpArea());
		}
		if (dto.getOfficeAddr() != null && !"".equals(dto.getOfficeAddr())){
			baseCorpinfo.setOfficeAddr(dto.getOfficeAddr());
		}
		if (dto.getWebsite() != null && !"".equals(dto.getWebsite())){
			baseCorpinfo.setWebsite(dto.getWebsite());
		}
		if(baseCorpinfo != null){
			baseCorpinfoService.save(baseCorpinfo);
		}
	}





}
