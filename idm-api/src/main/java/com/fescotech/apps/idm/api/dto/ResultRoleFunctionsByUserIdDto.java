package com.fescotech.apps.idm.api.dto;

import java.util.ArrayList;
import java.util.List;

public class ResultRoleFunctionsByUserIdDto extends ResultRoleByUserIdDto{
	/**
	 * 角色权限
	 */
	private List<ResultFunctionDto> functions;

	public List<ResultFunctionDto> getFunctions() {
		if (functions == null) {
			functions = new ArrayList<>();
		}
		return functions;
	}

	public void setFunctions(List<ResultFunctionDto> functions) {
		this.functions = functions;
	}
}
