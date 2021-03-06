/*******************************************************************************
 * Copyright (c) 2017 Pivotal, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Pivotal, Inc. - initial API and implementation
 *******************************************************************************/
package org.springframework.ide.vscode.commons.cloudfoundry.client.cftarget;

/**
 * 
 * A key for CF targets that uses {@link CFClientParams} values, EXCEPT for
 * credentials, as the key value.
 *
 */
public class ClientParamsCacheKey {

	public final CFClientParams fullParams;

	/**
	 * Use static API to create: {@link #from(CFClientParams)}
	 * @param fullParams
	 */
	private ClientParamsCacheKey(CFClientParams fullParams) {
		this.fullParams = fullParams;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fullParams.getApiUrl() == null) ? 0 : fullParams.getApiUrl().hashCode());
		result = prime * result + ((fullParams.getOrgName() == null) ? 0 : fullParams.getOrgName().hashCode());
		result = prime * result + (fullParams.skipSslValidation() ? 1231 : 1237);
		result = prime * result + ((fullParams.getSpaceName() == null) ? 0 : fullParams.getSpaceName().hashCode());
		result = prime * result + ((fullParams.getUsername() == null) ? 0 : fullParams.getUsername().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientParamsCacheKey other = (ClientParamsCacheKey) obj;
		if (fullParams.getApiUrl() == null) {
			if (other.fullParams.getApiUrl() != null)
				return false;
		} else if (!fullParams.getApiUrl().equals(other.fullParams.getApiUrl()))
			return false;
		if (fullParams.getOrgName() == null) {
			if (other.fullParams.getOrgName() != null)
				return false;
		} else if (!fullParams.getOrgName().equals(other.fullParams.getOrgName()))
			return false;
		if (fullParams.skipSslValidation() != other.fullParams.skipSslValidation())
			return false;
		if (fullParams.getSpaceName() == null) {
			if (other.fullParams.getSpaceName() != null)
				return false;
		} else if (!fullParams.getSpaceName().equals(other.fullParams.getSpaceName()))
			return false;
		if (fullParams.getUsername() == null) {
			if (other.fullParams.getUsername() != null)
				return false;
		} else if (!fullParams.getUsername().equals(other.fullParams.getUsername()))
			return false;
		return true;
	}
	
	public static ClientParamsCacheKey from(CFClientParams params) {
		return new ClientParamsCacheKey(params);
	}

}
