/*
 * Copyright (c) 2023, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.extension.identity.verification.api.rest.common.factory;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.extension.identity.verification.mgt.IdentityVerificationManager;

/**
 * Factory Beans serves as a factory for creating other beans within the IOC container. This factory bean is used to
 * instantiate the IdentityVerificationServiceOSGIFactory inside the container.
 */
public class IdentityVerificationManagerOSGIFactory extends AbstractFactoryBean<IdentityVerificationManager> {

    private IdentityVerificationManager identityVerificationManager;

    @Override
    public Class<?> getObjectType() {

        return Object.class;
    }

    @Override
    protected IdentityVerificationManager createInstance() throws Exception {

        if (this.identityVerificationManager == null) {
            IdentityVerificationManager identityVerificationManager = (IdentityVerificationManager)
                    (PrivilegedCarbonContext.getThreadLocalCarbonContext()
                            .getOSGiService(IdentityVerificationManager.class, null));
            if (identityVerificationManager != null) {
                this.identityVerificationManager = identityVerificationManager;
            } else {
                throw new Exception("Unable to retrieve IdentityVerificationService.");
            }
        }
        return this.identityVerificationManager;
    }

}